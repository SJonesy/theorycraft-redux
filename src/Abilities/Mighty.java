package Abilities;

public class Mighty extends Ability {
    public Mighty() {
        this.setName("Mighty");
        this.setAbilityType(AbilityType.PASSIVE);
        this.setMightModifier(4);
    }
}
