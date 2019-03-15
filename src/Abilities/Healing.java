package Abilities;

public class Healing extends Ability {
    public Healing () {
        this.setName("Healing");
        this.setAbilityType(AbilityType.SINGLE_TARGET_HEAL);
        this.setRecovery(2);
        this.setDelay(5);
        this.setMinPower(24);
        this.setMaxPower(44);
        this.setManaCost(20);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor prepares to cast healing on %target.");
        this.setDoActionText(Ansi.BLUE +  "%actor casts healing on %target, restoring %number health.");
    }
}
