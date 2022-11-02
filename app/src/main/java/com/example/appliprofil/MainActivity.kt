package com.example.appliprofil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.appliprofil.pages.*
import com.example.appliprofil.ui.theme.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewmodel : MainViewModel by viewModels()
        setContent {
                val windowSizeClass = calculateWindowSizeClass(this)
                // A surface container using the 'background' color from the theme
                AppliProfilTheme() {
                    Surface(modifier = Modifier.fillMaxSize()){
                        AppNavControl(windowSizeClass,viewmodel)
                    }
                }

        }
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
            Films(navController,viewmodel,tailleSc)
        }
        composable("series"){
            Series(navController,viewmodel,tailleSc)
        }
        composable("stars"){
            Personnes(navController,viewmodel, tailleSc)
        }
        composable("detailfilm" + "/{id}"){ NavBackStack ->
            val id = NavBackStack.arguments?.getString("id")
            if (id != null) {
                DetailFilm(navController,id, viewmodel)
            }
        }
        composable("detailserie" + "/{id}"){ NavBackStack ->
            val id = NavBackStack.arguments?.getString("id")
            if (id != null){
                DetailSerie(navController, id, viewmodel)
            }
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
                selectedContentColor = MyRed,
                unselectedContentColor = Color.White,
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
object Acteurs : NavItem("Stars",R.drawable.acteur,"stars")
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
