import Abilities.Ability;
import Abilities.AbilityType;
import Abilities.Ansi;
import Abilities.DamageType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Math;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String args[]) {
        List<Party> parties = new ArrayList<>();

        for (String arg : args) {
            parties.add(new Party(arg));
        }

        if (parties.size() == 2) {
            Duel(parties);
        }
        else if (parties.size() > 2) {
            Brawl(parties);
        }
        else {
            System.out.println("You must run this with at least 2 parties");
        }
    }

    private static void Duel (List<Party> parties) {
        int turnLimit = 1000;

        for (Party party : parties) {
            System.out.println(party.name);
        }

        ArrayList<CombatAction>[] queuedActions = new ArrayList[turnLimit];
        for(int i = 0; i < queuedActions.length; i++) {
            queuedActions[i] = new ArrayList<CombatAction>();
        }

        Map<Integer, List <CombatAction>> actionMap = new HashMap<Integer,List<CombatAction>>();

        // Main combat loop
        for (int tick = 0; tick < turnLimit; tick++) {
            // For debugging ticks, TODO: make a --debug flag
            // System.out.print("[" + Integer.toString(tick) + "]: ");

            if (tick % 20 == 0)
                DisplayStatus(parties);

            // Tick based events
            for (Party party : parties) {
                for (Character character : party.characters) {
                    if (!character.isAlive)
                        continue;

                    // Mana Regen
                    if (tick % Math.round(100 / character.intellect) == 0 && character.mana < character.maxMana)
                        character.mana++;

                    // Health Regen
                    if (character.isRegenerating && tick % 2 == 0) {
                        character.hitPoints = Math.min(character.hitPoints + character.regenValue, character.maxHitPoints);
                        System.out.println(String.format("%s%s regenerates %s health.%s", Ansi.LIGHT_BLUE, character.name, character.regenValue, Ansi.RESET));
                        character.regenValue -= 2;
                        if (character.regenValue <= 0) {
                            character.isRegenerating = false;
                            System.out.println(String.format("%s%s is no longer regenerating.%s", Ansi.DARK_GRAY, character.name, Ansi.RESET));
                        }
                    }

                    // Poison
                    if (character.isPoisoned && tick % 3 == 0) {
                        int poisonDamage = Math.round(1 / character.poisonResist * character.poisonValue);
                        System.out.println(String.format("%s%s takes %s damage from poison.%s", Ansi.LIGHT_GREEN, character.name, poisonDamage, Ansi.RESET));
                        character.hitPoints -= poisonDamage;
                        character.poisonValue -= 1;
                        if (character.poisonValue <= 0) {
                            character.isPoisoned = false;
                            System.out.println(String.format("%s%s is no longer poisoned.%s", Ansi.DARK_GRAY, character.name, Ansi.RESET));
                        }
                    }

                    // Death check for DoTs
                    if (character.hitPoints <= 0 && character.isAlive) {
                        character.isAlive = false;
                        System.out.println(Ansi.RED + character.name + " has died." + Ansi.RESET);
                    }
                }
            }

            // Do queued actions
            while (!queuedActions[tick].isEmpty()) {
                CombatAction action = queuedActions[tick].remove(0);
                if (!action.actor.isAlive)
                    continue;
                int power = ThreadLocalRandom.current().nextInt(action.ability.getMinPower(), action.ability.getMaxPower());

                // Action Fizzle Checks
                if ((action.targetCharacter != null && !action.targetCharacter.isAlive)
                        || (action.ability.getName() == "Poison" && action.targetCharacter.isPoisoned)
                        || (action.ability.getAbilityType() == AbilityType.SINGLE_TARGET_HEAL && action.targetCharacter.hitPoints >= action.targetCharacter.maxHitPoints)){
                    action.actor.recoveryTurnsRemaining = 0;
                    action.actor.isUsingAbility = false;
                    continue;
                }

                action.actor.recoveryTurnsRemaining = GetRecoveryTime(action);
                action.actor.isUsingAbility = false;
                action.actor.mana -= action.ability.getManaCost();
                switch (action.ability.getAbilityType()) {
                    case SELF_BUFF:
                        DoSelfBuff(action, power);
                        break;
                    case SINGLE_TARGET_DAMAGE:
                    case MELEE:
                        DoDamage(action, power);
                        break;
                    case SINGLE_TARGET_HEAL:
                        DoHealing(action, power);
                        break;
                    case GROUP_BUFF:
                        DoGroupBuff(action, power);
                        break;
                    case DAMAGE_OVER_TIME:
                        DoDamageOverTime(action, power);
                }
            }

            // Queue new actions
            for (int p = 0; p < parties.size(); p++) {
                for (int c = 0; c < parties.get(p).characters.size(); c++) {
                    Party party = parties.get(p);
                    Character character = party.characters.get(c);

                    if (!character.isAlive || character.isUsingAbility || character.isCasting)
                        continue;

                    if (character.recoveryTurnsRemaining > 0) {
                        character.recoveryTurnsRemaining--;
                        continue;
                    }

                    CombatAction action = character.mind.decideAction(character, parties);
                    if (action != null) {
                        int nextAction = tick + (action.ability.getDelay() * Math.round(10f / action.actor.dexterity));
                        if (nextAction == tick)
                            nextAction += 1;
                        queuedActions[nextAction].add(action);
                        action.actor.isUsingAbility = true;
                        CombatOutput(action.ability.getPrepareActionText(), action);
                    }
                }
            }

            // end of game check -- TODO: make less gross
            List<Party> deadPartyList = new ArrayList<>();
            for (Party party : parties) {
                if (party.characters.stream().noneMatch(c -> c.isAlive)) {
                    deadPartyList.add(party);
                }
            }
            if (deadPartyList.size() > 0) {
                for (Party party : parties) {
                    if (!deadPartyList.contains(party)) {
                        System.out.println(party.name + " has won!");
                        System.exit(0);
                    }
                }
            }

            // End of tick
        }

        System.out.println("Turn limit reached.  Something probably went wrong.");
    }

    private static void DoDamage(CombatAction action, int damage) {
        if (action.ability.getDamageType() == DamageType.RANGED_HIT) {
            int random = ThreadLocalRandom.current().nextInt(1, 10);
            if (random * action.actor.perception / 10 < 5) {
                CombatOutput("%actor misses %target with %ability.", action);
                return;
            }
        }

        if (action.actor.isRaging || action.targetCharacter.isRaging)
            damage += ThreadLocalRandom.current().nextInt(4, 8);

        damage = damage * action.actor.might / 10;
        damage = damage * 10 / action.targetCharacter.resolve;

        for (Ability ability : action.targetCharacter.abilities) {
            if (ability.getName() == "Shield") {
                switch (action.ability.getAbilityType()) {
                    case MELEE:
                        if (action.ability.getName() != "Hammer")
                            damage *= .5;
                        break;
                    case SINGLE_TARGET_DAMAGE:
                        damage *= .9;
                        break;
                }
            }
        }
        action.targetCharacter.hitPoints -= damage;
        CombatOutput(action.ability.getDoActionText(), action, damage);
        if (action.targetCharacter.hitPoints <= 0) {
            action.targetCharacter.isAlive = false;
            CombatOutput(Ansi.RED + "%target has died.", action);
        }
    }

    private static void DoHealing(CombatAction action, int healing) {
        healing = healing * action.actor.might / 10;
        action.targetCharacter.hitPoints += healing;
        if (action.targetCharacter.hitPoints > action.targetCharacter.maxHitPoints)
            action.targetCharacter.hitPoints = action.targetCharacter.maxHitPoints;
        CombatOutput(action.ability.getDoActionText(), action, healing);
    }

    private static void CombatOutput(String text, CombatAction action, int number) {
        text = text.replaceAll("%number", Integer.toString(number));
        CombatOutput(text, action);
    }

    private static void CombatOutput(String text, CombatAction action) {
        text = text.replaceAll("%actor", action.actor.name);
        text = text.replaceAll("%ability", action.ability.getName());
        if (action.targetCharacter != null)
            text = text.replaceAll("%target", action.targetCharacter.name);
        System.out.println(text + Ansi.RESET);
    }

    private static int GetRecoveryTime(CombatAction action) {
        return action.ability.getRecovery() * 10 / action.actor.dexterity;
    }

    private static void DoGroupBuff(CombatAction action, int power) {
        switch (action.ability.getName()) {
            case "GroupRegeneration":
                System.out.println(Ansi.BLUE + action.actor.name + " finishes casting group regeneration." + Ansi.RESET);
                power = power * action.actor.might / 10;
                for (Character character : action.targetParty.characters) {
                    if (!character.isAlive)
                        continue;
                    character.isRegenerating = true;
                    if (character.regenValue < power) {
                        System.out.println(Ansi.BLUE + character.name + " begins to regenerate." + Ansi.RESET);
                        character.regenValue = power;
                    }
                }
                break;
        }
    }

    private static void DoDamageOverTime(CombatAction action, int power) {
        CombatOutput(action.ability.getDoActionText(), action, power);
        switch (action.ability.getName()) {
            case "Poison":

                power = power * action.actor.might / 10;
                action.targetCharacter.isPoisoned = true;
                action.targetCharacter.poisonValue = power;
                break;
        }
    }

    private static void DoSelfBuff(CombatAction action, int power) {
        CombatOutput(action.ability.getDoActionText(), action, power);
        switch (action.ability.getName()) {
            case "Rage":
                action.actor.isRaging = true;
                break;
        }
    }

    private static void DisplayStatus(List<Party> parties) {
        for (Party party : parties) {
            System.out.print("\n");
            System.out.println(party.name);
            System.out.println(new String(new char[party.name.length()]).replace("\0", "-"));
            for (Character character : party.characters) {
                if (character.isAlive)
                    System.out.print( String.format("%s %s/%shp %s/%smana | ", character.name, character.hitPoints, character.maxHitPoints, character.mana, character.maxMana));
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    private static void Brawl (List<Party> parties) {

    }
}
