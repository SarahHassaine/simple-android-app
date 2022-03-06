package com.example.simpleandroidapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.simpleandroidapp.api.Album
import com.example.simpleandroidapp.ui.theme.SimpleAndroidAppTheme
import org.junit.Rule
import org.junit.Test

class AlbumScreenTests {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun albumScreen_displays_sortButtonAndAlbums() {
        composeTestRule.setContent {
            SimpleAndroidAppTheme {
                val albums = listOf(
                    Album(userId = 1, id = 1, title = "Frodo"),
                    Album(userId = 1, id = 2, title = "Gandalf"),
                    Album(userId = 1, id = 3, title = "Bilbo")
                )
                AlbumScreen(albums) {}
            }
        }

        composeTestRule.onNodeWithContentDescription("Sort button").assertExists()
        composeTestRule.onNodeWithText("Frodo").assertExists()
        composeTestRule.onNodeWithText("Gandalf").assertExists()
        composeTestRule.onNodeWithText("Bilbo").assertExists()
    }
}