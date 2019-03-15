package Abilities;

public class Rage extends Ability {
    public Rage() {
        this.setName("Rage");
        this.setAbilityType(AbilityType.SELF_BUFF);
        this.setRecovery(0);
        this.setDelay(2);
        this.setMinPower(0);
        this.setMaxPower(1);
        this.setManaCost(1);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor starts to seethe with anger.");
        this.setDoActionText(Ansi.BOLD + "%actor roars as blood and adrenaline soar through his veins.");
        this.setClassOnly(true);
    }
}
