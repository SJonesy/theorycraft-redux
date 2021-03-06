package Races;

public class Human extends Race {
    public Human () {
        this.setName("Human");

        this.setMightModifier(1);
        this.setIntellectModifier(1);
        this.setConstitutionModifier(1);
        this.setDexterityModifier(1);
        this.setPerceptionModifier(1);
        this.setResolveModifier(1);
        this.setIceResistModifier(1.1f);
        this.setFireResistModifier(1.1f);
        this.setPoisonResistModifier(1.1f);
        this.setHolyResistModifier(1.1f);
        this.setUnholyResistModifier(1.1f);
        this.setWaterResistModifier(1.1f);
        this.setAirResistModifier(1.1f);
        this.setEarthResistModifier(1.1f);
    }
}
