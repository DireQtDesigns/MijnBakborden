package com.klaczynski.mijnbakborden.io;

import com.klaczynski.mijnbakborden.BakbordenLijstFragment;
import com.klaczynski.mijnbakborden.objects.Platform;
import com.klaczynski.mijnbakborden.objects.Station;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class MockData {

    //99=eindbord, 123=sein, 456=juk

    @Deprecated
    public static void populateMockData() {
        BakbordenLijstFragment.stationsMap.clear();
        /*//Almelo
        HashMap<String, Platform> amlSporen = new HashMap<String, Platform>();
        ArrayList<Integer> aml4Bakborden = new ArrayList<Integer>();
        aml4Bakborden.add(4);
        aml4Bakborden.add(6);
        aml4Bakborden.add(8);
        aml4Bakborden.add(10);
        aml4Bakborden.add(12);
        ArrayList<Integer> aml2Bakborden = new ArrayList<Integer>();
        aml2Bakborden.add(99);
        ArrayList<Integer> aml1Bakborden = new ArrayList<Integer>();
        aml1Bakborden.add(123);
        ArrayList<Integer> aml5Bakborden = new ArrayList<Integer>();
        aml5Bakborden.add(123);
        ArrayList<Integer> aml3Bakborden = new ArrayList<Integer>();
        aml3Bakborden.add(456);
        amlSporen.put("4", new Platform("4", false, aml4Bakborden));
        amlSporen.put("2", new Platform("2", true, aml2Bakborden));
        amlSporen.put("1", new Platform("1", true, aml1Bakborden));
        amlSporen.put("5", new Platform("5", false, aml5Bakborden));
        amlSporen.put("3", new Platform("3", true, aml3Bakborden));
        BakbordenLijstFragment.stationsMap.put("Aml", new Station("Almelo", "Aml", amlSporen));

        //Hengelo
        HashMap<String, Platform> hglSporen = new HashMap<String, Platform>();
        ArrayList<Integer> hgl2Bakborden = new ArrayList<Integer>();
        hgl2Bakborden.add(6);
        hgl2Bakborden.add(8);
        hgl2Bakborden.add(10);
        hgl2Bakborden.add(14);
        hgl2Bakborden.add(123);
        ArrayList<Integer> hgl3Bakborden = new ArrayList<Integer>();
        hgl3Bakborden.add(6);
        hgl3Bakborden.add(8);
        hgl3Bakborden.add(10);
        hgl3Bakborden.add(14);
        hgl3Bakborden.add(123);
        hglSporen.put("2", new Platform("2", false, hgl2Bakborden));
        hglSporen.put("3",new Platform("3", true, hgl3Bakborden));
        BakbordenLijstFragment.stationsMap.put("Hgl", new Station("Hengelo", "Hgl", hglSporen));

        //Test no platforms
        BakbordenLijstFragment.stationsMap.put("Bn", new Station("Borne", "Bn", null));*/

    }
}
