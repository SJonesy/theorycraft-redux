package Abilities;

public class MagicMissile extends Ability {
    public MagicMissile () {
        this.setName("MagicMissile");
        this.setAbilityType(AbilityType.SINGLE_TARGET_DAMAGE);
        this.setDamageType(DamageType.RANGED_HIT);
        this.setRecovery(2);
        this.setDelay(3);
        this.setMinPower(12);
        this.setMaxPower(20);
        this.setManaCost(8);
        this.setPrepareActionText(Ansi.LIGHT_GRAY + "%actor prepares to cast magic missile at %target.");
        this.setDoActionText(Ansi.LIGHT_YELLOW +  "%actor's magic missile hits %target for %number damage.");
    }
}
