package com.example.appliprofil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
//import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.vector.ImageVector
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
                    Image(imageVector = Icons.Filled.MailOutline, contentDescription = "mail")
                    Texte(carac = "georgy.crt@gmail.com")
                }
                Row {
                    Image(imageVector = Icons.Filled.Star, contentDescription = "mail")
                    Texte(carac = "www.linkedin.com/in/corentin-georgy")
                }
                Button(onClick = { nav.navigate("films")}) {
                    Text(text = "Button")
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
                        Image(imageVector = Icons.Filled.MailOutline, contentDescription = "mail")
                        Texte(carac = "georgy.crt@gmail.com")
                    }
                    Row {
                        Image(imageVector = Icons.Filled.Star, contentDescription = "mail")
                        Texte(carac = "www.linkedin.com/in/corentin-georgy")
                    }
                    Spacer(Modifier.height(30.dp))
                    Button(onClick = {nav.navigate("films")}, modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(text = "Button")
                    }
                }


            }
        }
    }
}

@Composable
fun Image_ppd() {
    Image(painterResource(id = R.mipmap.yoruppd), contentDescription = "photo de profil",
        Modifier
            .clip(CircleShape)
            .size(200.dp)
    )
}

@Composable
fun Titre(carac: String){
    Text(text = carac, fontSize = 21.sp)
}
@Composable
fun Texte(carac : String){
    Text(text = carac, fontSize = 14.sp)
}