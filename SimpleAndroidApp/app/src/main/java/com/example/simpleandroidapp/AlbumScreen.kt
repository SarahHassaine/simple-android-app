package com.example.simpleandroidapp

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simpleandroidapp.api.Album
import com.example.simpleandroidapp.ui.theme.SimpleAndroidAppTheme

@Composable
fun AlbumScreen(albums: List<Album>, onSortClicked: (Boolean) -> Unit) {
    var isDescending by rememberSaveable { mutableStateOf(true) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isDescending) -180f else 0f,
        animationSpec = tween(durationMillis = 250)
    )
    Scaffold(topBar =
    {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.albums_title)) },
            actions = {
                IconButton(onClick = {
                    onSortClicked(isDescending)
                    isDescending = !isDescending
                }) {
                    Icon(
                        painterResource(id = R.drawable.ic_sort_white),
                        stringResource(id = R.string.albums_sort_button_content_description),
                        modifier = Modifier.rotate(rotationAngle)
                    )
                }
            }
        )
    }) {
        AlbumList(albums)
    }
}

@Composable
fun AlbumList(albums: List<Album>) {
    LazyColumn {
        items(albums) { album ->
            AlbumRow(album)
        }
    }
}

@Composable
fun AlbumRow(album: Album) {
    Text(text = album.title, Modifier.padding(16.dp))
    Divider()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimpleAndroidAppTheme {
        val albums = listOf(
            Album(userId = 1, id = 1, title = "quidem molestiae enim"),
            Album(userId = 1, id = 2, title = "non esse culpa molestiae omnis sed optio"),
            Album(userId = 1, id = 3, title = "omnis laborum odio"),
            Album(
                userId = 1,
                id = 4,
                title = "omnis laborum odio omnis laborum odio omnis laborum odio omnis laborum odio omnis laborum odio"
            )
        )
        AlbumScreen(albums) {}
    }
}