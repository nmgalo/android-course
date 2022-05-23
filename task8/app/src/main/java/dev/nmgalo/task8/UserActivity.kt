package dev.nmgalo.task8

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import dev.nmgalo.task8.MainActivity.Companion.USER_ID_EXTRA
import dev.nmgalo.task8.data.ApiClient
import dev.nmgalo.task8.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserActivity : AppCompatActivity() {

    private lateinit var userImage: ImageView
    private lateinit var userFullName: TextView
    private lateinit var userEmail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userImage = findViewById(R.id.userImage)
        userFullName = findViewById(R.id.userFullName)
        userEmail = findViewById(R.id.userEmail)

        val userId =
            intent?.extras?.getLong(USER_ID_EXTRA) ?: error("userId ის გარეშე არ მოხივიდე!!")

        ApiClient.usersService().getUserById(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        Picasso.get()
                            .load(it.avatar)
                            .into(userImage)

                        userFullName.text = "${it.firstName} ${it.lastName}"
                        userEmail.text = it.email
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            }
        })
    }
}
