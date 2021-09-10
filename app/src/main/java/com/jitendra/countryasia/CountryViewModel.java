package com.jitendra.countryasia;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jitendra.countryasia.entity.EntityData;
import com.jitendra.countryasia.repository.CountryRepository;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {

    private CountryRepository countryRepository;
    private final LiveData<List<EntityData>> mAllCountries;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        countryRepository = new CountryRepository(application);
        mAllCountries =countryRepository.getAllCountries();
    }

    LiveData<List<EntityData>> getAllCountries(){return  mAllCountries; }

    public void insert(EntityData countryData) { countryRepository.insert(countryData); }

    public void update(EntityData countryData) {
        countryRepository.update(countryData);
    }

    public void deleteAllCountries(){
        countryRepository.deleteAllCountries();
    }
}
