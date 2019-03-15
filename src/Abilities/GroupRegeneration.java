package Abilities;

public class GroupRegeneration extends Ability {
    public GroupRegeneration () {
        this.setName("GroupRegeneration");
        this.setAbilityType(AbilityType.GROUP_BUFF);
        this.setRecovery(8);
        this.setDelay(10);
        this.setMinPower(10);
        this.setMaxPower(20);
        this.setManaCost(30);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor prepares to cast group regeneration.");
        this.setDoActionText(Ansi.BLUE +  "%actor finishes casting group regeneration.");
    }
}
