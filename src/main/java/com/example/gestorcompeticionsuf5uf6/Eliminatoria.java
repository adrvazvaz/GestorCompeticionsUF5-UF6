package com.example.gestorcompeticionsuf5uf6;

/**
 * Clase que representa una competición de tipo eliminatoria.
 */
public class Eliminatoria extends Competicio {

    public Eliminatoria(int codigo, String tipus, int numEquips, String categoria, String genere) {
        super(codigo, tipus, numEquips, categoria, genere);

        if (numEquips<= 0) {
            throw new IllegalArgumentException("El número de equipos debe ser mayor que cero.");
        }
    }

}
