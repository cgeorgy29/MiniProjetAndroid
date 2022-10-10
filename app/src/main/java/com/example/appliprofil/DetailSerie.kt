package com.example.appliprofil

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun DetailSerie(nav : NavController){
    Column() {
        Image_ppd()
        Titre(carac = "Détail Séries")
        Button(onClick = { nav.navigate("profil") }) {
            Text(text = "Button")
        }
    }

}