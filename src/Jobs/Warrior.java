package Jobs;

public class Warrior extends Job {
    public Warrior() {
        this.setName("Warrior");
        this.setMightModifier(2);
        this.setConstitutionModifier(2);
        this.setResolveModifier(2);
        this.setDexterityModifier(-1);
        this.setIntellectModifier(-2);
    }
}
