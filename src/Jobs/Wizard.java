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
        this.setConstitutionModifier(-3);
        this.setResolveModifier(-2);
        this.setDexterityModifier(-3);

        List<Ability> abilities = new ArrayList<>();
        abilities.add(new MagicMissile());

        this.setAbilities(abilities);
    }
}
