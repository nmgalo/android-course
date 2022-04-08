package ge.edu.btu.task5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import ge.edu.btu.task5.data.AppDatabase
import ge.edu.btu.task5.data.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var runRange: EditText
    private lateinit var swimRange: EditText
    private lateinit var calorie: EditText
    private lateinit var stats: TextView
    private lateinit var save: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        runRange = findViewById(R.id.runRange)
        swimRange = findViewById(R.id.swimRange)
        calorie = findViewById(R.id.calorie)
        stats = findViewById(R.id.stats)
        save = findViewById(R.id.save)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "user_db"
        ).build()

        CoroutineScope(Dispatchers.IO).launch {
            setData()
        }

        save.setOnClickListener {
            if (validateInputs()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    database.userDao().insert(
                        User(
                            runRange = runRange.toDouble(),
                            swimRange = swimRange.toDouble(),
                            calorie = calorie.toDouble()
                        )
                    )
                    clearInputs()
                    setData()
                }
            }
        }
    }

    private suspend fun setData() {
        val userDao = database.userDao()
        val totalDistance = userDao.totalDistance()

        val avgRun = userDao.avgOfRunRange().toString()
        val avgSwim = userDao.avgOfSwimRange().toString()
        val avgCalorie = userDao.avgOfCalories().toString()

        withContext(Dispatchers.Main) {
            stats.text = getString(
                R.string.average_stats,
                avgRun,
                avgSwim,
                avgCalorie,
                totalDistance.toString()
            )
        }
    }

    private fun validateInputs(): Boolean {
        return runRange.text.toString().isEmpty() || swimRange.text.toString()
            .isEmpty() || calorie.text.toString().isEmpty()
    }

    private fun clearInputs() {
        runRange.setText("")
        swimRange.setText("")
        calorie.setText("")
    }

    private fun EditText.toDouble(): Double {
        return this.text.toString().toDouble()
    }
}
