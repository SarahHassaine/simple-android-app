package com.example.simpleandroidapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.simpleandroidapp.api.Album
import com.example.simpleandroidapp.api.ApiService
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumViewModelTests {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @RelaxedMockK
    private lateinit var mockApiService: ApiService

    private lateinit var viewModel: AlbumViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
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
        every { mockApiService.getAlbums() } returns Single.just(albums)
        viewModel = AlbumViewModel(mockApiService)
    }

    @Test
    fun `getAlbums replaces current albums with new data`() {
        viewModel.albums.add(Album(1, 1, "album to be replaced"))

        viewModel.getAlbums().blockingAwait()

        assertThat(viewModel.albums.count()).isEqualTo(4)
    }

    @Test
    fun `sortAlbums sorts by title in ascending order when isDescending is false`() {
        viewModel.getAlbums().blockingAwait()

        viewModel.sortAlbums(isDescending = false)

        assertThat(viewModel.albums[0].id).isEqualTo(2)
        assertThat(viewModel.albums[1].id).isEqualTo(3)
        assertThat(viewModel.albums[2].id).isEqualTo(4)
        assertThat(viewModel.albums[3].id).isEqualTo(1)
    }

    @Test
    fun `sortAlbums sorts by title in descending order when isDescending is true`() {
        viewModel.getAlbums().blockingAwait()

        viewModel.sortAlbums(isDescending = true)

        assertThat(viewModel.albums[0].id).isEqualTo(1)
        assertThat(viewModel.albums[1].id).isEqualTo(4)
        assertThat(viewModel.albums[2].id).isEqualTo(3)
        assertThat(viewModel.albums[3].id).isEqualTo(2)
    }

}