package dev.nmgalo.task7

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import dev.nmgalo.task7.model.CreateUserRequest
import dev.nmgalo.task7.model.CreateUserResponse
import dev.nmgalo.task7.model.UsersDTO
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/api/")
            .build()

        val service: ApiService = retrofit.create(ApiService::class.java)

        service.getUsers().enqueue(object : Callback<UsersDTO> {
            override fun onResponse(call: Call<UsersDTO>, response: Response<UsersDTO>) {
                Log.d("response from get users", response.toString())
            }

            override fun onFailure(call: Call<UsersDTO>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        service.createUser(CreateUserRequest("test", "dasda"))
            .enqueue(object : Callback<CreateUserResponse> {
                override fun onResponse(
                    call: Call<CreateUserResponse>,
                    response: Response<CreateUserResponse>
                ) {
                    Log.d("response from create users", response.toString())
                }

                override fun onFailure(call: Call<CreateUserResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }
}
