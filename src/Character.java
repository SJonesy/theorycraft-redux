import Abilities.Ability;
import Jobs.Job;
import Races.Race;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class Character {
    public String name;
    public Race race;
    public Job job;
    public Mind mind;

    public int party;

    public List<Ability> abilities = new ArrayList<>();

    public int maxHitPoints = 150;
    public int hitPoints   = 150;
    public int maxMana     = 100;
    public int mana        = 100;

    // Stats
    public int might        = 10;
    public int constitution = 10;
    public int dexterity    = 10;
    public int perception   = 10;
    public int intellect    = 10;
    public int resolve      = 10;

    // Resists
    public float iceResist    = 1.0f;
    public float fireResist   = 1.0f;
    public float poisonResist = 1.0f;
    public float holyResist   = 1.0f;
    public float unholyResist = 1.0f;
    public float waterResist  = 1.0f;
    public float airResist    = 1.0f;
    public float earthResist  = 1.0f;

    // Combat Variables
    public boolean isCasting      = false;
    public boolean isUsingAbility = false;
    public boolean isAlive        = true;
    public boolean isPoisoned     = false;
    public boolean isRegenerating = false;
    public boolean isRaging       = false;
    public int regenValue         = 0;
    public int poisonValue        = 0;
    public int recoveryTurnsRemaining = 0;

    public Character (String data) {
        // Load data from file
        String[] characterData = data.split(",");
        this.name = characterData[0];
        String raceName = characterData[1].toUpperCase();
        String jobName = characterData[2].toUpperCase();
        for (int i=3; i <= 5; i++) {
            Ability ability = Ability.GetAbility(characterData[i].toUpperCase());
            if (!ability.isClassOnly()) {
                this.addAbility(ability);
            }
        }

        applyRace(raceName);
        applyJob(jobName);
        this.mind = Mind.GetMind("GENERAL");

        // hitpointsFormula = (float) this.constitution / modifier * constant;
        // Decreasing the modifier makes everyone beefier, increasing it makes them weaker
        float hitpointsFormula = (float) this.constitution / 60f * 1000f;
        this.maxHitPoints = Math.round(hitpointsFormula);
        this.hitPoints = maxHitPoints;
        this.maxMana = this.intellect * 10;
        this.mana = maxMana;
    }

    private void applyRace(String raceName) {
        this.race = Race.GetRace(raceName);
        this.might        += this.race.getMightModifier();
        this.intellect    += this.race.getIntellectModifier();
        this.constitution += this.race.getConstitutionModifier();
        this.dexterity    += this.race.getDexterityModifier();
        this.perception   += this.race.getPerceptionModifier();
        this.resolve      += this.race.getResolveModifier();
        this.iceResist    *= this.race.getIceResistModifier();
        this.fireResist   *= this.race.getFireResistModifier();
        this.poisonResist *= this.race.getPoisonResistModifier();
        this.holyResist   *= this.race.getHolyResistModifier();
        this.unholyResist *= this.race.getUnholyResistModifier();
        this.waterResist  *= this.race.getWaterResistModifier();
        this.airResist    *= this.race.getAirResistModifier();
        this.earthResist  *= this.race.getEarthResistModifier();

        for (Ability ability : this.race.getAbilities())
            this.addAbility(ability);
    }

    private void applyJob(String jobName) {
        this.job = Job.GetJob(jobName);
        this.might        += this.job.getMightModifier();
        this.intellect    += this.job.getIntellectModifier();
        this.constitution += this.job.getConstitutionModifier();
        this.dexterity    += this.job.getDexterityModifier();
        this.perception   += this.job.getPerceptionModifier();
        this.resolve      += this.job.getResolveModifier();
        this.iceResist    *= this.job.getIceResistModifier();
        this.fireResist   *= this.job.getFireResistModifier();
        this.poisonResist *= this.job.getPoisonResistModifier();
        this.holyResist   *= this.job.getHolyResistModifier();
        this.unholyResist *= this.job.getUnholyResistModifier();
        this.waterResist  *= this.job.getWaterResistModifier();
        this.airResist    *= this.job.getAirResistModifier();
        this.earthResist  *= this.job.getEarthResistModifier();

        for (Ability ability : this.job.getAbilities())
            this.addAbility(ability);
    }

    private void applyAbility(Ability ability) {
        this.might        += ability.getMightModifier();
        this.intellect    += ability.getIntellectModifier();
        this.constitution += ability.getConstitutionModifier();
        this.dexterity    += ability.getDexterityModifier();
        this.perception   += ability.getPerceptionModifier();
        this.resolve      += ability.getResolveModifier();
        this.iceResist    *= ability.getIceResistModifier();
        this.fireResist   *= ability.getFireResistModifier();
        this.poisonResist *= ability.getPoisonResistModifier();
        this.holyResist   *= ability.getHolyResistModifier();
        this.unholyResist *= ability.getUnholyResistModifier();
        this.waterResist  *= ability.getWaterResistModifier();
        this.airResist    *= ability.getAirResistModifier();
        this.earthResist  *= ability.getEarthResistModifier();
    }

    private void addAbility(Ability ability) {
        if (!this.abilities.contains(ability)) {
            this.abilities.add(ability);
            this.applyAbility(ability);
        }
    }
}

