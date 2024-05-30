package model;
public class Model {
    private String dato;

    public Model() {
    }

    public Model(String dato) {
        this.dato = dato;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return dato;
    }
}
