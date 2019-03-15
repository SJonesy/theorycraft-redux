package Jobs;

import Abilities.Ability;
import Abilities.GroupRegeneration;

import java.util.ArrayList;
import java.util.List;

public class Priest extends Job {
    public Priest() {
        this.setName("Priest");

        this.setIntellectModifier(2);
        this.setMightModifier(2);
        this.setConstitutionModifier(-2);
        this.setResolveModifier(-1);
        this.setDexterityModifier(-1);

        List<Ability> abilities = new ArrayList<>();
        abilities.add(new GroupRegeneration());

        this.setAbilities(abilities);
    }
}
