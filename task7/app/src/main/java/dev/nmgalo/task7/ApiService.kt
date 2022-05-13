package dev.nmgalo.task7

import dev.nmgalo.task7.model.CreateUserRequest
import dev.nmgalo.task7.model.CreateUserResponse
import dev.nmgalo.task7.model.UsersDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("users")
    fun getUsers(): Call<UsersDTO>

    @POST("users")
    fun createUser(@Body createUserRequest: CreateUserRequest): Call<CreateUserResponse>
}
