package com.jitendra.countryasia;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYouListener;
import com.jitendra.countryasia.entity.EntityData;
import com.jitendra.countryasia.model.data.CountryItem;
import com.jitendra.countryasia.model.data.Language;

import java.util.ArrayList;
import java.util.List;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {

    ArrayList<CountryItem> items;
    Context context;

    public CountryAdapter(ArrayList<CountryItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.county_data_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView countryName, countryCapital, regionTxt, subRegion, population, borders, language;
        private final ImageView countryFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.country_name_data);
            countryFlag = itemView.findViewById(R.id.countryImg);
            countryCapital = itemView.findViewById(R.id.country_capital_data);
            regionTxt = itemView.findViewById(R.id.region_txt);
            subRegion = itemView.findViewById(R.id.sub_region_txt);
            population = itemView.findViewById(R.id.population_txt);
            borders = itemView.findViewById(R.id.borders_txt);
            language = itemView.findViewById(R.id.lang_txt);
        }

        public void bind(CountryItem countryItem) {
            countryName.setText(countryItem.getName());
            countryCapital.setText(countryItem.getCapital());
            regionTxt.setText(countryItem.getRegion());
            subRegion.setText(countryItem.getSub_region());
            population.setText(countryItem.getPopulation());

            if (countryItem.getBorders().size() != 0) {
                String borderStr = "";
                for (String border :
                        countryItem.getBorders()) {
                    borderStr += ", " + border;
                }

                borderStr = borderStr.substring(2);

                borders.setText(borderStr);
            }
            else {
                borders.setText("No borders available");
            }

            if (countryItem.getLanguages().size() != 0) {
                String langStr = "";
                for (Language lang :
                        countryItem.getLanguages()) {
                    langStr += ", " + lang.getName();
                }

                langStr = langStr.substring(2);

                language.setText(langStr);
            }
            else {
                language.setText("No languages available");
            }

            GlideToVectorYou
                    .init()
                    .with(context)
                    .withListener(new GlideToVectorYouListener() {
                        @Override
                        public void onLoadFailed() {
//                            Toast.makeText(context, "Load failed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResourceReady() {
//                            Toast.makeText(context, "Image ready", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .load(Uri.parse(countryItem.getFlags()), countryFlag);
        }
    }
}
