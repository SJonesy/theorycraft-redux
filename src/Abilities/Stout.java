package Abilities;

public class Stout extends Ability {
    public Stout() {
        this.setName("Stout");
        this.setAbilityType(AbilityType.PASSIVE);
        this.setConstitutionModifier(3);
        this.setResolveModifier(3);
    }
}
