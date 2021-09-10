package com.jitendra.countryasia.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jitendra.countryasia.dao.CountryDao;
import com.jitendra.countryasia.db.CountryRoomDatabase;
import com.jitendra.countryasia.entity.EntityData;

import java.util.List;

public class CountryRepository {

    private CountryDao countryDao;
    private LiveData<List<EntityData>> mAllCountries;

    public CountryRepository(Application application){
        CountryRoomDatabase db = CountryRoomDatabase.getDatabase(application);
        countryDao = db.countryDao();
        mAllCountries = countryDao.getAllCountries();
    }

    public LiveData<List<EntityData>> getAllCountries(){
        return mAllCountries;
    }

    public void insert(EntityData countryData) {
        CountryRoomDatabase.databaseWriteExecutor.execute(() -> {
            countryDao.insert(countryData);
        });
    }

    public void update(EntityData countryData){
        CountryRoomDatabase.databaseWriteExecutor.execute(() -> {
            countryDao.update(countryData);
        });
    }

    public void deleteAllCountries(){
        CountryRoomDatabase.databaseWriteExecutor.execute(() ->{
            countryDao.deleteAllCountries();
        });
    }


}
