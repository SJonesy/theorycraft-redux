package Races;

public class Dwarf extends Race {
    public Dwarf () {
        this.setName("Dwarf");

        this.setMightModifier(0);
        this.setIntellectModifier(-1);
        this.setConstitutionModifier(2);
        this.setDexterityModifier(-1);
        this.setPerceptionModifier(0);
        this.setResolveModifier(2);
        this.setIceResistModifier(1.6f);
        this.setFireResistModifier(1.6f);
        this.setPoisonResistModifier(1.6f);
        this.setEarthResistModifier(1.6f);
    }
}
