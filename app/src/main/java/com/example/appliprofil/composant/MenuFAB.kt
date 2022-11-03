package com.example.appliprofil.composant

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appliprofil.R


@Composable
fun ExpendableFABMenu(
    expandedInitially: Boolean = false,
    navController: NavController
) {
    val (expanded, onExpandedChanged) = remember {
        mutableStateOf(expandedInitially)
    }
    Crossfade(targetState = expanded) { isSearchFieldVisible ->
        when (isSearchFieldVisible) {
            true -> ExpendedMenu(
                onExpandedChanged = onExpandedChanged,
                navController = navController
            )

            false -> CollapsedMenu(
                onExpandedChanged = onExpandedChanged,
            )
        }
    }
}

@Composable
fun CollapsedMenu(
    onExpandedChanged: (Boolean) -> Unit
    ){
    FloatingActionButton(onClick = {onExpandedChanged(true)},
        modifier = Modifier.padding(5.dp,0.dp)
    ) {
        Icon(
            painterResource(id = R.drawable.menu),
            contentDescription = "menu icon",
            Modifier.width(25.dp)
        )
    }
}



@Composable
fun ExpendedMenu(
    onExpandedChanged: (Boolean) -> Unit,
    navController: NavController
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(5.dp,0.dp)) {
        FloatingActionButton(
            modifier = Modifier.size(56.dp),
            onClick = {
                onExpandedChanged(false)
            }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "fermeture",
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        FloatingActionButton(modifier = Modifier.size(45.dp),
            onClick = { navController.navigate("films")}) {
            Icon(
                painterResource(id = R.drawable.clap),
                contentDescription = "Icon movie",
                modifier = Modifier.fillMaxSize(0.7f),

            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        FloatingActionButton(modifier = Modifier.size(45.dp),
            onClick = { navController.navigate("series")}) {
            Icon(
                painterResource(id = R.drawable.series),
                contentDescription = "Icon serie",
                modifier = Modifier.fillMaxSize(0.8f),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        FloatingActionButton(modifier = Modifier.size(45.dp),
            onClick = { navController.navigate("stars")}) {
            Icon(
                painterResource(id = R.drawable.acteur),
                contentDescription = "Icon personne",
                modifier = Modifier.fillMaxSize(0.8f)
            )
        }
    }
}


