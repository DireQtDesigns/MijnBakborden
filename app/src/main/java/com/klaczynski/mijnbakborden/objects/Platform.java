package com.klaczynski.mijnbakborden.objects;

import java.util.ArrayList;

public class Platform {

    private String name;
    private boolean a_to_b;
    private ArrayList<Integer> bakborden;

    public Platform(String name, boolean a_to_b, ArrayList<Integer> bakborden) {
        this.name = name;
        this.a_to_b = a_to_b;
        this.bakborden = bakborden;
    }

    public String getName() {
        return name;
    }

    public boolean isA_to_b() {
        return a_to_b;
    }

    public ArrayList<Integer> getBakborden() {
        return bakborden;
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < getBakborden().size(); i++) {
            if (i != getBakborden().size() - 1) {
                returnString += getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk") + ", ";
            } else {
                returnString += getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk");
            }
        }
        return returnString;
    }

    public String bakbordenString() {
        String returnString = "";
        for (int i = 0; i < getBakborden().size(); i++) {
            if (i != getBakborden().size() - 1) {
                returnString += getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk") + ", ";
            } else {
                returnString += getBakborden().get(i).toString().replace("99", "Eindbord").replace("123", "Sein").replace("456", "Stootjuk");
            }
        }
        return returnString;
    }

    //TODO Multi-directional platforms
}
