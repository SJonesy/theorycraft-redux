package Abilities;

public class Sword extends Ability {
    public Sword () {
        this.setName("Sword");
        this.setAbilityType(AbilityType.MELEE);
        this.setRecovery(1);
        this.setDelay(3);
        this.setMinPower(8);
        this.setMaxPower(17);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor prepares to strike %target with his sword.");
        this.setDoActionText("%actor strikes %target with his sword for %number damage.");
    }
}
