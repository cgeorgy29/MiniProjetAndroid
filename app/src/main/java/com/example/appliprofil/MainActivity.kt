package com.example.appliprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
//import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appliprofil.ui.theme.AppliProfilTheme
import java.util.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel : MainViewModel by viewModels()
        setContent {
                val windowSizeClass = calculateWindowSizeClass(this)
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    AppNavControl(windowSizeClass,viewmodel)
            }
        }
    }
    override fun isUiContext(): Boolean {
        return super.isUiContext()
    }
}

@Composable
fun AppNavControl (tailleSc: WindowSizeClass, viewmodel: MainViewModel){
    val navController = rememberNavController()
    NavHost(modifier = Modifier, navController = navController ,startDestination = "profil"){
        composable("profil") {
            Profil(tailleSc,navController )
        }
        composable("films"){
            Films(navController,viewmodel)
        }
        composable("series"){
            Series(navController)
        }
        composable("acteurs"){
            Acteurs(navController)
        }
        composable("DetailFilm"){
            DetailFilm(navController)
        }
        composable("DetailSerie"){
            DetailSerie(navController)
        }

    }
}


@Composable
fun ParamBottomBar(navController: NavController){
    val items = listOf(
        NavItem.Film,
        NavItem.Serie,
        NavItem.Acteurs
    )
    BottomNavigation(
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon ={ Icon(painterResource(id = item.icon), contentDescription = item.title, Modifier .size(25.dp)) },
                label = { Text(text = item.title)},
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.7f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                navController.navigate(item.route){
                navController.graph.startDestinationRoute?.let {
                                route ->
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}



sealed class NavItem(var title:String, var icon:Int, var route:String){
object Film : NavItem("Films",R.drawable.clap ,"films")
object Serie : NavItem("Séries",R.drawable.series, "series")
object Acteurs : NavItem("Acteurs",R.drawable.acteur,"acteurs")
}

/*@Preview(showBackground = true)
@Composable
fun BottomBarPreview(){
    ParamBottomBar()
}*/



/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppliProfilTheme {
        Screen(windowSizeClass)
    }
}*/
