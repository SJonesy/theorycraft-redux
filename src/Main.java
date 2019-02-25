import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        int turnLimit = 500;

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
            System.out.print("[" + Integer.toString(tick) + "]: ");

            // Do queued actions
            while (!queuedActions[tick].isEmpty()) {
                CombatAction action = queuedActions[tick].remove(0);
                if (!action.actor.isAlive)
                    continue;
                action.actor.recoveryTurnsRemaining += GetRecoveryTime(action);
                action.actor.isUsingAbility = false;
                switch (action.ability.getAbilityType()) {
                    case MELEE:
                        int damage = ThreadLocalRandom.current().nextInt(action.ability.getMinPower(), action.ability.getMaxPower());
                        DoDamage(action, damage);
                        break;
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
                        int nextAction = tick + action.ability.getDelay() * 10 / action.actor.dexterity;
                        queuedActions[nextAction].add(action);
                        action.actor.isUsingAbility = true;
                        CombatOutput(action.ability.getPrepareActionText(), action);
                    }
                }
            }

            // end of game check -- TODO: make less gross
            List<Party> deadPartyList = new ArrayList<>();
            for (Party party : parties) {
                if (party.characters.stream().filter(c -> c.isAlive).count() == 0) {
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
        damage = damage * action.actor.might / 10;
        damage = damage * 10 / action.targetCharacter.resolve;
        action.targetCharacter.hitPoints -= damage;
        CombatOutput(action.ability.getDoActionText(), action, damage);
        if (action.targetCharacter.hitPoints <= 0) {
            action.targetCharacter.isAlive = false;
            System.out.println(action.targetCharacter.name + " has died.");
        }
    }

    private static void CombatOutput(String text, CombatAction action, int number) {
        text = text.replaceAll("%number", Integer.toString(number));
        CombatOutput(text, action);
    }

    private static void CombatOutput(String text, CombatAction action) {
        text = text.replaceAll("%actor", action.actor.name);
        text = text.replaceAll("%target", action.targetCharacter.name);
        System.out.println(text);
    }

    private static void Brawl (List<Party> parties) {

    }

    private static int GetRecoveryTime(CombatAction action) {
        return action.ability.getRecovery() * 10 / action.actor.dexterity;
    }
}
