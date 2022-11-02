package com.example.appliprofil.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appliprofil.MainViewModel
import com.example.appliprofil.ParamBottomBar
import com.example.appliprofil.Serie
import com.example.appliprofil.compo.ExpendableFABMenu
import com.example.appliprofil.compo.ExpandableSearchView
import com.example.appliprofil.compo.ExpendableSearchButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Series(navController : NavController, viewModel : MainViewModel, classe : WindowSizeClass){
    val classLargeur = classe.widthSizeClass
    val series by viewModel.series.collectAsState()
    var sname by rememberSaveable {mutableStateOf("") }
    if (series.isEmpty()|| sname.isEmpty()) viewModel.lastSerie()
    if (sname.isNotEmpty()) viewModel.searchSerie(sname)
    when (classLargeur){
        WindowWidthSizeClass.Compact -> {
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
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        Modifier.padding(bottom = 60.dp)
                    ) {
                        items(series) { serie ->
                            FabCardS(serie,navController,classe)
                        }
                    }
                },

                bottomBar = {
                    ParamBottomBar(navController)
                }
            )
        }
        else -> {
            Scaffold(
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                    ExpendableSearchButton(
                        searchDisplay = sname,
                        onSearchDisplayChanged = {sname =it} ,
                        onSearchDisplayClosed = {sname= ""}
                    )
                },

                ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    Modifier.padding(bottom = 10.dp, start = 70.dp)){
                    items(series){ serie ->
                        FabCardS(serie = serie, navController = navController, classe)
                    }

                }
            }
            ExpendableFABMenu(
                navController = navController
            )
        }
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
            AsyncImage(
                model = url,
                contentDescription = "affiche",
                modifier = Modifier
                    .height(tcard),
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