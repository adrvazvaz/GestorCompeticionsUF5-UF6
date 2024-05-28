package com.example.gestorcompeticionsuf5uf6;

public class Competicio {

    private final int codigo;
    private final String tipus;
    private final int numEquips;
    private final String categoria;
    private final String genere;

    public Competicio(int codigo, String tipus, int numEquips, String categoria, String genere) {
        this.codigo = codigo;
        this.tipus = tipus;
        this.numEquips = numEquips;
        this.categoria = categoria;
        this.genere = genere;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipus() {
        return tipus;
    }

    public int getNumEquips() {
        return numEquips;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public String toString() {
        return "Competicio{" +
                "codigo=" + codigo +
                ", tipus='" + tipus + '\'' +
                ", numEquips=" + numEquips +
                ", categoria='" + categoria + '\'' +
                ", genere='" + genere + '\'' +
                '}';
    }


}
