package com.klaczynski.mijnbakborden.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class Station {

    private String fullName;
    private String abbreviation;
    private HashMap<String, Platform> platforms;

    public Station(String fName, String abbrev, HashMap<String, Platform> pltfrms) {
        this.fullName = fName;
        this.abbreviation = abbrev;
        this.platforms = pltfrms;

    }

    public String getFullName() {
        return fullName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public HashMap<String, Platform> getPlatforms() {
        return platforms;
    }

    public void addEmptyPlatform(String platformName, boolean a_to_b) {
        String directionalName = platformName + ":" + (a_to_b ? "ab" : "ba");
        if (platforms == null) {
            this.platforms = new HashMap<String, Platform>();
        }
        platforms.put(platformName, new Platform(directionalName, platformName, a_to_b, new ArrayList<Integer>()));
    }


    public void setPlatforms(HashMap<String, Platform> platforms) {
        this.platforms = platforms;
    }
}
