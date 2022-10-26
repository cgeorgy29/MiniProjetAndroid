package com.example.appliprofil

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appliprofil.ui.theme.ExpandableSearchView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Series(navController : NavController, viewModel :MainViewModel){
    val series by viewModel.series.collectAsState()
    var sname by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                {
                    ExpandableSearchView(
                        searchDisplay = sname , //val par défaut
                        onSearchDisplayChanged = {sname = it}, //changement à saisie
                        onSearchDisplayClosed = {sname =""} //changement à fermeture de recherche
                    )
                }
            )
        },

        content = {
            if (series.isEmpty()|| sname.isEmpty()) viewModel.lastSerie()
            if (sname.isNotEmpty()) viewModel.searchSerie(sname)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier.padding(bottom = 60.dp)
                ) {
                items(series) { serie ->
                    FabCardS(serie,navController)
                }
            }
        },

        bottomBar = {
            ParamBottomBar(navController)
        }
    )
}
@Composable
fun FabCardS(serie : Serie, navController: NavController){
    val url =
        "https://image.tmdb.org/t/p/w220_and_h330_face" + serie.poster_path
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { navController.navigate("detailserie" + "/${serie.id}") }
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
                text = serie.name,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp
            )
            Text(text = serie.first_air_date, fontSize = 15.sp)

        }
    }
}