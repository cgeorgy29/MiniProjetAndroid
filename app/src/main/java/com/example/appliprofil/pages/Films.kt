package com.example.appliprofil

import android.annotation.SuppressLint
import android.icu.text.ListFormatter.Width
import android.view.Window
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.appliprofil.ui.theme.ExpandableSearchView


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Films (navController : NavController, viewModel: MainViewModel, classe : WindowSizeClass){
    val classHauteur = classe.heightSizeClass
    val classLargeur = classe.widthSizeClass
    val movies by viewModel.movies.collectAsState()
    var fname by rememberSaveable {mutableStateOf("")}
    if (movies.isEmpty()|| fname.isEmpty()) viewModel.lastMovie()
    if (fname.isNotEmpty()) viewModel.searchMovie(fname)
    when (classLargeur){
        WindowWidthSizeClass.Compact ->{
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
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        Modifier.padding(bottom = 60.dp)
                    ) {
                        items(movies) { movie ->
                            FabCardF(movie = movie, navController = navController, classe )
                        }
                    }
                },
                bottomBar = {
                    ParamBottomBar(navController)
                }
            )
        }
        else -> {
            FloatingActionButton(
                modifier = Modifier.padding(start = 10.dp)
                    .size(50.dp),
                onClick = { /*TODO*/ }) {

            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                Modifier.padding(bottom = 10.dp, start = 70.dp)){
                items(movies){ movie ->
                    FabCardF(movie = movie, navController = navController, classe)
                }

            }

            FloatingActionButton(
                modifier = Modifier.padding(100.dp),
                onClick = { /*TODO*/ }) {

            }


        }
    }
}


@Composable
fun FabCardF(movie: Movie, navController: NavController, classe: WindowSizeClass){
    val url ="https://image.tmdb.org/t/p/w220_and_h330_face" + movie.poster_path
    var testt: Dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { navController.navigate("detailfilm" + "/${movie.id}") }
            .padding(4.dp),
        elevation = 10.dp
    ) {
        if(classe.widthSizeClass == WindowWidthSizeClass.Compact) {
            testt = 300.dp
        } else {
            testt = 220.dp
        }
        Column(Modifier.padding(4.dp)) {
            AsyncImage(
                model = url,
                contentDescription = "affiche",
                modifier = Modifier
                    .height(testt),
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