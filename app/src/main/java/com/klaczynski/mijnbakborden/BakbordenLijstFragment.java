package com.klaczynski.mijnbakborden;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.klaczynski.mijnbakborden.databinding.LijstFragmentBinding;
import com.klaczynski.mijnbakborden.io.InOutOperator;
import com.klaczynski.mijnbakborden.misc.Definitions;
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
    InOutOperator io;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.d(TAG, "onCreateView: called");
        binding = LijstFragmentBinding.inflate(inflater, container, false);
        io = new InOutOperator(getActivity());
        stations = new ArrayList<Station>();
        stationsMap = new HashMap<String, Station>();
        //Stationsdata laden / creeren indien niet aanwezig in opslag
        try {
            stationsMap = io.loadMap(Definitions.STATION_LIST_KEY);
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
        io.saveMap(stationsMap, Definitions.STATION_LIST_KEY);
        return binding.getRoot();
    }




    public void syncData(boolean updateView) {
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
    }

    @Override
    public void onStop() {
        io.saveMap(stationsMap, Definitions.STATION_LIST_KEY);
        super.onStop();
    }

    public void onResume() {
        io.saveMap(stationsMap, Definitions.STATION_LIST_KEY);
        super.onResume();
        syncData(true);
    }

    public static boolean stationExists(String abbreviation) {
        for (int i = 0; i < stationsMap.size(); i++) {
            if (stationsMap.get(stationsMap.keySet().toArray()[i]).getAbbreviation().equals(abbreviation))
                return true;
        }
        return false;
    }
}