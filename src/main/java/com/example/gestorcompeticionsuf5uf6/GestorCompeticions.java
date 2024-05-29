package com.example.gestorcompeticionsuf5uf6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorCompeticions {
    private static final List<Competicio> competicions = new ArrayList<>();

    private GestorCompeticions() {}

    public static List<Competicio> getCompeticions() {
        return Collections.unmodifiableList(competicions);
    }

    public static void addCompeticio(Competicio competicio) {
        competicions.add(competicio);
    }


}


