package com.example.patrigod.objects_models

import com.example.patrigod.models.Anuncio
//Datos de los anuncios inmutable
class DatosAnuncios {
    var listAnuncios: List<Anuncio> = listOf(
        Anuncio(0, "¿Los ochios de dónde son?", "Úbeda", "19/12/2023", "Los ochios originalmente son de Úbeda", "https://upload.wikimedia.org/wikipedia/commons/8/86/Ochios_Andaluces.jpg"),
        Anuncio(1, "¿Qué monumento visitar en Baeza?", "Baeza", "20/12/2023", "La Catedral de Baeza es uno de los lugares más emblemáticos.", "https://upload.wikimedia.org/wikipedia/commons/9/91/Baeza-Catedral-2007-08.jpg"),
        Anuncio(2, "Fiestas de Úbeda", "Úbeda", "21/12/2023", "Las fiestas de San Miguel en Úbeda son imperdibles.", "https://upload.wikimedia.org/wikipedia/commons/4/47/Ubeda_Paseo.JPG"),
        Anuncio(3, "Mejor época para visitar Granada", "Granada", "22/12/2023", "La primavera es ideal para disfrutar de la Alhambra y los alrededores.", "https://upload.wikimedia.org/wikipedia/commons/e/e8/Alhambra_-_Granada%2C_Spain_-_panoramio.jpg"),
        Anuncio(4, "¿Qué hace especial a la Alhambra?", "Granada", "23/12/2023", "La Alhambra es famosa por su arquitectura nazarí y vistas espectaculares.", "https://upload.wikimedia.org/wikipedia/commons/8/87/Alhambra_-_Generalife.jpg")
    )
}
