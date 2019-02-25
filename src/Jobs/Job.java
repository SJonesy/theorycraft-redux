package Jobs;

import Abilities.Ability;

import java.util.ArrayList;
import java.util.List;

public abstract class Job {
    public String name = "";

    public int mightModifier        = 0;
    public int intellectModifier    = 0;
    public int constitutionModifier = 0;
    public int dexterityModifier    = 0;
    public int perceptionModifier   = 0;
    public int resolveModifier      = 0;
    public float iceResistModifier    = 1.0f;
    public float fireResistModifier   = 1.0f;
    public float poisonResistModifier = 1.0f;
    public float holyResistModifier   = 1.0f;
    public float unholyResistModifier = 1.0f;
    public float waterResistModifier  = 1.0f;
    public float airResistModifier    = 1.0f;
    public float earthResistModifier  = 1.0f;

    public List<Ability> abilities = new ArrayList<>();

    public static Job GetJob(String raceName) {
        switch (raceName.toUpperCase()) {
            case "WARRIOR":
                return new Warrior();
            default:
                return null;
        }

    }
}
