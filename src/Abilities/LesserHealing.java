package Abilities;

public class LesserHealing extends Ability {
    public LesserHealing () {
        this.setName("LesserHealing");
        this.setAbilityType(AbilityType.SINGLE_TARGET_HEAL);
        this.setRecovery(2);
        this.setDelay(4);
        this.setMinPower(12);
        this.setMaxPower(22);
        this.setManaCost(10);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor prepares to cast lesser healing on %target.");
        this.setDoActionText(Ansi.BLUE +  "%actor casts lesser healing on %target, restoring %number health.");
    }
}
