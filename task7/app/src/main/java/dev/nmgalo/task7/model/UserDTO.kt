package dev.nmgalo.task7.model

import com.google.gson.annotations.SerializedName

data class UserDTO(
    val id: Long,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)
