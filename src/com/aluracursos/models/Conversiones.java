package com.aluracursos.models;

public class Conversiones {
    String conversion;
    String fecha;

    public Conversiones() {
    }

    public Conversiones(String conversion, String fecha) {
        this.conversion = conversion;
        this.fecha = fecha;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Conversion:{" +
                "conversion='" + conversion + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
