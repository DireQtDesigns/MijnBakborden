package com.klaczynski.mijnbakborden.io;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.klaczynski.mijnbakborden.objects.Station;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;

public class InOutOperator {

    private static final String TAG = "InOutOperator";
    Activity activity;

    public InOutOperator(Activity activity) {
        this.activity = activity;
    }

    public void saveMap(HashMap<String, Station> map, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(map);
        Log.d("ListFragment", "saveArrayList: Opgeslagen json: " + json);
        editor.putString(key, json);
        editor.apply();
    }

    public HashMap<String, Station> loadMap(String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
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
        if (json == null || json.equals("null") || json.equals("[]")) {
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
            InputStream is = activity.getAssets().open("mockdata.json");
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
}
