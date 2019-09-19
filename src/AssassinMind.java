import Abilities.Ability;
import Abilities.AbilityType;

import java.util.List;

public class AssassinMind extends GeneralMind {
    public CombatAction decideAction(Character actor, List<Party> parties) {
        Ability ability = findHighestDamageAbility(actor);
        Party enemyParty = getEnemyParty(parties, actor);
        Character lowestHealthEnemy = getLowestHealthCharacter(enemyParty);

        if ( ability != null
                && actor.mana >= ability.getManaCost()
                && lowestHealthEnemy != null
                && lowestHealthEnemy.hitPoints <= lowestHealthEnemy.maxHitPoints * .25) {
            return new CombatAction(ability, actor, lowestHealthEnemy);
        }

        return super.decideAction(actor, parties);
    }

    public Ability findHighestDamageAbility(Character actor) {
        Ability highestDamageAbility = null;
        float highestAveragePower = 0;
        for (Ability ability : actor.abilities) {
            if (ability.getAbilityType() == AbilityType.SINGLE_TARGET_DAMAGE) {
                float abilityAveragePower = (ability.getMinPower() + ability.getMaxPower()) / 2.0f;
                 if (abilityAveragePower > highestAveragePower) {
                     highestDamageAbility = ability;
                     highestAveragePower = abilityAveragePower;
                }

            }
        }
        return highestDamageAbility;
    }
}
