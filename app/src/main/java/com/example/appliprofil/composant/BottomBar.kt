package com.example.appliprofil.composant

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.appliprofil.R
import com.example.appliprofil.ui.theme.Gold
import com.example.appliprofil.ui.theme.MyRed


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
                label = { Text(text = item.title) },
                selectedContentColor = Gold,
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
    object Film : NavItem("Films", R.drawable.clap ,"films")
    object Serie : NavItem("Séries",R.drawable.series, "series")
    object Acteurs : NavItem("Stars",R.drawable.acteur,"stars")
}
