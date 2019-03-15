package Jobs;

import Abilities.Ability;
import Abilities.Healing;

import java.util.ArrayList;
import java.util.List;

public class Priest extends Job {
    public Priest() {
        this.setName("Priest");

        this.setIntellectModifier(2);
        this.setMightModifier(2);
        this.setConstitutionModifier(-2);
        this.setResolveModifier(-2);
        this.setDexterityModifier(-2);

        List<Ability> abilities = new ArrayList<>();
        abilities.add(new Healing());

        this.setAbilities(abilities);
    }
}
