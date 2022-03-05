package com.example.simpleandroidapp.api
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("albums")
    fun getAlbums(): Single<List<Album>>
}