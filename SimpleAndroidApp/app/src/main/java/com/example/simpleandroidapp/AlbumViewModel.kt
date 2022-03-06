package com.example.simpleandroidapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.simpleandroidapp.api.Album
import com.example.simpleandroidapp.api.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    var albums = mutableStateListOf<Album>()

    fun getAlbums(): Completable {
        return apiService.getAlbums()
            .subscribeOn(Schedulers.io())
            .doOnSuccess {
                albums.clear()
                albums.addAll(it)
                sortAlbums(false)
            }
            .ignoreElement()
    }

    fun sortAlbums(isDescending: Boolean) {
        if (isDescending) {
            albums.sortByDescending { it.title }
        } else {
            albums.sortBy { it.title }
        }
    }
}