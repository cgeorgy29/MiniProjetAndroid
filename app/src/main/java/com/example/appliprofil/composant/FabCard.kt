package com.example.appliprofil.composant

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appliprofil.Movie
import com.example.appliprofil.Personnes
import com.example.appliprofil.R
import com.example.appliprofil.Serie

@Composable
fun FabCardF(movie: Movie, navController: NavController, classe: WindowSizeClass){
    val url ="https://image.tmdb.org/t/p/w220_and_h330_face" + movie.poster_path
    var tcard: Dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { navController.navigate("detailfilm" + "/${movie.id}") }
            .padding(4.dp),
        elevation = 10.dp
    ) {
        tcard = if(classe.widthSizeClass == WindowWidthSizeClass.Compact) {
            300.dp
        } else {
            220.dp
        }
        Column(Modifier.padding(4.dp)) {
            if (movie.poster_path.isNullOrBlank()){
                AsyncImage(
                    model = R.drawable.clap,
                    contentDescription = "affiche",
                    modifier = Modifier
                        .height(tcard),
                    contentScale = ContentScale.Fit
                )
            }
            else{
                AsyncImage(
                    model = url,
                    contentDescription = "affiche",
                    modifier = Modifier
                        .height(tcard),
                    contentScale = ContentScale.Fit)
            }
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )

            if (movie.release_date.isNullOrBlank()){
                Text(text = "Non répertorié", fontSize = 15.sp)

            }else {
                Text(text = movie.release_date, fontSize = 15.sp)
            }        }
    }
}

@Composable
fun FabCardS(serie : Serie, navController: NavController, classe: WindowSizeClass){
    val url ="https://image.tmdb.org/t/p/w220_and_h330_face" + serie.poster_path
    var tcard: Dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { navController.navigate("detailserie" + "/${serie.id}") }
            .padding(4.dp),
        elevation = 10.dp
    ) {
        tcard = if(classe.widthSizeClass == WindowWidthSizeClass.Compact) {
            300.dp
        } else {
            220.dp
        }
        Column(Modifier.padding(4.dp)) {
            if (serie.poster_path.isNullOrBlank()){
                AsyncImage(
                    model = R.drawable.series,
                    contentDescription = "affiche",
                    modifier = Modifier
                        .height(tcard),
                    contentScale = ContentScale.Fit
                )}
            else{
                AsyncImage(
                    model = url,
                    contentDescription = "affiche",
                    modifier = Modifier
                        .height(tcard),
                    contentScale = ContentScale.Fit)
            }
            Text(
                text = serie.name,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            if (serie.first_air_date.isNullOrBlank()){
                Text(text = "Non répertorié", fontSize = 15.sp)

            }else {
                Text(text = serie.first_air_date, fontSize = 15.sp)
            }

        }
    }
}

@Composable
fun FabCardP(personne: Personnes, classe: WindowSizeClass){
    val url = "https://image.tmdb.org/t/p/w220_and_h330_face" + personne.profile_path
    var tcard: Dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable {}
            .padding(4.dp),
        elevation = 10.dp
    ) {
        tcard = if(classe.widthSizeClass == WindowWidthSizeClass.Compact) {
            300.dp
        } else {
            220.dp
        }
        Column(Modifier.padding(4.dp)) {
            if (personne.profile_path.isNullOrBlank()){
                AsyncImage(
                    model = R.drawable.acteur,
                    contentDescription = "affiche",
                    modifier = Modifier
                        .height(tcard),
                    contentScale = ContentScale.Fit
                )}
            else{
                AsyncImage(
                    model = url,
                    contentDescription = "affiche",
                    modifier = Modifier
                        .height(tcard),
                    contentScale = ContentScale.Fit)
            }
            Text(
                text = personne.name,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(text = personne.known_for_department, fontSize = 15.sp)
        }
    }
}

