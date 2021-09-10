package com.jitendra.countryasia.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CountryItem implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("capital")
    private String capital;

    @SerializedName("population")
    private String population;

    @SerializedName("region")
    private String region;

    @SerializedName("subregion")
    private String sub_region;

    @SerializedName("flag")
    private String flags;

    @SerializedName("borders")
    private ArrayList<String> borders;

    @SerializedName("languages")
    private ArrayList<Language> languages;

    public CountryItem(String name, String capital, String population, String region, String sub_region, String flags, ArrayList<String> borders, ArrayList<Language> languages) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.region = region;
        this.sub_region = sub_region;
        this.flags = flags;
        this.borders = borders;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getPopulation() {
        return population;
    }

    public String getRegion() {
        return region;
    }

    public String getSub_region() {
        return sub_region;
    }

    public String getFlags() {
        return flags;
    }

    public ArrayList<String> getBorders() {
        return borders;
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }
}
