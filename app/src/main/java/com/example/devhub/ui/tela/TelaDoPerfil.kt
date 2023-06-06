package com.example.devhub.ui.tela

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
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

//barra de busca
@Composable
fun DefaultIcon (
    modifier: Modifier = Modifier,
    searchIcon: ImageVector = Icons.Default.Search,
    iconColor: Color = Color.White,
    contentDescription: String = "Magnifier Search Icon",
    onIconClickAction: () -> Unit = {}
) {
    IconButton(
        modifier = modifier,
        onClick = onIconClickAction
    ) {
        Icon(
            imageVector = searchIcon,
            contentDescription = contentDescription,
            tint = iconColor
        )
    }
}

@Composable
fun SearchIcon(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Filled.Search,
        contentDescription = "Search Icon",
        onIconClickAction = action
    )
}

@Composable
fun SearchLeadingIcon(action: () -> Unit = {}) {
    DefaultIcon(
        modifier = Modifier.alpha(ContentAlpha.medium),
        onIconClickAction = action
    )
}

@Composable
fun SearchTrailingIcon(action: () -> Unit = {}) {
    DefaultIcon(
        searchIcon = Icons.Default.Close,
        contentDescription = "Deactivate Search Icon",
        onIconClickAction = action
    )
}

@Composable
fun SearchTopBar(
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(modifier = Modifier.fillMaxWidth(),
            value = currentSearchText,
            onValueChange = { onSearchTextChanged(it) },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Digite nome do usuário aqui...",
                    color = Color.White
                )
            },
            textStyle = TextStyle( fontSize = typography.body1.fontSize),
            singleLine = true,
            leadingIcon = { SearchLeadingIcon() },
            trailingIcon = { SearchTrailingIcon {
                if (currentSearchText.isNotEmpty()) onSearchTextChanged("") else onSearchDeactivated() }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchDispatched(currentSearchText) } ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            )
        )
    }
}
@Composable
fun HomeTopBar(@StringRes topBarNameId: Int = R.string.empty, onSearchIconClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(id = topBarNameId) ) },
        actions = { SearchIcon(action = onSearchIconClicked) }
    )
}



@Composable
fun SearchableTopBar(
    isShowingSearchField: Boolean,
    currentSearchText: String,
    onSearchTextChanged: (String) -> Unit,
    onSearchDeactivated: () -> Unit,
    onSearchDispatched: (String) -> Unit,
    onSearchIconClicked: () -> Unit
) {
    when (isShowingSearchField) {
        true -> SearchTopBar(
            currentSearchText = currentSearchText,
            onSearchTextChanged = onSearchTextChanged,
            onSearchDeactivated = onSearchDeactivated,
            onSearchDispatched = onSearchDispatched
        )
        false -> HomeTopBar(topBarNameId = R.string.app_name, onSearchIconClicked = onSearchIconClicked)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DevHubTheme {
        TelaDePerfil("alexfelipe")
    }
}