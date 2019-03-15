package Jobs;

import Abilities.Ability;
import Abilities.Rage;

import java.util.ArrayList;
import java.util.List;

public class Barbarian extends Job {
    public Barbarian() {
        this.setName("Barbarian");
        this.setMightModifier(2);
        this.setConstitutionModifier(2);
        this.setResolveModifier(2);
        this.setDexterityModifier(-1);
        this.setIntellectModifier(-4);

        List<Ability> abilities = new ArrayList<>();
        abilities.add(new Rage());

        this.setAbilities(abilities);
    }
}
