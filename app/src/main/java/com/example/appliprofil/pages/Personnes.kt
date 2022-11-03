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
import com.example.appliprofil.composant.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Personnes (navController : NavController, viewModel: MainViewModel, classe : WindowSizeClass){
    val classLargeur = classe.widthSizeClass
    val personnes by viewModel.personnes.collectAsState()
    var pname by rememberSaveable { mutableStateOf("") }
    if (pname.isNotEmpty()) viewModel.searchPersonne(pname)
    when (classLargeur){
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        {
                            ExpandableSearchView(
                                searchDisplay = pname , //val par défaut
                                onSearchDisplayChanged = {pname = it}, //changement à saisie
                                onSearchDisplayClosed = {pname =""}, //changement à fermeture de recherche
                            )
                        }
                    )
                },
                content = {
                    if (personnes.isEmpty() && pname.isNotEmpty()){
                        NoResult()
                    }else {
                        if (pname.isEmpty()) viewModel.lastPersonne()
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            Modifier.padding(bottom = 60.dp)
                        ) {
                            items(personnes) { personne ->
                                FabCardP(personne, classe)
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
                    searchDisplay = pname,
                    onSearchDisplayChanged = {pname =it} ,
                    onSearchDisplayClosed = {pname= ""}
                )
            },

            ) {
            if (personnes.isEmpty() && pname.isNotEmpty()){
                NoResult()
            }else {
                if (pname.isEmpty()) viewModel.lastPersonne()
                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    Modifier.padding(bottom = 10.dp, start = 70.dp)
                ) {
                    items(personnes) { personne ->
                        FabCardP(personne, classe)
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

