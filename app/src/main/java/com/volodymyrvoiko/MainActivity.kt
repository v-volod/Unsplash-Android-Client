package com.volodymyrvoiko

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.volodymyrvoiko.screen.photos.PhotosScreen
import com.volodymyrvoiko.ui.UnsplashTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashTheme {
                Surface(color = MaterialTheme.colors.background) {
                    PhotosScreen()
                }
            }
        }
    }
}