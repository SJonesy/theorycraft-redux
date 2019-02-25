package Abilities;

public class Sword extends Ability {
    public Sword () {
        this.setName("Sword");
        this.setAbilityType(AbilityType.MELEE);
        this.setRecovery(10);
        this.setDelay(2);
        this.setMinPower(10);
        this.setMaxPower(20);
        this.setPrepareActionText("%actor prepares to strike %target with his sword.");
        this.setDoActionText("%actor strikes %target with his sword for %number damage.");
    }
}
