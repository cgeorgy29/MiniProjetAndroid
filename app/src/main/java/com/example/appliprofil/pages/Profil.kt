package com.example.appliprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Profil(classe : WindowSizeClass, nav : NavController){
    when (classe.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Image_ppd()
                Titre("Georgy Corentin")
                Texte("Elève en 4è année du cycle ingénieur")
                Texte("Ecole d'ingénieur ISIS - INU Champolion")
                Spacer(Modifier.height(50.dp))

                Row {
                    Icon(painterResource(id = R.drawable.mail),
                        contentDescription = "mail",
                        modifier = Modifier.size(18.dp))

                    Texte(carac = "georgy.crt@gmail.com")
                }
                Row {
                    Icon(painterResource(id = R.drawable.favori),
                        contentDescription = "linkedin",
                        modifier = Modifier.size(18.dp))
                    Texte(carac = "www.linkedin.com/in/corentin-georgy")
                }
                Button(onClick = { nav.navigate("films")}) {
                    Text(text = "Start")
                }
            }
        }
        else -> {

            Row ( verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth() ){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image_ppd()
                    Titre("Georgy Corentin")
                    Texte("Elève en 4è année du cycle ingénieur")
                    Texte("Ecole d'ingénieur ISIS - INU Champolion")
                }
                Column {
                    Row {
                        Icon(painterResource(id = R.drawable.mail),
                            contentDescription = "mail",
                            modifier = Modifier.size(18.dp))


                        Texte(carac = "georgy.crt@gmail.com")
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painterResource(id = R.drawable.favori),
                            contentDescription = "linkedin",
                            modifier = Modifier.size(18.dp))

                        Texte(carac = "www.linkedin.com/in/corentin-georgy")
                    }
                    Spacer(Modifier.height(30.dp))
                    Button(onClick = {nav.navigate("films")}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "Start")
                    }
                }
            }
        }
    }
}

@Composable
fun Image_ppd() {
    if(isSystemInDarkTheme()){
        Image(painterResource(id = R.drawable.yorublack2), contentDescription = "photo de profil",
            Modifier
                .padding(5.dp)
                .clip(CircleShape)
                .border(color = Color.White, width = 2.dp, shape = CircleShape)
                .size(200.dp)
        )
    }
    else {
        Image(painterResource(id = R.drawable.yoruwhite), contentDescription = "photo de profil",
            Modifier
                .padding(5.dp)
                .clip(CircleShape)
                .border(color = Color.Black, width = 2.dp, shape = CircleShape)
                .size(200.dp)
        )
    }


}

@Composable
fun Titre(carac: String){
    Text(text = carac, fontSize = 21.sp)
}
@Composable
fun Texte(carac : String){
    Text(text = carac, fontSize = 14.sp, modifier = Modifier.padding(start = 6.dp))
}