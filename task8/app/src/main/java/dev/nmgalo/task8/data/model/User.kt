package dev.nmgalo.task8.data.model

import com.squareup.picasso.Picasso
import dev.nmgalo.task8.databinding.UserItemBinding
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val email: String,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    val avatar: String,
) {
    fun bindTo(binding: UserItemBinding, onUserClick: () -> Unit) {
        binding.root.setOnClickListener { onUserClick() }
        binding.userName.text = "$firstName $lastName"
        binding.userEmail.text = email
        Picasso.get().load(avatar).into(binding.userProfile)
    }
}
