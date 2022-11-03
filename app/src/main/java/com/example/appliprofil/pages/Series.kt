package com.example.appliprofil.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appliprofil.MainViewModel
import com.example.appliprofil.ParamBottomBar
import com.example.appliprofil.composant.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Series(navController : NavController, viewModel : MainViewModel, classe : WindowSizeClass){
    val classLargeur = classe.widthSizeClass
    val series by viewModel.series.collectAsState()
    var sname by rememberSaveable {mutableStateOf("") }
    if (sname.isNotEmpty()) viewModel.searchSerie(sname)
    when (classLargeur){


        //------------------- position verticale --------------------//
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        {
                            ExpandableSearchView(
                                searchDisplay = sname , //val par défaut
                                onSearchDisplayChanged = {sname = it}, //changement à saisie
                                onSearchDisplayClosed = {sname =""}, //changement à fermeture de recherche
                            )
                        }
                    )
                },

                content = {
                    if (series.isEmpty() && sname.isNotEmpty()){
                        NoResult()
                    }else {
                        if (sname.isEmpty()) viewModel.lastSerie()
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            Modifier.padding(bottom = 60.dp)
                        ) {
                            items(series) { serie ->
                                FabCardS(serie, navController, classe)
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
                        searchDisplay = sname,
                        onSearchDisplayChanged = {sname =it} ,
                        onSearchDisplayClosed = {sname= ""}
                    )
                },

                ) {
                if (series.isEmpty() && sname.isNotEmpty()){
                    NoResult()
                }else {
                    if (sname.isEmpty()) viewModel.lastSerie()
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        Modifier.padding(bottom = 10.dp, start = 70.dp)
                    ) {
                        items(series) { serie ->
                            FabCardS(serie = serie, navController = navController, classe)
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