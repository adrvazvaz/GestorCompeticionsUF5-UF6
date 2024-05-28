package com.example.gestorcompeticionsuf5uf6;

/**
 * Clase que representa una competición de tipo eliminatoria.
 */
public class Eliminatoria extends Competicio {
    private static final int codigo = 0;

    /**
     * Constructor de una competición de tipo eliminatoria.
     *
     * @param numEquipsValue Número de equipos en la competición.
     * @param categoriaValue La categoría de la competición.
     * @param genereValue    El género de la competición.
     */
    public Eliminatoria(int numEquipsValue, String categoriaValue, String genereValue) {
        super(codigo, "Eliminatòria", numEquipsValue, categoriaValue, genereValue);
    }
}