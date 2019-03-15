package Abilities;

public class Poison extends Ability {
    public Poison () {
        this.setName("Poison");
        this.setAbilityType(AbilityType.DAMAGE_OVER_TIME);
        this.setRecovery(2);
        this.setDelay(4);
        this.setMinPower(5);
        this.setMaxPower(10);
        this.setManaCost(16);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor prepares to cast poison at %target.");
        this.setDoActionText(Ansi.GREEN +  "%actor finishes casting poison at %target.");
    }
}
