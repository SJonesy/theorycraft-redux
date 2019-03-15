package Races;

public class Ogre extends Race {
    public Ogre () {
        this.setName("Ogre");

        this.setMightModifier(4);
        this.setResolveModifier(4);
        this.setConstitutionModifier(4);
        this.setIntellectModifier(-4);
        this.setDexterityModifier(-4);
        this.setPerceptionModifier(-2);
    }
}
