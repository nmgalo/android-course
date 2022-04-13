package ge.edu.btu.midterm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var amountInput: EditText
    private lateinit var nameOnCardInput: EditText
    private lateinit var creditCardNumberInput: EditText
    private lateinit var expireDateMonth: EditText
    private lateinit var expireDateYear: EditText
    private lateinit var cardCVVInput: EditText
    private lateinit var buyNowButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val now: Calendar = Calendar.getInstance()

        amountInput = findViewById(R.id.amountInput)
        nameOnCardInput = findViewById(R.id.nameOnCardInput)
        creditCardNumberInput = findViewById(R.id.creditCardNumberInput)
        expireDateMonth = findViewById(R.id.expireDateMonth)
        expireDateYear = findViewById(R.id.expireDateYear)
        cardCVVInput = findViewById(R.id.cardCVVInput)
        buyNowButton = findViewById(R.id.buyNowButton)

        buyNowButton.setOnClickListener {
            when {
                amountInput.text.toString().isEmpty() -> showAlert("Amount field is required")
                nameOnCardInput.text.toString().isEmpty() -> showAlert("Please enter name field")
                creditCardNumberInput.text.toString().length != 16 -> showAlert("Invalid card number")
                expireDateMonth.text.toString().isEmpty() || expireDateMonth.text.toString()
                    .isEmpty() -> showAlert("Expire date field is required")
                expireDateYear.text.toString().toInt() < now.get(Calendar.YEAR).toString()
                    .takeLast(2).toInt() -> showAlert("Card is expired")
                cardCVVInput.text.toString().isEmpty() -> showAlert("CVV is required")
                else -> {
                    submitPayment()
                }
            }
        }
    }

    private fun showAlert(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


    private fun submitPayment() {
        startActivity(
            Intent(this, PaymentActivity::class.java).apply {
                putExtra("amount", amountInput.text.toString())
                putExtra("name", nameOnCardInput.text.toString())
                putExtra("card_number", creditCardNumberInput.text.toString())
                putExtra(
                    "expire_date",
                    expireDateMonth.text.toString() + "/" + expireDateYear.text.toString()
                )
            }
        )
    }
}
