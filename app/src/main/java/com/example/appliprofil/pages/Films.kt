package com.example.appliprofil

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.appliprofil.ui.theme.ExpandableSearchView


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Films (navController : NavController, viewModel: MainViewModel){
    val movies by viewModel.movies.collectAsState()
    var fname by rememberSaveable {mutableStateOf("")}
    Scaffold(
        topBar = {
            TopAppBar(
                {
                    ExpandableSearchView(
                        searchDisplay = fname , //val par défaut
                        onSearchDisplayChanged = {fname = it}, //changement à saisie
                        onSearchDisplayClosed = {fname =""} //changement à fermeture de recherche
                    )
                }
                )
            },

        content = {
                if (movies.isEmpty()|| fname.isEmpty()) viewModel.lastMovie()
                if (fname.isNotEmpty()) viewModel.searchMovie(fname)
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                Modifier.padding(bottom = 60.dp)
            ) {
                items(movies) { movie ->
                    FabCardF(movie = movie, navController = navController )
                }
            }
        },

        bottomBar = {
                ParamBottomBar(navController)
        }
    )
}
@Composable
fun FabCardF(movie: Movie, navController: NavController){
    val url =
        "https://image.tmdb.org/t/p/w220_and_h330_face" + movie.poster_path
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { navController.navigate("detailfilm" + "/${movie.id}") }
            .padding(4.dp),
        elevation = 10.dp
    ) {
        Column(Modifier.padding(4.dp)) {
            AsyncImage(
                model = url,
                contentDescription = "affiche",
                modifier = Modifier
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(text = movie.release_date, fontSize = 15.sp)
        }
    }
}