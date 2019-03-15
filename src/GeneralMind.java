import Abilities.Ability;
import Abilities.AbilityType;

import java.util.List;

public class GeneralMind extends Mind {
    public CombatAction decideAction(Character actor, List<Party> parties) {

        for (Ability ability : actor.abilities) {
            if (actor.mana < ability.getManaCost())
                continue;

            if (ability.getAbilityType() == AbilityType.GROUP_BUFF) {
                Party allyParty = getFriendlyParty(parties, actor);

                switch (ability.getName()) {
                    case "GroupRegeneration":
                        if (!actor.isRegenerating) {
                            return new CombatAction(ability, actor, allyParty);
                        }
                        break;
                }
            }
        }

        for (Ability ability : actor.abilities) {
            if (actor.mana < ability.getManaCost())
                continue;

            if (ability.getAbilityType() == AbilityType.SELF_BUFF) {
                Party allyParty = getFriendlyParty(parties, actor);

                switch (ability.getName()) {
                    case "Rage":
                        if (!actor.isRaging) {
                            return new CombatAction(ability, actor);
                        }
                        break;
                }
            }
        }

        for (Ability ability : actor.abilities) {
            if (actor.mana < ability.getManaCost())
                continue;

            if (ability.getAbilityType() == AbilityType.DAMAGE_OVER_TIME) {
                Party enemyParty = getEnemyParty(parties, actor);

                switch (ability.getName()) {
                    case "Poison":
                        for (Character character : enemyParty.characters) {
                            if (!character.isPoisoned)
                                return new CombatAction(ability, actor, character);
                        }
                        break;
                }
            }
        }

        for (Ability ability : actor.abilities) {
            if (actor.mana < ability.getManaCost())
                continue;

            if (ability.getAbilityType() == AbilityType.SINGLE_TARGET_HEAL) {
                Party allyParty = getFriendlyParty(parties, actor);
                Character healTarget = getSingleTargetHealTarget(allyParty);

                if (healTarget != null && healTarget.maxHitPoints - healTarget.hitPoints > ability.getMaxPower())
                    return new CombatAction(ability, actor, healTarget);
            }
        }

        for (Ability ability : actor.abilities) {
            if (actor.mana < ability.getManaCost())
                continue;

            if (ability.getAbilityType() == AbilityType.SINGLE_TARGET_DAMAGE) {
                Party enemyParty = getEnemyParty(parties, actor);
                Character lowestHealthEnemy = getLowestHealthCharacter(enemyParty);

                if (lowestHealthEnemy != null)
                    return new CombatAction(ability, actor, lowestHealthEnemy);
            }
        }

        for (Ability ability : actor.abilities) {
            if (ability.getAbilityType() == AbilityType.MELEE) {
                Party enemyParty = getEnemyParty(parties, actor);
                for (Character character : enemyParty.characters) {
                    if (character.isAlive)
                        return new CombatAction(ability, actor, character);
                }
            }
        }

        return null;
    }

    private Character getLowestHealthCharacter(Party party) {
        int lowestHitpoints = 99999;
        Character lowestHealthCharacter = null;
        for (Character character : party.characters) {
            if (character.isAlive && character.hitPoints < lowestHitpoints) {
                lowestHealthCharacter = character;
                lowestHitpoints = character.hitPoints;
            }
        }

        return lowestHealthCharacter;
    }

    private Character getSingleTargetHealTarget(Party party) {
        int lowestHitpoints = 99999;
        Character lowestHealthCharacter = null;
        for (Character character : party.characters) {
            if (character.isAlive && character.hitPoints < lowestHitpoints && (character.maxHitPoints - character.hitPoints > 20)) {
                lowestHealthCharacter = character;
                lowestHitpoints = character.hitPoints;
            }
        }

        return lowestHealthCharacter;
    }

    private Party getEnemyParty(List<Party> parties, Character actor) {
        for (Party party : parties) {
            if (!party.characters.contains(actor))
                return party;
        }

        return null;
    }

    private Party getFriendlyParty(List<Party> parties, Character actor) {
        for (Party party : parties) {
            if (party.characters.contains(actor))
                return party;
        }

        return null;
    }
}

