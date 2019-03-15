package Abilities;

public class Hammer extends Ability {
    Hammer() {
        this.setName("Hammer");
        this.setAbilityType(AbilityType.MELEE);
        this.setRecovery(1);
        this.setDelay(4);
        this.setMinPower(12);
        this.setMaxPower(20);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor prepares to smash %target with his hammer.");
        this.setDoActionText("%actor smashes %target with his hammer for %number damage.");
    }
}
