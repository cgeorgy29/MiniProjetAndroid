package com.example.appliprofil.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.appliprofil.MainViewModel
import com.example.appliprofil.R
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun DetailSerie (navController : NavController, idserie:String, viewModel: MainViewModel){
    val serie = viewModel.oneserie.collectAsState().value
    viewModel.oneSerie(idserie)
    Scaffold(
        topBar = {
            TopAppBar (
                title = {Text(text = "Détail Serie")
                },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate("series")}) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "back icon",
                            Modifier.size(25.dp))
                    }
                }
            )

        },

        content = {
            if (serie == null){
                Text(text = "Une erreur c'est produite, retournez à la page précédente")}
            else {
                LazyColumn(Modifier.padding(5.dp)) {
                    item {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = serie.name,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.h4

                            )
                            val url =
                                "https://image.tmdb.org/t/p/original" + serie.backdrop_path

                            AsyncImage(
                                model = url,
                                contentDescription = "affiche",
                                modifier = Modifier
                                    .fillMaxSize(0.95f)
                                    .clip(RoundedCornerShape(20.dp)),
                                contentScale = ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.size(10.dp))
                            Row {
                                AsyncImage(
                                    model = "https://image.tmdb.org/t/p/original" + serie.poster_path,
                                    contentDescription = "poster",
                                    modifier = Modifier
                                        .height(200.dp)
                                        .padding(10.dp, 0.dp),
                                    contentScale = ContentScale.Fit
                                )
                                Column{
                                        Text(
                                            text = "Première parution : " + serie.first_air_date,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(text = "Dernier épisode le : " + serie.last_air_date,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    when(serie.status){
                                        "Returning Series" -> Text(text = "Statut : En Cours...")
                                        "Ended" -> Text(text = "Statut : Terminé !")
                                        "In Production" -> Text(text = "Statut : En Production")
                                        else -> Text(text = "Statut : Inconnu...")
                                    }
                                    Text(text = "Genre : ", fontWeight = FontWeight.SemiBold)
                                    for (genre in serie.genres) {
                                        Text(
                                            text = "- " + genre.name,
                                            fontStyle = FontStyle.Italic
                                        )
                                    }
                                }
                            }
                            Divider(
                                color = Color.Black, modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(0.dp, 15.dp)
                            )

                        }
                        Text(text = "Synopsis", style = MaterialTheme.typography.h4)
                        Text(text = serie.overview, textAlign = TextAlign.Justify)

                        Divider(
                            color = Color.Black, modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .padding(0.dp, 15.dp)
                        )
                        Text(text = "Casting", style = MaterialTheme.typography.h4)
                    }
                    item {
                        val itemSize: Dp = LocalConfiguration.current.screenWidthDp.dp / 2 - 8.dp
                        FlowRow(mainAxisSize = SizeMode.Expand, mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly) {
                            serie.credits.cast.forEach{ acteur ->
                                Card(
                                    modifier = Modifier
                                        .width(itemSize)
                                        .fillMaxHeight()
                                        .padding(4.dp),
                                    elevation = 10.dp
                                ) {
                                    Column(Modifier.padding(4.dp)) {


                                        if (acteur.profile_path.isNullOrBlank()){
                                            AsyncImage(
                                                model = R.drawable.acteur,
                                                contentDescription = "affiche",
                                                modifier = Modifier
                                                    .height(300.dp),
                                                contentScale = ContentScale.Fit
                                            )
                                        }
                                        else {AsyncImage(
                                            model = "https://image.tmdb.org/t/p/original" + acteur.profile_path ,
                                            contentDescription = "affiche",
                                            modifier = Modifier
                                                .height(300.dp),
                                            contentScale = ContentScale.Fit
                                        ) }

                                        Text(
                                            text = acteur.name,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 17.sp
                                        )
                                        Text(text = acteur.character, fontSize = 15.sp)
                                    }
                                }
                            }
                        }
                    }


                }

            }

        },
    )
}
