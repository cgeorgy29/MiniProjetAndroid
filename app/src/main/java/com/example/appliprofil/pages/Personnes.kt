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
import com.example.appliprofil.Personnes
import com.example.appliprofil.R
import com.example.appliprofil.compo.ExpandableSearchView
import com.example.appliprofil.compo.ExpendableFABMenu
import com.example.appliprofil.compo.ExpendableSearchButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Personnes (navController : NavController, viewModel: MainViewModel, classe : WindowSizeClass){
    val classLargeur = classe.widthSizeClass
    val personnes by viewModel.personnes.collectAsState()
    var pname by rememberSaveable { mutableStateOf("") }
    if (personnes.isEmpty()|| pname.isEmpty()) viewModel.lastPersonne()
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
                                onSearchDisplayClosed = {pname =""} //changement à fermeture de recherche
                            )
                        }
                    )
                },
                content = {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        Modifier.padding(bottom = 60.dp)
                    ) {
                        items(personnes) { personne ->
                            FabCardP(personne, classe)
                        }
                    }
                },

                bottomBar = {
                    ParamBottomBar(navController)
                }
            )
        }else -> {
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
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                Modifier.padding(bottom = 10.dp, start = 70.dp)){
                items(personnes){ personne ->
                    FabCardP(personne, classe)
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
fun FabCardP(personne: Personnes, classe: WindowSizeClass){
    val url = "https://image.tmdb.org/t/p/w220_and_h330_face" + personne.profile_path
    var tcard: Dp
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clickable { }
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
                        .height(300.dp),
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