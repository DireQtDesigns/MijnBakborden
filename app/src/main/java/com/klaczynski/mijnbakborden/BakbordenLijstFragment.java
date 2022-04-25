package com.klaczynski.mijnbakborden;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.klaczynski.mijnbakborden.databinding.LijstFragmentBinding;
import com.klaczynski.mijnbakborden.objects.Platform;
import com.klaczynski.mijnbakborden.objects.Station;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class BakbordenLijstFragment extends Fragment {

    private static final String TAG = "BakbordenLijstFragment";
    private LijstFragmentBinding binding;
    public static ArrayList<Station> stations;
    public static HashMap<String, Station> stationsMap;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.d(TAG, "onCreateView: called");
        binding = LijstFragmentBinding.inflate(inflater, container, false);
        stations = new ArrayList<Station>();
        stationsMap = new HashMap<String, Station>();
        //Stationsdata laden / creeren indien niet aanwezig in opslag
        try {
            stationsMap = loadMap(Definitions.STATION_LIST_KEY);
            for (int i = 0; i < stationsMap.size(); i++) {
                stations.add(stationsMap.get(stationsMap.keySet().toArray()[i]));
            }
            if (stationsMap.size() == 0) stations = new ArrayList<Station>();
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: Exceptie tijdens laden lijst, creeert nieuwe..");
            Log.e(TAG, Log.getStackTraceString(e));
            Toast toast = Toast.makeText(getContext(),
                    "Fout opgetreden tijdens inladen data, nieuwe lijst aangemaakt!",
                    Toast.LENGTH_SHORT);

            toast.show();
        }

        StationAdapter adapter = new StationAdapter(this.getContext(), stations);
        ListView lijst = binding.lijst;
        lijst.setAdapter(adapter);
        lijst.setClickable(true);

        syncData(false);
        saveMap(stationsMap, Definitions.STATION_LIST_KEY);
        return binding.getRoot();
    }


    public void saveMap(HashMap<String, Station> map, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        Log.d("ListFragment", "saveArrayList: Opgeslagen json: " + json);
        editor.putString(key, json);
        editor.apply();
    }

    public HashMap<String, Station> loadMap(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Log.d(TAG, "loadMap: Geladen json: " + json);
        if (json == null || json.equals("null") || json.equals("[]")) {
            Log.d(TAG, "loadArrayList: Geen data aanwezig, genereert nieuwe lijst..");
            return new HashMap<String, Station>();
        }
        Type type = new TypeToken<HashMap<String, Station>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public HashMap<String, Station> loadMapJson() {
        Gson gson = new Gson();
        String json = loadJSONFromAsset();
        Log.d(TAG, "loadMap: Geladen json: " + json);
        if(json == null || json.equals("null") || json.equals("[]")) {
            Log.d(TAG, "loadArrayList: Geen data aanwezig, genereert nieuwe lijst..");
            return new HashMap<String, Station>();
        }
        Type type = new TypeToken<HashMap<String, Station>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("mockdata.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void syncData(boolean updateView) {
        Log.d(TAG, "syncData: called");
        stations = new ArrayList<Station>();
        if (stationsMap.size() != stations.size()) {
            for (int i = 0; i < stationsMap.size(); i++) {
                stations.add(stationsMap.get(stationsMap.keySet().toArray()[i]));
            }
        }
        if (updateView) {
            ListView lijst = getView().findViewById(R.id.lijst);
            ((ArrayAdapter) lijst.getAdapter()).clear();
            ((ArrayAdapter) lijst.getAdapter()).addAll(stations);
            ((ArrayAdapter) lijst.getAdapter()).notifyDataSetChanged();
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        Log.d(TAG, "onDestroyView: called");
    }

    @Override
    public void onStop() {
        saveMap(stationsMap, Definitions.STATION_LIST_KEY);
        super.onStop();
        Log.d(TAG, "onStop: called");
    }

    public void onResume() {
        Log.d(TAG, "onResume: called");
        saveMap(stationsMap, Definitions.STATION_LIST_KEY);
        super.onResume();
        syncData(true);
    }

    public static boolean stationExists(String abbreviation) {
        for (int i = 0; i < stationsMap.size(); i++) {
            if(stationsMap.get(stationsMap.keySet().toArray()[i]).getAbbreviation().equals(abbreviation)) return true;
        }
        return false;
    }
}