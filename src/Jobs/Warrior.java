package Jobs;

public class Warrior extends Job {
    public Warrior() {
        this.setName("Warrior");
        this.setMightModifier(2);
        this.setConstitutionModifier(4);
        this.setResolveModifier(4);
        this.setDexterityModifier(-2);
        this.setIntellectModifier(-2);
    }
}
