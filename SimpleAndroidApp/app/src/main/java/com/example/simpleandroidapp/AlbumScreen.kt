package com.example.simpleandroidapp

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simpleandroidapp.api.Album
import com.example.simpleandroidapp.ui.theme.SimpleAndroidAppTheme

@Composable
fun AlbumScreen(albums: List<Album>) {
    Scaffold(topBar =
    {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.albums_title)) },
        )
    }) {
        AlbumList(albums = albums)
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
            Album(userId = 1, id = 1, title = "omnis laborum odio"),
            Album(
                userId = 1,
                id = 1,
                title = "omnis laborum odio omnis laborum odio omnis laborum odio omnis laborum odio omnis laborum odio"
            )
        )
        AlbumScreen(albums)
    }
}