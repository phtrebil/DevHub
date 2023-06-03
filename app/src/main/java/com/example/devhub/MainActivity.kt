package com.example.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.devhub.data.DevHubApi
import com.example.devhub.data.GitHubWebClient
import com.example.devhub.model.Dev
import com.example.devhub.ui.theme.DevHubTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val dev = Dev("", "", "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TelaDePerfil("phtrebil")
                }
            }
        }
    }

    @Composable
    fun TelaDePerfil(
        usuario: String,
        webClient: GitHubWebClient = GitHubWebClient()
    ) {
        val buscaUsuario by webClient.buscaPerfilUsuario(usuario)
            .collectAsState(initial = null)
        buscaUsuario?.let { dev ->
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
                    AsyncImage(
                        dev.imagem,
                        contentDescription = "Foto do Perfil",
                        placeholder = painterResource(id = R.drawable.usuario_foreground),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .offset(y = alturaImagem / 2)
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
                    Text(
                        text = "Pedro Henrique Perez Trebilcock",
                        style = MaterialTheme.typography.h6
                    )
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
    }

        @Preview(showBackground = true)
        @Composable
        fun DefaultPreview() {
            DevHubTheme {
                TelaDePerfil("phtrebil")
            }
        }
    }

