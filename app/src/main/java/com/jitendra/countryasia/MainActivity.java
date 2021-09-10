package com.jitendra.countryasia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jitendra.countryasia.model.data.CountryItem;
import com.jitendra.countryasia.entity.EntityData;
import com.jitendra.countryasia.model.data.ApiCallInterface;
import com.jitendra.countryasia.model.data.RetrofitClientInstance;
import com.jitendra.countryasia.model.data.Language;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CountryAdapter countryAdapter;
    private RecyclerView recyclerView;
    private ArrayList<CountryItem> list;
    private ApiCallInterface apiCallInterface;
    public static final String TAG = "TAG";
    private CountryViewModel countryViewModel;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);

        list = new ArrayList<>();
        countryAdapter = new CountryAdapter(list, this);

        recyclerView.setAdapter(countryAdapter);

        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);

        countryViewModel = new ViewModelProvider(this).get(CountryViewModel.class);
        countryViewModel.getAllCountries().observe(this, this::submitList);

        apiCallInterface = RetrofitClientInstance.getRetrofitInstance().create(ApiCallInterface.class);

        fetchData();

        findViewById(R.id.delete_btn).setOnClickListener(v -> {
            countryViewModel.deleteAllCountries();
            list.clear();
            countryAdapter.notifyDataSetChanged();
        });


    }

    private void fetchData() {
        pd.setMessage("Fetching data...");
        pd.show();

        Call<List<CountryItem>> call = apiCallInterface.getCountryDetails();

        call.enqueue(new Callback<List<CountryItem>>() {
            @Override
            public void onResponse(Call<List<CountryItem>> call, Response<List<CountryItem>> response) {
                pd.dismiss();
                if(!response.isSuccessful()){
                    Log.d(TAG, "onResponse: Failed");
                    return;
                }

                Log.d(TAG, "onResponse: Successful");

                Gson gson = new Gson();

                String data = gson.toJson(response.body());

                list.clear();
                list.addAll(response.body());

                for (CountryItem item:
                        list) {

                    String borderStr = "";

                    if (item.getBorders().size() != 0) {
                        for (String border :
                                item.getBorders()) {
                            borderStr += ", " + border;
                        }
                        borderStr = borderStr.substring(2);
                    }
                    else {
                        borderStr = "No borders available";
                    }

                    String langStr = "";

                    if (item.getLanguages().size() != 0) {
                        for (Language lang :
                                item.getLanguages()) {
                            langStr += ", " + lang.getName();
                        }
                        langStr = langStr.substring(2);
                    }
                    else {
                        langStr = "No languages available";
                    }

                    EntityData entityData = new EntityData(
                            item.getName(),
                            item.getCapital(),
                            item.getFlags(),
                            item.getRegion(),
                            item.getSub_region(),
                            Integer.parseInt(item.getPopulation()),
                            borderStr,
                            langStr
                    );

                    countryViewModel.insert(entityData);
                }


            }

            @Override
            public void onFailure(Call<List<CountryItem>> call, Throwable t) {
                pd.dismiss();
                Log.d(TAG, "onFailure: "+t.getMessage());
                t.printStackTrace();
                Toast.makeText(MainActivity.this, "You are offline", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void submitList(List<EntityData> countries) {
        Log.d(TAG, "submitList: "+countries.size());
        list.clear();
        if (countries.size() != 0){

            for (EntityData item:
                    countries) {
                ArrayList<String> bordersList = new ArrayList<>();
                ArrayList<Language> languageList = new ArrayList<>();

                if (!item.getBorders().equals("No borders available")) {
                    String[] str = item.getBorders().split(", ");
                    for (String s :
                            str) {
                        bordersList.add(s);
                    }
                }

                if (!item.getLanguages().equals("No languages available")) {
                    String[] str = item.getLanguages().split(", ");
                    for (String s :
                            str) {
                        Language language = new Language(s);
                        languageList.add(language);
                    }
                }

                CountryItem countryItem = new CountryItem(
                        item.getName(),
                        item.getCapital(),
                        item.getPopulation().toString(),
                        item.getRegion(),
                        item.getSubregion(),
                        item.getFlag(),
                        bordersList,
                        languageList
                );
                list.add(countryItem);
            }

            countryAdapter.notifyDataSetChanged();

        }
    }
}