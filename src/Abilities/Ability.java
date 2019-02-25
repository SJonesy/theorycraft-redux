package Abilities;

public abstract class Ability {
    private String name;
    private AbilityType abilityType;
    private int recovery;
    private int delay;
    private int maxPower;
    private int minPower;
    private String prepareActionText;
    private String doActionText;

    public static Ability GetAbility(String abilityName) {
        switch (abilityName.toUpperCase()) {
            case "SWORD":
                return new Sword();
            case "SHIELD":
                return new Shield();
            case "STOUT":
                return new Stout();
            default:
                return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public int getRecovery() {
        return recovery;
    }

    public void setRecovery(int recovery) {
        this.recovery = recovery;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getMinPower() {
        return minPower;
    }

    public void setMinPower(int minPower) {
        this.minPower = minPower;
    }

    public String getPrepareActionText() {
        return prepareActionText;
    }

    public void setPrepareActionText(String prepareActionText) {
        this.prepareActionText = prepareActionText;
    }

    public String getDoActionText() {
        return doActionText;
    }

    public void setDoActionText(String doActionText) {
        this.doActionText = doActionText;
    }
}
