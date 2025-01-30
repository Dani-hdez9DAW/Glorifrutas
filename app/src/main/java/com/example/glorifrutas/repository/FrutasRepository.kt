package com.example.glorifrutas.repository

import com.example.glorifrutas.R
import com.example.glorifrutas.model.Fruta

class FrutasRepository {
    fun getFrutas(): List<Fruta> {
        val frutas = mutableListOf(
            Fruta(
                1,
                "Manzana",
                R.color.red_200,
                "Una manzana roja deliciosa y jugosa, perfecta para un snack saludable.",
                "Una manzana roja deliciosa y jugosa, perfecta para un snack saludable o para hacer postres. Es rica en fibra y vitaminas, y se puede disfrutar de muchas maneras diferentes, ya sea cruda, cocida o en jugos.",
                R.drawable.manzana,
                5
            ),
            Fruta(
                2,
                "Banana",
                R.color.yellow_200,
                "Una banana amarilla dulce y nutritiva, ideal para batidos y postres.",
                "Una banana amarilla dulce y nutritiva, ideal para batidos, postres o simplemente como un snack rápido. Las bananas son una excelente fuente de potasio y energía, y son perfectas para llevar a cualquier lugar.",
                R.drawable.banana,
                4
            ),
            Fruta(
                3,
                "Naranja",
                R.color.orange_800,
                "Una naranja jugosa y rica en vitamina C, excelente para jugos frescos.",
                "Una naranja jugosa y rica en vitamina C, excelente para jugos frescos o para comer directamente. Las naranjas son conocidas por su capacidad para fortalecer el sistema inmunológico y proporcionar una gran cantidad de antioxidantes.",
                R.drawable.naranjas,
                5
            ),
            Fruta(
                4,
                "Fresa",
                R.color.pink_200,
                "Una fresa roja dulce y jugosa, perfecta para postres y batidos.",
                "Una fresa roja dulce y jugosa, perfecta para postres y batidos. Las fresas son bajas en calorías y ricas en vitamina C, fibra y antioxidantes, lo que las convierte en una opción saludable y deliciosa.",
                R.drawable.fresas,
                5
            ),
            Fruta(
                5,
                "Uva",
                R.color.purple_200,
                "Un racimo de uvas moradas, ideal para snacks o para hacer vino.",
                "Un racimo de uvas moradas, ideal para comer como snack o para hacer vino. Las uvas son ricas en antioxidantes y tienen propiedades antiinflamatorias, lo que las hace beneficiosas para la salud del corazón.",
                R.drawable.uvas,
                4
            ),
            Fruta(
                6,
                "Piña",
                R.color.orange_500,
                "Una piña tropical dulce y jugosa, perfecta para jugos y postres.",
                "Una piña tropical dulce y jugosa, perfecta para jugos y postres. La piña es conocida por su contenido de bromelina, una enzima que ayuda en la digestión y tiene propiedades antiinflamatorias.",
                R.drawable.pinas,
                5
            ),
            Fruta(
                7,
                "Mango",
                R.color.orange_700,
                "Un mango dulce y jugoso, ideal para batidos y postres tropicales.",
                "Un mango dulce y jugoso, ideal para batidos y postres tropicales. Los mangos son ricos en vitaminas A y C, y son conocidos por su sabor exótico y su capacidad para mejorar la salud de la piel.",
                R.drawable.mangos,
                5
            ),
            Fruta(
                8,
                "Sandía",
                R.color.green_500,
                "Una sandía grande y jugosa, perfecta para refrescarse en verano.",
                "Una sandía grande y jugosa, perfecta para refrescarse en verano. La sandía es hidratante y baja en calorías, y es una excelente fuente de vitaminas A y C, así como de antioxidantes como el licopeno.",
                R.drawable.sandias,
                5
            )
        )
        return frutas
    }
}