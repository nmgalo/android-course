package ge.edu.btu.midterm

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PaymentActivity : AppCompatActivity() {


    private lateinit var amountValue: TextView
    private lateinit var nameValue: TextView
    private lateinit var numberValue: TextView
    private lateinit var expireDateValue: TextView
    private lateinit var pay: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        amountValue = findViewById(R.id.amountValue)
        nameValue = findViewById(R.id.nameValue)
        numberValue = findViewById(R.id.numberValue)
        expireDateValue = findViewById(R.id.expireDateValue)
        pay = findViewById(R.id.pay)

        amountValue.text = intent.extras?.get("amount").toString()
        nameValue.text = intent.extras?.get("name").toString()
        numberValue.text = intent.extras?.get("card_number").toString()
        expireDateValue.text = intent.extras?.get("expire_date").toString()

        pay.setOnClickListener {
            onBackPressed()
        }
    }
}
