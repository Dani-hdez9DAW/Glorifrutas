package com.example.glorifrutas.repository;

import com.example.glorifrutas.R;
import com.example.glorifrutas.model.Fruta;

import java.util.ArrayList;
import java.util.List;

public class FrutasRepository {

    public List<Fruta> obtenerTodasLasFrutas() {
        List<Fruta> frutas = new ArrayList<>();

        frutas.add(new Fruta(1, "Manzana", "Rojo", "Una manzana roja", "Una manzana roja deliciosa y jugosa, perfecta para un snack saludable o para hacer postres. Es rica en fibra y vitaminas, y se puede disfrutar de muchas maneras diferentes, ya sea cruda, cocida o en jugos.", R.drawable.manzana, 5));
        frutas.add(new Fruta(2, "Banana", "Amarillo", "Una banana amarilla", "Una banana amarilla dulce y nutritiva, ideal para batidos, postres o simplemente como un snack rápido. Las bananas son una excelente fuente de potasio y energía, y son perfectas para llevar a cualquier lugar.", R.drawable.banana, 4));
        frutas.add(new Fruta(3, "Naranja", "Naranja", "Una naranja", "Una naranja jugosa y rica en vitamina C, excelente para jugos frescos o para comer directamente. Las naranjas son conocidas por su capacidad para fortalecer el sistema inmunológico y proporcionar una gran cantidad de antioxidantes.", R.drawable.naranjas, 5));
        frutas.add(new Fruta(4, "Fresa", "Rojo", "Una fresa roja", "Una fresa roja dulce y jugosa, perfecta para postres y batidos. Las fresas son bajas en calorías y ricas en vitamina C, fibra y antioxidantes, lo que las convierte en una opción saludable y deliciosa.", R.drawable.fresas, 5));
        frutas.add(new Fruta(5, "Uva", "Morado", "Un racimo de uvas", "Un racimo de uvas moradas, ideal para comer como snack o para hacer vino. Las uvas son ricas en antioxidantes y tienen propiedades antiinflamatorias, lo que las hace beneficiosas para la salud del corazón.", R.drawable.uvas, 4));
        frutas.add(new Fruta(6, "Piña", "Amarillo", "Una piña", "Una piña tropical dulce y jugosa, perfecta para jugos y postres. La piña es conocida por su contenido de bromelina, una enzima que ayuda en la digestión y tiene propiedades antiinflamatorias.", R.drawable.pinas, 5));
        frutas.add(new Fruta(7, "Mango", "Naranja", "Un mango", "Un mango dulce y jugoso, ideal para batidos y postres tropicales. Los mangos son ricos en vitaminas A y C, y son conocidos por su sabor exótico y su capacidad para mejorar la salud de la piel.", R.drawable.mangos, 5));
        frutas.add(new Fruta(8, "Sandía", "Verde", "Una sandía", "Una sandía grande y jugosa, perfecta para refrescarse en verano. La sandía es hidratante y baja en calorías, y es una excelente fuente de vitaminas A y C, así como de antioxidantes como el licopeno.", R.drawable.sandias, 5));

        return frutas;
    }

}