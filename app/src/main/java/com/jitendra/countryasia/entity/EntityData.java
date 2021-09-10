package com.jitendra.countryasia.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.jitendra.countryasia.model.data.Language;
import java.util.ArrayList;

@Entity(tableName = "entity_country_data")
public class EntityData {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "country")
    private String name;
    private String capital;
    private String flag;
    private String region;
    private String subregion;
    private Integer population;
    private String borders;
    private String languages;

    public EntityData(@NonNull String name, String capital, String flag, String region, String subregion, Integer population, String borders, String languages) {
        this.name = name;
        this.capital = capital;
        this.flag = flag;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.borders = borders;
        this.languages = languages;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getBorders() {
        return borders;
    }

    public void setBorders(String borders) {
        this.borders = borders;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }
}
