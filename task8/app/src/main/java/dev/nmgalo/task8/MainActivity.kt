package dev.nmgalo.task8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.nmgalo.task8.data.ApiClient
import dev.nmgalo.task8.data.model.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var usersRecyclerView: RecyclerView

    private val adapter = UsersListAdapter {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra(USER_ID_EXTRA, it)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersRecyclerView = findViewById(R.id.usersRecyclerView)
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = adapter

        ApiClient.usersService().getUsers().enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                if (response.isSuccessful) response.body()?.data?.let(adapter::submitList)
            }

            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
            }
        })

    }

    companion object {
        const val USER_ID_EXTRA = "USER_ID"
    }
}
