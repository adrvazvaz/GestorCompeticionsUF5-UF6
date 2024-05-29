package com.example.gestorcompeticionsuf5uf6;

public class Competicio {

    private final int codigo;
    private String tipus;
    private int numEquips;
    private String categoria;
    private String genere;

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

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public void setNumEquips(int numEquips) {
        this.numEquips = numEquips;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
}
