package com.jitendra.countryasia.model.data;

import com.jitendra.countryasia.model.data.CountryItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCallInterface {

    @GET("region/asia")
    Call<List<CountryItem>> getCountryDetails() ;

}
