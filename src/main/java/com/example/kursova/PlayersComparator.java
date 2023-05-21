package com.example.kursova;

import com.example.kursova.microobjects.Poor;

import java.util.Comparator;

public class PlayersComparator implements Comparator<Poor> {

    @Override
    public int compare(Poor o1, Poor o2) {
        return o1.compareTo(o2);
    }
}
