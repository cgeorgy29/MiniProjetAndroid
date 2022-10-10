package com.example.appliprofil

import android.graphics.drawable.Icon
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Films (navController : NavController, viewModel: MainViewModel){
    val movies by viewModel.movies.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fav'App")},
                )
            },

        content = {
                if (movies.isEmpty()) viewModel.lastMovie()
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                ){
                    items(movies){
                            movie ->
                        val url = "https://image.tmdb.org/t/p/w220_and_h330_face" + movie.poster_path
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .clickable { }
                                .padding(4.dp),
                            elevation = 10.dp
                        ){
                            Column(Modifier.padding(4.dp)) {
                                AsyncImage(
                                    model = url ,
                                    contentDescription = "affiche",
                                    modifier = Modifier
                                        .height(300.dp),
                                    contentScale = ContentScale.Fit)
                                Text(text = movie.original_title, fontWeight = FontWeight.Bold, fontSize = 17.sp )
                                Text(text = movie.release_date, fontSize = 15.sp)
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