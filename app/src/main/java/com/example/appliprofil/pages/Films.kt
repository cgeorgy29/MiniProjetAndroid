package com.example.appliprofil.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.navigation.NavController
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appliprofil.MainViewModel
import com.example.appliprofil.ParamBottomBar
import com.example.appliprofil.composant.*


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Films (navController : NavController, viewModel: MainViewModel, classe : WindowSizeClass){
    val classLargeur = classe.widthSizeClass
    val movies by viewModel.movies.collectAsState()
    var fname by rememberSaveable {mutableStateOf("")}
    if (fname.isNotEmpty()) viewModel.searchMovie(fname)
    when (classLargeur){

        //------------------- position verticale --------------------//
        WindowWidthSizeClass.Compact ->{
            Scaffold(
                topBar = {
                    TopAppBar(
                        {
                            ExpandableSearchView(
                                searchDisplay = fname , //val par défaut
                                onSearchDisplayChanged = {fname =it}, //changement à saisie
                                onSearchDisplayClosed = {fname= ""}, //changement à fermeture de recherche
                            )
                        }
                    )
                },
                content = {
                    if (movies.isEmpty() && fname.isNotEmpty()){
                        NoResult()
                    }else {
                        if (fname.isEmpty()) viewModel.lastMovie()
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            Modifier.padding(bottom = 60.dp)
                        ) {
                            items(movies) { movie ->
                                FabCardF(movie = movie, navController = navController, classe)
                            }
                        }
                    }
                },
                bottomBar = {
                    ParamBottomBar(navController)
                }
            )
        }

        // ----------------position horizontale ------------------------//

        else -> {
            Scaffold(
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = {
                         ExpendableSearchButton(
                                searchDisplay = fname,
                                onSearchDisplayChanged = {fname =it} ,
                                onSearchDisplayClosed = {fname= ""}
                         )
               },

            ) {
                if (movies.isEmpty() && fname.isNotEmpty()){
                    NoResult()
                }else {
                    if (fname.isEmpty()) viewModel.lastMovie()
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        Modifier.padding(bottom = 10.dp, start = 70.dp)
                    ) {
                        items(movies) { movie ->
                            FabCardF(movie = movie, navController = navController, classe)
                        }
                    }
                }
            }

            ExpendableFABMenu(
                navController = navController
            )
        }
    }
}