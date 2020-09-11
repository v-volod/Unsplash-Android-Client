package com.volodymyrvoiko.screen.photos

import androidx.lifecycle.ViewModel
import com.volodymyrvoiko.api.Photo
import com.volodymyrvoiko.api.UnsplashAPI
import com.volodymyrvoiko.current
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PhotosViewModel(
    private val api: UnsplashAPI = current.api
) : ViewModel() {
    val photos: Flow<List<Photo>> = flow {
        emit(api.getPhotos())
    }
}