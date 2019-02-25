import Abilities.Ability;
import Abilities.AbilityType;

import java.util.List;

public class GeneralMind extends Mind {
    public CombatAction decideAction(Character actor, List<Party> parties) {
        for (int i = 0; i < actor.abilities.size(); i++) {
            Ability ability = actor.abilities.get(i);
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

    private Party getEnemyParty(List<Party> parties, Character actor) {
        for (Party party : parties) {
            if (!party.characters.contains(actor))
                return party;
        }

        return null;
    }
}

