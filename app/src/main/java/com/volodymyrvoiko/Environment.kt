package com.volodymyrvoiko

import com.volodymyrvoiko.api.UnsplashAPI
import com.volodymyrvoiko.api.unsplashRetrofitAPI

data class Environment(
    var api: UnsplashAPI = unsplashRetrofitAPI()
)

var current = Environment()