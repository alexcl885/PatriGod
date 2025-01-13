package com.example.patrigod.objects_models

import com.example.patrigod.models.Monumento

class Datos {
    var listMonumentos: List<Monumento> = listOf(
        // Úbeda
        Monumento(
            0,
            "Sacra Capilla del Salvador",
            "Úbeda",
            "1536",
            "Es un templo funerario construido bajo patrocinio de Francisco de los Cobos.",
            "https://upload.wikimedia.org/wikipedia/commons/e/e8/Ubeda_-_Capilla_del_Salvador_42.jpg",
            "La Sacra Capilla del Salvador es uno de los máximos exponentes del Renacimiento en España. Este templo, concebido como capilla funeraria de Francisco de los Cobos, refleja el esplendor y la riqueza de la aristocracia renacentista. Diseñada por Diego de Siloé y construida por Andrés de Vandelvira, la capilla destaca por su impresionante fachada plateresca, rica en detalles iconográficos que combinan lo religioso y lo mitológico. En su interior, la cúpula ornamentada simboliza la conexión entre lo terrenal y lo divino, mientras que los sepulcros y retablos dan testimonio de la devoción y el poder de su fundador."
        ),
        Monumento(
            1,
            "Santa María de los Reales Alcázares",
            "Úbeda, Jaén",
            "1233",
            "Es una impresionante iglesia gótica y renacentista.",
            "https://imgs.search.brave.com/sicVDBkconPOvYM9pP7qIVu7oCacNGAFxU6oyKIPyx0/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly91cGxv/YWQud2lraW1lZGlh/Lm9yZy93aWtpcGVk/aWEvY29tbW9ucy8z/LzNlLzIwMDItMTAt/MjZfMTEtMTVfQW5k/YWx1c2llbixfTGlz/c2Fib25fMTIyXyVD/MyU5QWJlZGEuanBn",
            "La iglesia de Santa María de los Reales Alcázares es un majestuoso templo que fusiona estilos gótico, renacentista y barroco, destacando como un ejemplo único de la riqueza arquitectónica de Úbeda. Originalmente construida en 1233 sobre una antigua mezquita, su estructura evolucionó con los siglos, incorporando detalles mudéjares y renacentistas. Su imponente portada y los elementos ornamentales de su interior reflejan la diversidad cultural e histórica de la región. Además, ha sido testigo de eventos históricos y espirituales que la convierten en un lugar imprescindible para entender el legado de la ciudad."
        ),

        // Baeza
        Monumento(
            2,
            "Catedral de Baeza",
            "Baeza, Jaén",
            "1147",
            "Una destacada catedral renacentista en el corazón de Baeza.",
            "https://imgs.search.brave.com/SgCIqDaMR_5xIyu46IvZRsB5HsnBijPcHjKCeOjmIYA/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9waG90/bzYyMHg0MDAubW5z/dGF0aWMuY29tL2Iw/ZGQ1OTgzNDY3ZGU1/MmQ4YWNlODI1Mjk2/OTM5NTk0L2NhdGVk/cmFsLWRlLWJhZXph/LmpwZw",
            "La Catedral de Baeza, construida sobre una antigua mezquita en el siglo XII, es un emblema del Renacimiento en España. Transformada en catedral en el siglo XVI, combina elementos arquitectónicos góticos, renacentistas y barrocos que reflejan la riqueza cultural de la ciudad. Su fachada principal, decorada con delicados relieves, invita a los visitantes a descubrir un interior que alberga impresionantes obras de arte sacro, incluyendo retablos, capillas y sepulcros. Su papel como centro espiritual y cultural ha sido crucial en la historia de Baeza."
        ),
        Monumento(
            3,
            "Fuente de Santa María",
            "Baeza, Jaén",
            "1564",
            "Una emblemática fuente renacentista situada frente a la catedral.",
            "https://media-cdn.tripadvisor.com/media/photo-s/0e/c8/f4/07/construida-por-el-maestro.jpg",
            "La Fuente de Santa María, situada en la plaza principal de Baeza, es una joya del Renacimiento español. Diseñada en 1564, su elegante estructura celebra la prosperidad de la época, con detalles ornamentales que incluyen relieves mitológicos y heráldicos. La fuente, que servía tanto como decoración como recurso funcional, es un testimonio del esplendor arquitectónico de la ciudad y un lugar icónico donde se fusionan historia y belleza."
        ),

        // Salamanca
        Monumento(
            4,
            "Plaza Mayor de Salamanca",
            "Salamanca",
            "1729 - 1756",
            "Considerada una de las plazas más bellas de España, de estilo barroco.",
            "https://imgs.search.brave.com/shw9jkGNQir-YTVszGOd_NMdo_PP8XGWcruqIIeSZXU/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9saXZl/LnN0YXRpY2ZsaWNr/ci5jb20vMjYwNy8z/Njg1MTQ5ODI4XzU0/MWYyNzAyYjAuanBn",
            "La Plaza Mayor de Salamanca es un conjunto arquitectónico que se erige como uno de los máximos exponentes del estilo barroco español. Diseñada por Alberto Churriguera y finalizada en 1756, esta plaza es el corazón de la vida social y cultural de Salamanca. Con sus proporciones perfectas y detalles ornamentales, es un lugar de encuentro y celebración. Tanto de día como de noche, su iluminación resalta la belleza de sus arcos y edificios, convirtiéndola en un punto central de la ciudad."
        ),
        Monumento(
            5,
            "Universidad de Salamanca",
            "Salamanca",
            "1218",
            "Una de las universidades más antiguas de Europa, famosa por su fachada plateresca.",
            "https://imgs.search.brave.com/rLnNM3QRmbY5RrJdmpkZRbO1-6u5Doo88tbGnKeZnk8/rs:fit:860:0:0:0/g:ce/aHR0cDovL3d3dy52/ZXJzYWxhbWFuY2Eu/Y29tL2ZvdG9zL3Vu/aXZlcnNpZGFkLmpw/Zw",
            "La Universidad de Salamanca, fundada en 1218 por Alfonso IX, es un referente académico internacional y una de las más antiguas de Europa. Su icónica fachada plateresca, adornada con intrincados relieves y el famoso motivo de la rana sobre una calavera, es un símbolo de la ciudad. A lo largo de los siglos, ha sido cuna de ilustres figuras como Fray Luis de León y Miguel de Unamuno. La universidad combina historia, arte y educación, atrayendo a estudiantes y visitantes de todo el mundo."
        ),

        // Toledo
        Monumento(
            6,
            "Catedral de Santa María",
            "Toledo",
            "1226",
            "Una de las catedrales góticas más importantes de Europa.",
            "https://imgs.search.brave.com/Fs8w7O9SH92wExT_9pxLKFMa7RWBcZAXJK_cHgmOjTQ/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly9ldm9j/YXJ0ZS5jb20vd3At/Y29udGVudC91cGxv/YWRzLzIwMjAvMTEv/Y2F0ZWRyYWwtZGUt/dG9sZWRvLmpwZw",
            "La Catedral de Santa María, conocida también como la Catedral Primada de Toledo, es una obra maestra del gótico español. Iniciada en 1226, su construcción se prolongó durante más de dos siglos, resultando en una combinación de estilos que reflejan el paso del tiempo. Su imponente fachada y la riqueza de su interior, que incluye el impresionante altar mayor, la capilla mozárabe y la sacristía, hacen de este templo un lugar imprescindible para los amantes del arte y la historia."
        ),
        Monumento(
            7,
            "Alcázar de Toledo",
            "Toledo",
            "932 d.C",
            "Una impresionante fortaleza ubicada en lo alto de la ciudad.",
            "https://imgs.search.brave.com/e1R5HvTtZ3KZAwPbeqn6KKfgeiIjs_3JS5aZ0k_Zl-Y/rs:fit:860:0:0:0/g:ce/aHR0cHM6Ly93d3cu/c3BhaW4uaW5mby9l/eHBvcnQvc2l0ZXMv/c2VndHVyLy5jb250/ZW50L2ltYWdlcy9j/YWJlY2VyYXMtZ3Jh/bmRlcy9jYXN0aWxs/YS1tYW5jaGEvYWxj/YXphci10b2xlZG8t/Yy5qcGdfNjA0ODg5/Mzg5LmpwZw",
            "El Alcázar de Toledo es una fortaleza histórica que se alza en lo alto de la ciudad, dominando su horizonte. Con orígenes que se remontan al siglo III como palacio romano, ha sido reconstruido y adaptado a lo largo de los siglos. Durante la Guerra Civil Española, su defensa lo convirtió en un símbolo de resistencia. Hoy alberga el Museo del Ejército, donde los visitantes pueden explorar una rica colección que narra la historia militar de España, así como admirar su impresionante arquitectura y vistas panorámicas."
        )
    )
}
