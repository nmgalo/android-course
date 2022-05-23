package dev.nmgalo.task8.data

import dev.nmgalo.task8.data.model.UserResponse
import dev.nmgalo.task8.data.model.UsersResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    fun getUsers(): Call<UsersResponse>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Long): Call<UserResponse>
}
