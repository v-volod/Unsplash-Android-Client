package com.volodymyrvoiko.api

data class User(
    val id: String,
    val name: String,
    val profileImage: ProfileImage
) {
    data class ProfileImage(
        val small: String,
        val medium: String,
        val large: String
    )
}