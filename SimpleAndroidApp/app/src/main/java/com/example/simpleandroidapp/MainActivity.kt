package com.example.simpleandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.simpleandroidapp.ui.theme.SimpleAndroidAppTheme
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAlbums()
        setContent {
            SimpleAndroidAppTheme {
                AlbumScreen(viewModel.albums)
            }
        }
    }

    private fun loadAlbums() {
        disposable.add(viewModel.getAlbums().subscribe())
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
}
