package com.volodymyrvoiko.api

data class Photo(
    val id: String,
    val description: String?,
    val user: User,
    val urls: Urls,
    val width: Int,
    val height: Int
) {
    data class Urls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    )
}

val Photo.aspect: Float get() = width.toFloat() / height.toFloat()