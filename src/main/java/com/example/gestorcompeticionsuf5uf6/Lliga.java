package com.example.gestorcompeticionsuf5uf6;

public class Lliga extends Competicio {

    /**
     * Constructor de la competición de tipo liga.
     *
     * @param codigo    Código de la competición.
     * @param tipus     Tipo de la competición.
     * @param numEquips Número de equipos en la competición.
     * @param categoria Categoría de la competición.
     * @param genere    Género de la competición.
     * @throws MaxTeamsExceededException si se excede el número máximo de equipos.
     */
    public Lliga(int codigo, String tipus, int numEquips, String categoria, String genere) throws MaxTeamsExceededException {
        super(codigo, "Lliga", numEquips, categoria, genere);

        if (numEquips > 30) {
            throw new MaxTeamsExceededException("Error: El número máximo de equipos en una liga es 30.");
        }
    }

    /**
     * Excepción lanzada cuando se excede el número máximo de equipos en una liga.
     */
    public static class MaxTeamsExceededException extends Exception {
        public MaxTeamsExceededException(String message) {
            super(message);
        }
    }
}



