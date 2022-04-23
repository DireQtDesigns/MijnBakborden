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
}
