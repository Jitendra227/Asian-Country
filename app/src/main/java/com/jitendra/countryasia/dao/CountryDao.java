package com.jitendra.countryasia.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.jitendra.countryasia.entity.EntityData;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(EntityData data);

    @Update
    void update(EntityData data);

    @Delete
    void delete(EntityData data);

    @Query("DELETE FROM entity_country_data")
    void deleteAllCountries();

    @Query("SELECT * FROM  entity_country_data ORDER BY country ASC")
    LiveData<List<EntityData>> getAllCountries();

}
