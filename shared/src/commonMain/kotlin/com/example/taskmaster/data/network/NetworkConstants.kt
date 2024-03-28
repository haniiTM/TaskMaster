package com.example.taskmaster.data.network

object NetworkConstants {
    // изменить url
    const val baseUrl = "https://pokeapi.co/api/v2/"

    object Login {
        // изменить домен
        const val route = baseUrl + "login"
        val byName: (String) -> String = { name -> "$route/$name"}
    }
}