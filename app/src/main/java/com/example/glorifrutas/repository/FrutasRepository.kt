package com.example.glorifrutas.repository

import com.example.glorifrutas.R
import com.example.glorifrutas.model.Fruta
import com.google.firebase.firestore.FirebaseFirestore

class FrutasRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getFrutas(): List<Fruta> {
        return listOf(
            Fruta(1, "Manzana", 0xFFE57373.toInt(), "Una manzana roja deliciosa, crujiente y llena de sabor dulce con un toque ácido. Ideal para disfrutar como snack saludable o en postres.", "La manzana es una fruta muy versátil que se puede comer cruda, en compotas, tartas y jugos. Es rica en fibra, vitamina C y antioxidantes. Además, ayuda a la digestión y fortalece el sistema inmunológico.", R.drawable.manzana, 5, "Las manzanas flotan en el agua debido a que un 25% de su volumen es aire.", "Estados Unidos 🇺🇸"),

            Fruta(2, "Banana", 0xFFFFEB3B.toInt(), "Una banana amarilla y dulce, perfecta para obtener energía rápida. Es ideal para batidos, postres y meriendas.", "La banana es rica en potasio, vitamina B6 y fibra. Es conocida por sus beneficios para la digestión, el sistema nervioso y el corazón. Además, es una fruta muy popular entre deportistas por su capacidad para reponer energías rápidamente.", R.drawable.banana, 4, "Las bananas son técnicamente bayas y crecen en plantas herbáceas.", "Ecuador 🇪🇨"),

            Fruta(3, "Naranja", 0xFFFF9800.toInt(), "Una naranja jugosa, cargada de vitamina C, ideal para zumos frescos y saludables. Su sabor es dulce con un toque cítrico refrescante.", "Las naranjas son cítricos muy saludables que refuerzan el sistema inmunológico. Además de vitamina C, contienen fibra y antioxidantes. Se usan en una amplia variedad de recetas, desde jugos hasta mermeladas y ensaladas.", R.drawable.naranjas, 5, "Las naranjas son un híbrido natural entre los pomelos y las mandarinas.", "España 🇪🇸"),

            Fruta(4, "Fresa", 0xFFFF4081.toInt(), "Una fresa roja y jugosa, con un aroma delicioso y un sabor dulce y ligeramente ácido. Perfecta para postres y batidos.", "Las fresas son ricas en vitamina C, antioxidantes y fibra. Ayudan a mantener la piel sana y a mejorar la salud cardiovascular. Su uso en la cocina es muy amplio, desde ensaladas hasta pasteles y mermeladas.", R.drawable.fresas, 5, "Las fresas son las únicas frutas con semillas en su exterior.", "México 🇲🇽"),

            Fruta(5, "Uva", 0xFF9C27B0.toInt(), "Un racimo de uvas frescas, dulces y jugosas, ideales para consumir solas o como parte de ensaladas y postres.", "Las uvas contienen antioxidantes potentes como el resveratrol, que es bueno para el corazón. Se usan para hacer vinos, jugos y como snack saludable. Su consumo también se asocia a la mejora de la circulación sanguínea.", R.drawable.uvas, 4, "Las uvas son una de las frutas más antiguas cultivadas por el ser humano.", "Italia 🇮🇹"),

            Fruta(6, "Piña", 0xFFFFA726.toInt(), "Una piña tropical, con su pulpa amarilla dulce y refrescante. Excelente para jugos, postres o para comerla sola.", "La piña es una fruta tropical rica en vitamina C y bromelina, una enzima que ayuda a la digestión. Es muy usada en la cocina, tanto en platos dulces como salados, y aporta un toque exótico a las recetas.", R.drawable.pinas, 5, "Las piñas pueden tardar hasta dos años en crecer completamente.", "Costa Rica 🇨🇷"),

            Fruta(7, "Mango", 0xFFFFA000.toInt(), "Un mango dulce y jugoso, con un sabor tropical que es perfecto para batidos, ensaladas y postres.", "El mango es conocido como el rey de las frutas en la India. Es rico en vitamina A, C y antioxidantes. Su pulpa suave y dulce lo convierte en un ingrediente muy versátil en la cocina.", R.drawable.mangos, 5, "El mango es una de las frutas más consumidas a nivel mundial.", "India 🇮🇳"),

            Fruta(8, "Sandía", 0xFF4CAF50.toInt(), "Una sandía grande, refrescante y jugosa, ideal para hidratarse en los días calurosos. Su sabor es dulce y suave.", "La sandía contiene mucha agua, lo que la hace muy hidratante. Es rica en antioxidantes como el licopeno, beneficioso para la salud del corazón. Se consume en trozos frescos, en jugos o incluso en ensaladas.", R.drawable.sandias, 5, "La sandía está compuesta en un 92% de agua.", "Brasil 🇧🇷")
        )
    }


    fun guardarFrutasEnFirestore() {
        val frutas = getFrutas()
        for (fruta in frutas) {
            db.collection("frutas")
                .document(fruta.id.toString()) // Guardar con ID único
                .set(fruta)
                .addOnSuccessListener {
                    println("Fruta ${fruta.nombre} guardada exitosamente en Firestore")
                }
                .addOnFailureListener { e ->
                    println("Error al guardar ${fruta.nombre}: ${e.message}")
                }
        }
    }
}
