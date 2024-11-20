package com.example.patrigod.objects_models
import com.example.patrigod.models.Monumento

class Datos {
    var listMonumentos: List<Monumento> = listOf(
        // Úbeda
        Monumento(
            "Sacra Capilla del Salvador",
            "Úbeda",
            "2024-12-02",
            "Es una bonita capilla",
            "https://upload.wikimedia.org/wikipedia/commons/e/e8/Ubeda_-_Capilla_del_Salvador_42.jpg"
        ),
        Monumento(
            "Santa Maria de los Reales Alcázares",
            "Úbeda, Jaén",
            "2024-12-02",
            "Es una impresionante iglesia gótica y renacentista",
            "https://upload.wikimedia.org/wikipedia/commons/5/50/Santa_María_de_los_Reales_Alcázares_%28Úbeda%29.jpg"
        ),

        // Baeza
        Monumento(
            "Catedral de Baeza",
            "Baeza, Jaén",
            "2024-12-03",
            "Una destacada catedral renacentista en el corazón de Baeza",
            "https://upload.wikimedia.org/wikipedia/commons/e/ef/Catedral_de_Baeza.jpg"
        ),
        Monumento(
            "Fuente de Santa María",
            "Baeza, Jaén",
            "2024-12-03",
            "Una emblemática fuente renacentista situada frente a la catedral",
            "https://upload.wikimedia.org/wikipedia/commons/3/36/Fuente_de_Santa_María_%28Baeza%29.jpg"
        ),

        // Salamanca
        Monumento(
            "Plaza Mayor de Salamanca",
            "Salamanca",
            "2024-12-04",
            "Considerada una de las plazas más bellas de España, de estilo barroco",
            "https://upload.wikimedia.org/wikipedia/commons/6/64/Plaza_Mayor_de_Salamanca.jpg"
        ),
        Monumento(
            "Universidad de Salamanca",
            "Salamanca",
            "2024-12-04",
            "Una de las universidades más antiguas de Europa, famosa por su fachada plateresca",
            "https://upload.wikimedia.org/wikipedia/commons/9/99/Universidad_de_Salamanca.jpg"
        ),

        // Toledo
        Monumento(
            "Catedral de Santa María",
            "Toledo",
            "2024-12-05",
            "Una de las catedrales góticas más importantes de Europa",
            "https://upload.wikimedia.org/wikipedia/commons/d/d7/Catedral_de_Santa_María_de_Toledo.jpg"
        ),
        Monumento(
            "Alcázar de Toledo",
            "Toledo",
            "2024-12-05",
            "Una impresionante fortaleza ubicada en lo alto de la ciudad",
            "https://upload.wikimedia.org/wikipedia/commons/6/63/Alcázar_de_Toledo.jpg"
        )
    )
}
