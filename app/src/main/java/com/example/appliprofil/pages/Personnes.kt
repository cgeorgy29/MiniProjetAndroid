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
@Composable
fun Personnes (navController : NavController, viewModel: MainViewModel){
    val personnes by viewModel.personnes.collectAsState()
    var pname by rememberSaveable { mutableStateOf("") }
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
            if (personnes.isEmpty()|| pname.isEmpty()) viewModel.lastPersonne()
            if (pname.isNotEmpty()) viewModel.searchPersonne(pname)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                Modifier.padding(bottom = 60.dp)
            ) {
                items(personnes) { personne ->
                    val url = "https://image.tmdb.org/t/p/w220_and_h330_face" + personne.profile_path
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .clickable { }
                            .padding(4.dp),
                        elevation = 10.dp
                    ) {
                        Column(Modifier.padding(4.dp)) {
                            if (personne.profile_path.isNullOrBlank()){
                            AsyncImage(
                                model = R.drawable.acteur,
                                contentDescription = "affiche",
                                modifier = Modifier
                                    .height(300.dp),
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
            }
        },

        bottomBar = {
            ParamBottomBar(navController)
        }
    )
}