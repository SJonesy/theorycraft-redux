import Abilities.Ability;

public class CombatAction {
    public Ability ability;
    public Character actor;
    public Character targetCharacter;
    public Party targetParty;
    public int tick;

    public CombatAction() { }

    public CombatAction(Ability ability, Character actor, Character targetCharacter) {
        this.ability = ability;
        this.actor = actor;
        this.targetCharacter = targetCharacter;
    }

    public CombatAction(Ability ability, Character actor, Party targetParty) {
        this.ability = ability;
        this.actor = actor;
        this.targetParty = targetParty;
    }
}

