package com.aluracursos.models;

import com.aluracursos.records.RecordMoneda;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Moneda {
    @SerializedName("base_code")
    private String abreviatura;
    @SerializedName("conversion_rates")
    private HashMap<String, Double> valores=new HashMap<>();

    public Moneda(RecordMoneda recordMoneda) {
        this.abreviatura = recordMoneda.base_code();
        this.valores = recordMoneda.conversion_rates();
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public HashMap<String, Double> getValores() {
        return valores;
    }

    public void setValores(HashMap<String, Double> valores) {
        this.valores = valores;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "abreviatura='" + abreviatura + '\'' +
                ", valores=" + valores +
                '}';
    }
}
