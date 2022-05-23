package dev.nmgalo.task8.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiClient {

    private fun getJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(getJson().asConverterFactory("application/json".toMediaType()))
            .baseUrl("https://reqres.in/api/")
            .build()
    }

    fun usersService() = getRetrofitInstance().create(ApiService::class.java)
}
