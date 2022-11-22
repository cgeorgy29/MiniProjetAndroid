package com.example.appliprofil.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailFilm (navController : NavController,idmovie:String, viewModel: MainViewModel, classe : WindowSizeClass) {
    val movie = viewModel.onemovie.collectAsState().value
    val classLargeur = classe.widthSizeClass

    viewModel.oneMovie(idmovie)

    when (classLargeur) {
        WindowWidthSizeClass.Compact -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = "Détail film")
                        },
                        navigationIcon = {
                            IconButton(onClick = { navController.navigate("films") }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.back),
                                    contentDescription = "back icon",
                                    Modifier.size(25.dp)
                                )
                            }
                        }
                    )
                },


                content = {
                    if (movie == null) {
                        Text(text = "Une erreur c'est produite, retourner à la page précédente")
                    } else {
                        LazyColumn(Modifier.padding(5.dp)) {
                            item {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = movie.title,
                                        textAlign = TextAlign.Center,
                                        fontWeight = FontWeight.ExtraBold,
                                        modifier = Modifier.fillMaxWidth(),
                                        style = MaterialTheme.typography.h4
                                    )
                                    AsyncImage(
                                        model = "https://image.tmdb.org/t/p/original" + movie.backdrop_path,
                                        contentDescription = "affiche",
                                        modifier = Modifier
                                            .fillMaxSize(0.95f)
                                            .clip(RoundedCornerShape(20.dp)),
                                        contentScale = ContentScale.Fit
                                    )
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Row {
                                        AsyncImage(
                                            model = "https://image.tmdb.org/t/p/original" + movie.poster_path,
                                            contentDescription = "poster",
                                            modifier = Modifier
                                                .height(200.dp)
                                                .padding(10.dp, 0.dp),
                                            contentScale = ContentScale.Fit
                                        )
                                        Column {
                                            Text(
                                                text = "Date de sortie : " + movie.release_date,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Text(
                                                text = "Genre : ",
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            for (genre in movie.genres) {
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
                                Text(text = movie.overview, textAlign = TextAlign.Justify)

                                Divider(
                                    color = Color.Black, modifier = Modifier
                                        .fillMaxWidth(0.8f)
                                        .padding(0.dp, 15.dp)
                                )
                                Text(text = "Casting", style = MaterialTheme.typography.h4)
                            }
                            item {
                                val itemSize: Dp =
                                    LocalConfiguration.current.screenWidthDp.dp / 2 - 8.dp
                                FlowRow(
                                    mainAxisSize = SizeMode.Expand,
                                    mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
                                ) {
                                    movie.credits.cast.forEach { acteur ->
                                        Card(
                                            modifier = Modifier
                                                .width(itemSize)
                                                .fillMaxHeight()
                                                .padding(4.dp),
                                            elevation = 10.dp
                                        ) {
                                            Column(Modifier.padding(4.dp)) {
                                                if (acteur.profile_path.isNullOrBlank()) {
                                                    AsyncImage(
                                                        model = R.drawable.acteur,
                                                        contentDescription = "affiche",
                                                        modifier = Modifier
                                                            .height(300.dp),
                                                        contentScale = ContentScale.Fit
                                                    )
                                                } else {
                                                    AsyncImage(
                                                        model = "https://image.tmdb.org/t/p/original" + acteur.profile_path,
                                                        contentDescription = "affiche",
                                                        modifier = Modifier
                                                            .height(300.dp),
                                                        contentScale = ContentScale.Fit
                                                    )
                                                }
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
        else -> {
            if (movie == null) {
                Text(text = "Une erreur c'est produite, retourner à la page précédente")
            } else {
                LazyRow() {
                    item {
                        Column() {
                            Row {
                                FloatingActionButton(
                                    modifier = Modifier.size(45.dp),
                                    onClick = { navController.navigate("films") }) {
                                    Icon(
                                        painterResource(id = R.drawable.back),
                                        contentDescription = "Icon back",
                                        modifier = Modifier.fillMaxSize(0.7f),
                                        )
                                }
                                Text(
                                    text = movie.title,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.ExtraBold,
                                    modifier = Modifier.fillMaxWidth()
                                     .padding(20.dp, 0.dp),
                                    style = MaterialTheme.typography.h4
                                )

                            }
                            Row {
                                    Column {
                                        AsyncImage(
                                            model = "https://image.tmdb.org/t/p/original" + movie.poster_path,
                                            contentDescription = "poster",
                                            modifier = Modifier
                                                .height(300.dp)
                                                .padding(10.dp, 5.dp)
                                                .clip(RoundedCornerShape(20.dp)),
                                            contentScale = ContentScale.Fit
                                        )
                                    }

                                    Column {
                                        Text(
                                            text = "Date de sortie : " + movie.release_date,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = "Genre : ",
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        for (genre in movie.genres) {
                                            Text(
                                                text = "- " + genre.name,
                                                fontStyle = FontStyle.Italic
                                            )
                                        }
                                        Text(text = "Synopsis", style = MaterialTheme.typography.h4)
                                        Text(
                                            text = movie.overview,
                                            textAlign = TextAlign.Justify,
                                            modifier = Modifier.fillParentMaxWidth(0.7f),
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }

                                }
                            }

                        }

                    }

                }
            }
        }
    }

