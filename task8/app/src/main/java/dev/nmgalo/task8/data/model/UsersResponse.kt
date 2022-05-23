package dev.nmgalo.task8.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UsersResponse(
    val data: List<User>
)
