package com.example.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.devhub.ui.theme.DevHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TelaDePerfil()
                }
            }
        }
    }

    @Composable
    fun TelaDePerfil() {
        Column {
            val boxAltura = remember {
                150.dp
            }
            val alturaImagem = remember {
                boxAltura
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFF2d333b), shape = RoundedCornerShape(
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .height(boxAltura)
            ) {
                val profileImage = painterResource(R.drawable.perfil)
                Image(
                    painter = profileImage,
                    contentDescription = "Foto do Perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .offset(y = alturaImagem/2)
                        .size(alturaImagem)
                        .align(Alignment.BottomCenter)
                        .clip(CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(alturaImagem / 2))
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Pedro Henrique Perez Trebilcock", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "@phtrebil", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Desenvolvedor Android apaixonado por tecnologia e programação.",
                    style = MaterialTheme.typography.body2
                )
            }


        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        DevHubTheme {
            TelaDePerfil()
        }
    }
}
