package com.cooldevs.erasmuskit.ui.cities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cooldevs.erasmuskit.R;
import com.cooldevs.erasmuskit.ui.cities.model.City;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by mario on 04/03/2017
 */

class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CityViewHolder> implements View.OnClickListener {

    private ArrayList<City> citiesList;
    private Context mContext;
    private View.OnClickListener listener;
    private int visibility;

    private static final String TAG = "CitiesAdapter";

    CitiesAdapter(ArrayList<City> cities, Context context) {
        this.citiesList = cities;
        this.mContext = context;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        itemView.setOnClickListener(this);

        return new CityViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        City city = citiesList.get(position);
        holder.bindCity(city);
    }

    @Override
    public int getItemCount() {
        return citiesList.size();
    }

    private void removeAt(City city) {
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("cities").child(city.getKey());
        ref.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError,
                                   DatabaseReference databaseReference) {
                if (databaseError == null)
                    Toast.makeText(mContext, R.string.delete_city_toast, Toast.LENGTH_SHORT).show();
                else {
                    Log.d(TAG, "removeValue:onComplete, " + databaseError.getDetails());
                    Toast.makeText(mContext, R.string.error_toast, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    void setIconVisibility(int visibility) {
        this.visibility = visibility;
    }

    class CityViewHolder extends RecyclerView.ViewHolder {

        private ImageView cityIcon;
        private TextView cityName;
        private TextView cityCountry;
        private ImageView deleteIcon;

        CityViewHolder(View itemView) {
            super(itemView);

            cityIcon = (ImageView) itemView.findViewById(R.id.list_item_icon);
            cityName = (TextView) itemView.findViewById(R.id.list_item_title);
            cityCountry = (TextView) itemView.findViewById(R.id.list_item_subtitle);
            deleteIcon = (ImageView) itemView.findViewById(R.id.list_item_secondary_icon);
        }

        void bindCity(final City city) {
            cityIcon.setImageResource(R.drawable.ic_location_city_black_24dp);
            cityName.setText(city.getName());
            cityCountry.setText(mContext.getString(R.string.country_city_content, city.getCountry()));
            deleteIcon.setVisibility(visibility);
            deleteIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeAt(city);
                }
            });
        }
    }
}
