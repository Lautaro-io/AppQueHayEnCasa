package com.chelo.appquehayencasa.ui.features.onboardingscreen

import com.chelo.appquehayencasa.R

data class PagerModel(
    val title: String,
    val description: String,
    val image: Int,
)

val pages = listOf<PagerModel>(
    PagerModel(
        "¿Saliste a comprar y no te acordás qué tenés?",
        "Guardá fácilmente todo lo que tenés en casa y llevá el control de tus productos.",
        R.drawable.products
    ),
    PagerModel(
        "Ideas con lo que ya tenés",
        "Te mostramos recetas según los ingredientes que ya tenés. Ahorrá tiempo y plata.",
        R.drawable.cocina
    ),
    PagerModel(
        " No tires más comida",
        "Recibí alertas antes de que se venza algo. Aprovechá todo y evitá desperdicios.",
        R.drawable.fechalimite
    )
)





