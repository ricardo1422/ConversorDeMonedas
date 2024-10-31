package com.aluracursos.main;

import com.aluracursos.models.Conversiones;
import com.aluracursos.models.Moneda;
import com.aluracursos.records.RecordMoneda;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Conversiones> listaConversiones=new ArrayList<>();
        Scanner sc= new Scanner(System.in);
        Gson gson= new GsonBuilder()
                .setPrettyPrinting().create();
        byte opcion=0;
        String monedaNombre="";
        String monedaNombreConversion="";
        boolean run=true;
        boolean menu=true;
        double cantidad=0.00;
        String cadenaSalida="";
        Conversiones conversionAux= new Conversiones();

        while(run) {
            while (menu) {
                monedaNombre="";
                System.out.println("""
                        Eliga una opción:
                        1.-Convertir de Peso Méxicano (MXN)
                        2.-Convertir de Dolar Estadunidense (USD)
                        3.-Convertir de Peso colombiano (COP)
                        4.-Convertir de Real Brazileño (BRL)
                        5.-Convertir de Peso Argentino(ARS)
                        6.-Convertir de Yen Japones(JPY)
                        7.-Salir del programa
                        """);
                opcion = sc.nextByte();
                switch (opcion) {
                    case 1:
                        monedaNombre = "MXN";
                        menu=false;
                        break;
                    case 2:
                        monedaNombre = "USD";
                        menu=false;
                        break;
                    case 3:
                        monedaNombre = "COP";
                        menu=false;
                        break;
                    case 4:
                        monedaNombre = "BRL";
                        menu=false;
                        break;
                    case 5:
                        monedaNombre = "ARS";
                        menu=false;
                        break;
                    case 6:
                        monedaNombre = "JPY";
                        menu=false;
                        break;
                    case 7:
                        run = false;
                        menu=false;
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            }
            if (!run){
                break;
            }
            menu=true;
            String direcion = "https://v6.exchangerate-api.com/v6/bac0f687aaf50a0da6916ff0/latest/"+monedaNombre;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direcion))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            // System.out.println(response.body());
            RecordMoneda recordMoneda = gson.fromJson(response.body(), RecordMoneda.class);
            Moneda moneda = new Moneda(recordMoneda);
            while (menu) {
                monedaNombre="";
                System.out.println("""
                        Eliga una opción:
                        1.-Convertir a Peso Méxicano (MXN)
                        2.-Convertir a Dolar Estadunidense (USD)
                        3.-Convertir a Peso colombiano (COP)
                        4.-Convertir a Real Brazileño (BRL)
                        5.-Convertir a Peso Argentino(ARS)
                        6.-Convertir a Yen Japones(JPY)
                        7.-Salir del programa
                        """);
                opcion = sc.nextByte();
                switch (opcion) {
                    case 1:
                        monedaNombreConversion = "MXN";
                        menu=false;
                        break;
                    case 2:
                        monedaNombreConversion = "USD";
                        menu=false;
                        break;
                    case 3:
                        monedaNombreConversion = "COP";
                        menu=false;
                        break;
                    case 4:
                        monedaNombreConversion = "BRL";
                        menu=false;
                        break;
                    case 5:
                        monedaNombreConversion = "ARS";
                        menu=false;
                        break;
                    case 6:
                        monedaNombreConversion = "JPY";
                        menu=false;
                        break;
                    case 7:
                        run = false;
                        menu=false;
                        break;
                    default:
                        System.out.println("Opcion invalida");
                }
            }
            if (!run){
                break;
            }
            menu=true;
            System.out.println("ingresa la cantidad a convertir");
            cantidad= sc.nextDouble();
            double cantidadConvertida=cantidad*moneda.getValores().get(monedaNombreConversion);
            cadenaSalida=cantidad+" "+moneda.getAbreviatura()+" convertida a: "+ monedaNombreConversion+" son: "+cantidadConvertida;
            System.out.println(cadenaSalida);
            conversionAux.setConversion(cadenaSalida);
            Date current= new Date();
            conversionAux.setFecha(String.valueOf(current));
            listaConversiones.add(conversionAux);
        }
        if (listaConversiones.size()>0) {
            FileWriter archivo = new FileWriter("Conversiones.txt");
            archivo.write(gson.toJson(listaConversiones));
            archivo.close();
            try {
                File archivoLeer = new File("Conversiones.txt");
                Scanner scanner = new Scanner(archivoLeer);

                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    System.out.println(linea);
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        }
        System.out.println("Programa finalizando");
    }
}
