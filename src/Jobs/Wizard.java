package Jobs;

import Abilities.Ability;
import Abilities.MagicMissile;

import java.util.ArrayList;
import java.util.List;

public class Wizard extends Job {
    public Wizard () {
        this.setName("Wizard");

        this.setIntellectModifier(4);
        this.setMightModifier(4);
        this.setConstitutionModifier(-4);
        this.setResolveModifier(-4);
        this.setDexterityModifier(-4);

        List<Ability> abilities = new ArrayList<>();
        abilities.add(new MagicMissile());

        this.setAbilities(abilities);
    }
}
