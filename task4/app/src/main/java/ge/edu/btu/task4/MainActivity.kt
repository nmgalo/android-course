package ge.edu.btu.task4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import ge.edu.btu.task4.databinding.ActivityMainBinding
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var resultTV: TextView
    private lateinit var operation: String
    private var result: Double = DEFAULT_VALUE
    private var firstOperand: Double = DEFAULT_VALUE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = ActivityMainBinding.inflate(layoutInflater)
        setContentView(view.root)
        view.onViewBind()
    }

    private fun ActivityMainBinding.onViewBind() {

        resultTV = resultTextView

        btn0.setOnClickListener(::onDigitClick)
        btn1.setOnClickListener(::onDigitClick)
        btn2.setOnClickListener(::onDigitClick)
        btn3.setOnClickListener(::onDigitClick)
        btn4.setOnClickListener(::onDigitClick)
        btn5.setOnClickListener(::onDigitClick)
        btn6.setOnClickListener(::onDigitClick)
        btn7.setOnClickListener(::onDigitClick)
        btn8.setOnClickListener(::onDigitClick)
        btn9.setOnClickListener(::onDigitClick)

        plus.setOnClickListener(::onOperationClick)
        minus.setOnClickListener(::onOperationClick)
        divide.setOnClickListener(::onOperationClick)
        multiply.setOnClickListener(::onOperationClick)

        calculate.setOnClickListener(::calculate)
        clear.setOnClickListener {
            onClear()
        }
    }

    private fun onOperationClick(view: View) {
        firstOperand = resultTV.text.toString().toDoubleOrNull() ?: error("Invalid operand value!")
        (view as? Button)?.let {
            operation = view.text.toString()
            resultTV.text = ""
        }
    }

    private fun onDigitClick(view: View) {
        (view as? Button)?.let {
            resultTV.text =
                getString(R.string.append_digit, resultTV.text.toString(), view.text.toString())
        }
    }

    private fun calculate(view: View) {
        (view as? Button)?.let {
            val secondOperand =
                resultTV.text.toString().toDoubleOrNull() ?: error("Invalid operand value!")
            when (operation) {
                "*" -> result = firstOperand * secondOperand
                "-" -> result = firstOperand - secondOperand
                "+" -> result = firstOperand + secondOperand
                "/" -> result = firstOperand / secondOperand
                "√" -> result = sqrt(resultTV.text.toString().toDouble())
            }
            resultTV.text = result.toString()
        }
    }

    private fun onClear() {
        firstOperand = DEFAULT_VALUE
        resultTV.text = ""
    }

    companion object {
        const val DEFAULT_VALUE = 0.0
    }

}

