package com.example.devhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.devhub.ui.tela.SearchableTopBar
import com.example.devhub.ui.tela.TelaDePerfil
import com.example.devhub.ui.theme.DevHubTheme
import com.google.android.material.search.SearchBar

class MainActivity : ComponentActivity() {

    var usuario = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DevHubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SearchableTopBar()
                    TelaDePerfil(usuario = usuario)
                }
            }
        }
    }

}

