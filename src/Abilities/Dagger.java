package Abilities;

public class Dagger extends Ability {
    public Dagger () {
        this.setName("Dagger");
        this.setAbilityType(AbilityType.MELEE);
        this.setRecovery(1);
        this.setDelay(2);
        this.setMinPower(4);
        this.setMaxPower(10);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor prepares to strike %target with his dagger.");
        this.setDoActionText("%actor stabs %target with his dagger for %number damage.");
    }
}
