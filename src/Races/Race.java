package Races;

import Abilities.Ability;

import java.util.ArrayList;
import java.util.List;

public abstract class Race {
    private String name = "";

    private int mightModifier        = 0;
    private int intellectModifier    = 0;
    private int constitutionModifier = 0;
    private int dexterityModifier    = 0;
    private int perceptionModifier   = 0;
    private int resolveModifier      = 0;
    private float iceResistModifier    = 1.0f;
    private float fireResistModifier   = 1.0f;
    private float poisonResistModifier = 1.0f;
    private float holyResistModifier   = 1.0f;
    private float unholyResistModifier = 1.0f;
    private float waterResistModifier  = 1.0f;
    private float airResistModifier    = 1.0f;
    private float earthResistModifier  = 1.0f;

    private List<Ability> abilities = new ArrayList<>();

    public static Race GetRace(String raceName) {
        switch (raceName.toUpperCase()) {
            case "HUMAN":
                return new Human();
            case "ELF":
                return new Elf();
            default:
                return null;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMightModifier() {
        return mightModifier;
    }

    public void setMightModifier(int mightModifier) {
        this.mightModifier = mightModifier;
    }

    public int getIntellectModifier() {
        return intellectModifier;
    }

    public void setIntellectModifier(int intellectModifier) {
        this.intellectModifier = intellectModifier;
    }

    public int getConstitutionModifier() {
        return constitutionModifier;
    }

    public void setConstitutionModifier(int constitutionModifier) {
        this.constitutionModifier = constitutionModifier;
    }

    public int getDexterityModifier() {
        return dexterityModifier;
    }

    public void setDexterityModifier(int dexterityModifier) {
        this.dexterityModifier = dexterityModifier;
    }

    public int getPerceptionModifier() {
        return perceptionModifier;
    }

    public void setPerceptionModifier(int perceptionModifier) {
        this.perceptionModifier = perceptionModifier;
    }

    public int getResolveModifier() {
        return resolveModifier;
    }

    public void setResolveModifier(int resolveModifier) {
        this.resolveModifier = resolveModifier;
    }

    public float getIceResistModifier() {
        return iceResistModifier;
    }

    public void setIceResistModifier(float iceResistModifier) {
        this.iceResistModifier = iceResistModifier;
    }

    public float getFireResistModifier() {
        return fireResistModifier;
    }

    public void setFireResistModifier(float fireResistModifier) {
        this.fireResistModifier = fireResistModifier;
    }

    public float getPoisonResistModifier() {
        return poisonResistModifier;
    }

    public void setPoisonResistModifier(float poisonResistModifier) {
        this.poisonResistModifier = poisonResistModifier;
    }

    public float getHolyResistModifier() {
        return holyResistModifier;
    }

    public void setHolyResistModifier(float holyResistModifier) {
        this.holyResistModifier = holyResistModifier;
    }

    public float getUnholyResistModifier() {
        return unholyResistModifier;
    }

    public void setUnholyResistModifier(float unholyResistModifier) {
        this.unholyResistModifier = unholyResistModifier;
    }

    public float getWaterResistModifier() {
        return waterResistModifier;
    }

    public void setWaterResistModifier(float waterResistModifier) {
        this.waterResistModifier = waterResistModifier;
    }

    public float getAirResistModifier() {
        return airResistModifier;
    }

    public void setAirResistModifier(float airResistModifier) {
        this.airResistModifier = airResistModifier;
    }

    public float getEarthResistModifier() {
        return earthResistModifier;
    }

    public void setEarthResistModifier(float earthResistModifier) {
        this.earthResistModifier = earthResistModifier;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }
}
