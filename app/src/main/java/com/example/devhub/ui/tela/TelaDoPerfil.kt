package com.example.devhub.ui.tela

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.devhub.R
import com.example.devhub.data.GitHubWebClient
import com.example.devhub.model.Dev
import com.example.devhub.ui.theme.DevHubTheme

@Composable
fun TelaDePerfil(
    usuario: String,
    webClient: GitHubWebClient = GitHubWebClient()
) {
    val buscaUsuario by webClient.buscaPerfilUsuario(usuario)
        .collectAsState(initial = null)
    buscaUsuario?.let { dev ->
        Perfil(dev)
    }
}

@Composable
private fun Perfil(dev: Dev) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                    Color(0xFF2d333b),
                    shape = RoundedCornerShape(
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .height(boxAltura)
        ) {
            AsyncImage(
                dev.imagem,
                contentDescription = "Foto do Perfil",
                placeholder = painterResource(R.drawable.usuario_foreground),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .offset(y = alturaImagem / 2)
                    .size(alturaImagem)
                    .align(Alignment.BottomCenter)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.height(alturaImagem / 2))
        Text(
            text = dev.nome,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "@"+dev.usuario,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = dev.sobre,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DevHubTheme {
        TelaDePerfil("alexfelipe")
    }
}