package com.example.gestorcompeticionsuf5uf6;

import java.util.ArrayList;
import java.util.List;

public class GestorCompeticions {
    private static final List<Competicio> competicions = new ArrayList<>();

    public static List<Competicio> getCompeticions() {
        return competicions;
    }

    public static void addCompeticio(Competicio competicio) {
        competicions.add(competicio);
    }

    public static List<String> getCompeticionsCounters() {
        List<String> contadores = new ArrayList<>();
        for (int i = 0; i < competicions.size(); i++) {
            contadores.add("Competició " + (i + 1));
        }
        return contadores;
    }
}

