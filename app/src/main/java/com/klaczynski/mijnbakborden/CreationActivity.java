package com.klaczynski.mijnbakborden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButton;
import com.klaczynski.mijnbakborden.objects.Platform;
import com.klaczynski.mijnbakborden.objects.Station;

import java.util.ArrayList;
import java.util.HashMap;

public class CreationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation_layout);

        CheckBox bord1 = findViewById(R.id.radioButton1);
        CheckBox bord2 = findViewById(R.id.radioButton2);
        CheckBox bord3 = findViewById(R.id.radioButton3);
        CheckBox bord4 = findViewById(R.id.radioButton4);
        CheckBox bord5 = findViewById(R.id.radioButton5);
        CheckBox bord6 = findViewById(R.id.radioButton6);
        CheckBox bord7 = findViewById(R.id.radioButton7);
        CheckBox bord8 = findViewById(R.id.radioButton8);
        CheckBox bord9 = findViewById(R.id.radioButton9);
        CheckBox bord10 = findViewById(R.id.radioButton10);
        CheckBox bord11 = findViewById(R.id.radioButton11);
        CheckBox bord12 = findViewById(R.id.radioButton12);
        CheckBox bord13 = findViewById(R.id.radioButton13);
        CheckBox bord14 = findViewById(R.id.radioButton14);
        CheckBox bord15 = findViewById(R.id.radioButton15);
        CheckBox bord16 = findViewById(R.id.radioButton16);
        CheckBox bordE = findViewById(R.id.radioButtonE);
        CheckBox bordS = findViewById(R.id.radioButtonS);
        CheckBox bordJ = findViewById(R.id.radioButtonJ);
        CheckBox bordG = findViewById(R.id.radioButtonG);
        EditText stationsNaam = findViewById(R.id.editTextStationName);
        EditText stationsAfkorting = findViewById(R.id.editTextStationAbbrev);
        EditText perronNummer = findViewById(R.id.editTextPlatformNr);
        ToggleButton rijRichting = findViewById(R.id.directionToggle);
        Button toevoegenButton = findViewById(R.id.buttonAdd);

        bordG.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    bord1.setChecked(false);
                    bord2.setChecked(false);
                    bord3.setChecked(false);
                    bord4.setChecked(false);
                    bord5.setChecked(false);
                    bord6.setChecked(false);
                    bord7.setChecked(false);
                    bord8.setChecked(false);
                    bord9.setChecked(false);
                    bord10.setChecked(false);
                    bord11.setChecked(false);
                    bord12.setChecked(false);
                    bord13.setChecked(false);
                    bord14.setChecked(false);
                    bord15.setChecked(false);
                    bord16.setChecked(false);
                    bordE.setChecked(false);
                    bordS.setChecked(false);
                    bordJ.setChecked(false);
                }
            }
        });
        bordS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                bordG.setChecked(false);
                if(isChecked && bordJ.isChecked()) {
                    bordJ.setChecked(false);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Zowel sein als juk samen onmogelijk!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        bordJ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                bordG.setChecked(false);
                if(isChecked && bordS.isChecked()) {
                    bordS.setChecked(false);
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Zowel sein als juk samen onmogelijk!",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        toevoegenButton.setOnClickListener(view -> {
            if (stationsNaam.getText().toString().equals("") ||
                    stationsAfkorting.getText().toString().equals("") || perronNummer.getText().toString().equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Geen geldige selectie gemaaakt of er is een fout opgetreden!",
                        Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            String abbrev = stationsAfkorting.getText().toString();
            String abbrevCap = abbrev.substring(0,1).toUpperCase() + abbrev.substring(1).toLowerCase();
            ArrayList<Integer> bakborden = new ArrayList<Integer>();
            if(bord1.isChecked()) bakborden.add(1);
            if(bord2.isChecked()) bakborden.add(2);
            if(bord3.isChecked()) bakborden.add(3);
            if(bord4.isChecked()) bakborden.add(4);
            if(bord5.isChecked()) bakborden.add(5);
            if(bord6.isChecked()) bakborden.add(6);
            if(bord7.isChecked()) bakborden.add(7);
            if(bord8.isChecked()) bakborden.add(8);
            if(bord9.isChecked()) bakborden.add(9);
            if(bord10.isChecked()) bakborden.add(10);
            if(bord11.isChecked()) bakborden.add(11);
            if(bord12.isChecked()) bakborden.add(12);
            if(bord13.isChecked()) bakborden.add(13);
            if(bord14.isChecked()) bakborden.add(14);
            if(bord15.isChecked()) bakborden.add(15);
            if(bord16.isChecked()) bakborden.add(16);
            if(bordE.isChecked()) bakborden.add(99);
            if(bordS.isChecked()) bakborden.add(123);
            if (bordJ.isChecked()) bakborden.add(456);

            if (bakborden.size() == 0 || bakborden == null) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Geen geldige selectie gemaakt of er is een fout opgetreden!",
                        Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            String directionalName = perronNummer.getText().toString() + ":" + (rijRichting.isChecked() ? "ab" : "ba");
            Platform platform = new Platform(directionalName, perronNummer.getText().toString(), rijRichting.isChecked(), bakborden);
            if (BakbordenLijstFragment.stationExists(abbrevCap)) {
                Station station = BakbordenLijstFragment.stationsMap.get(abbrevCap);
                if (station.getPlatforms() == null)
                    station.setPlatforms(new HashMap<String, Platform>());
                station.getPlatforms().put(directionalName, platform);
                BakbordenLijstFragment.stationsMap.put(abbrevCap, station);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Station " + abbrevCap + " is ge-updatet!",
                        Toast.LENGTH_SHORT);
                toast.show();
                finish();
            } else {
                HashMap<String, Platform> newPlatforms = new HashMap<String, Platform>();
                newPlatforms.put(directionalName, platform);
                Station newStation = new Station(stationsNaam.getText().toString(), abbrevCap, newPlatforms);
                BakbordenLijstFragment.stationsMap.put(abbrevCap, newStation);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Nieuw station "+abbrevCap+" is toegevoegd!",
                        Toast.LENGTH_SHORT);
                toast.show();
                finish();
            }

        });
    }
}