package Abilities;

public abstract class Ability {
    private String name;
    private AbilityType abilityType;
    private int recovery;
    private int delay;
    private int maxPower;
    private int minPower;
    private int manaCost;
    private int mightModifier        = 0;
    private int intellectModifier    = 0;
    private int constitutionModifier = 0;
    private int dexterityModifier    = 0;
    private int perceptionModifier   = 0;
    private int resolveModifier      = 0;
    private float iceResistModifier    = 1.0f;
    private float fireResistModifier   = 1.0f;
    private float poisonResistModifier = 1.0f;
    private float holyResistModifier   = 1.0f;
    private float unholyResistModifier = 1.0f;
    private float waterResistModifier  = 1.0f;
    private float airResistModifier    = 1.0f;
    private float earthResistModifier  = 1.0f;
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
            case "LESSERHEALING":
                return new LesserHealing();
            case "MAGICMISSILE":
                return new MagicMissile();
            case "MIGHTY":
                return new Mighty();
            case "DAGGER":
                return new Dagger();
            case "HEALING":
                return new Healing();
            case "GROUPREGENERATION":
                return new GroupRegeneration();
            case "POISON":
                return new Poison();
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

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getMightModifier() {
        return mightModifier;
    }

    public void setMightModifier(int mightModifier) {
        this.mightModifier = mightModifier;
    }

    public int getIntellectModifier() {
        return intellectModifier;
    }

    public void setIntellectModifier(int intellectModifier) {
        this.intellectModifier = intellectModifier;
    }

    public int getConstitutionModifier() {
        return constitutionModifier;
    }

    public void setConstitutionModifier(int constitutionModifier) {
        this.constitutionModifier = constitutionModifier;
    }

    public int getDexterityModifier() {
        return dexterityModifier;
    }

    public void setDexterityModifier(int dexterityModifier) {
        this.dexterityModifier = dexterityModifier;
    }

    public int getPerceptionModifier() {
        return perceptionModifier;
    }

    public void setPerceptionModifier(int perceptionModifier) {
        this.perceptionModifier = perceptionModifier;
    }

    public int getResolveModifier() {
        return resolveModifier;
    }

    public void setResolveModifier(int resolveModifier) {
        this.resolveModifier = resolveModifier;
    }

    public float getIceResistModifier() {
        return iceResistModifier;
    }

    public void setIceResistModifier(float iceResistModifier) {
        this.iceResistModifier = iceResistModifier;
    }

    public float getFireResistModifier() {
        return fireResistModifier;
    }

    public void setFireResistModifier(float fireResistModifier) {
        this.fireResistModifier = fireResistModifier;
    }

    public float getPoisonResistModifier() {
        return poisonResistModifier;
    }

    public void setPoisonResistModifier(float poisonResistModifier) {
        this.poisonResistModifier = poisonResistModifier;
    }

    public float getHolyResistModifier() {
        return holyResistModifier;
    }

    public void setHolyResistModifier(float holyResistModifier) {
        this.holyResistModifier = holyResistModifier;
    }

    public float getUnholyResistModifier() {
        return unholyResistModifier;
    }

    public void setUnholyResistModifier(float unholyResistModifier) {
        this.unholyResistModifier = unholyResistModifier;
    }

    public float getWaterResistModifier() {
        return waterResistModifier;
    }

    public void setWaterResistModifier(float waterResistModifier) {
        this.waterResistModifier = waterResistModifier;
    }

    public float getAirResistModifier() {
        return airResistModifier;
    }

    public void setAirResistModifier(float airResistModifier) {
        this.airResistModifier = airResistModifier;
    }

    public float getEarthResistModifier() {
        return earthResistModifier;
    }

    public void setEarthResistModifier(float earthResistModifier) {
        this.earthResistModifier = earthResistModifier;
    }
}

