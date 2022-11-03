package com.example.appliprofil.composant

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appliprofil.R

@Composable
fun NoResult () {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .padding(top = 100.dp)){
        Text(text = "Pas de Résultat", fontSize = 25.sp)
        Icon(painterResource(id = R.drawable.sad_face), contentDescription = "", Modifier.size(100.dp))
    }
}