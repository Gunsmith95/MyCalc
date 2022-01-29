package com.example.mycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mycalc.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        val view = bindingClass.root
        setContentView(view)

        bindingClass.zeroBTN.setOnClickListener { setTextField("0") }
        bindingClass.oneBTN.setOnClickListener { setTextField("1") }
        bindingClass.twoBTN.setOnClickListener { setTextField("2") }
        bindingClass.threeBTN.setOnClickListener { setTextField("3") }
        bindingClass.fourBTN.setOnClickListener { setTextField("4") }
        bindingClass.fiveBTN.setOnClickListener { setTextField("5") }
        bindingClass.sixBTN.setOnClickListener { setTextField("6") }
        bindingClass.sevenBTN.setOnClickListener { setTextField("7") }
        bindingClass.eightBTN.setOnClickListener { setTextField("8") }
        bindingClass.nineBTN.setOnClickListener { setTextField("9") }

        bindingClass.divideBTN.setOnClickListener { setTextField("/") }
        bindingClass.multiplyBTN.setOnClickListener { setTextField("*") }
        bindingClass.minusBTN.setOnClickListener { setTextField("-") }
        bindingClass.addBTN.setOnClickListener { setTextField("+") }
        bindingClass.pointBTN.setOnClickListener { setTextField(".") }
        bindingClass.exponentBTN.setOnClickListener { setTextField("^") }
        bindingClass.plusMinusBTN.setOnClickListener { setTextField("-") }

        bindingClass.parenthesesBTN.setOnClickListener {
            val cursorPos: Int = bindingClass.textField.selectionStart
            var openPar: Int = 0
            var closePar: Int = 0
            val textLen: Int = bindingClass.textField.text.length

            for (n in 0 until cursorPos) {
                if (bindingClass.textField.text.toString().substring(n, n + 1) == "(") {
                    openPar += 1
                }
                if (bindingClass.textField.text.toString().substring(n, n + 1) == ")") {
                    closePar += 1
                }
            }

            if (openPar == closePar || bindingClass.textField.text.toString()
                    .substring(textLen - 1, textLen) == "("
            ) {
                setTextField("(")
            } else if (closePar < openPar && bindingClass.textField.text.toString()
                    .substring(textLen - 1, textLen) != "("
            ) {
                setTextField(")")
            }
            bindingClass.textField.setSelection(cursorPos + 1)
        }

        bindingClass.clearBTN.setOnClickListener {
            bindingClass.textField.setText("")
        }

        bindingClass.backspaceBTN.setOnClickListener {
            val str = bindingClass.textField.text.toString()
            if (str.isNotEmpty()) {
                bindingClass.textField.setText(str.substring(0, str.length - 1))
            }
        }
        bindingClass.equalsBTN.setOnClickListener {
            try {
                res()
            } catch (e: Exception) {
                Log.d("Error", "message: ${e.message}")

            }
        }
    }

    private fun res() {
        val ex = ExpressionBuilder(bindingClass.textField.text.toString()).build()
        val result = ex.evaluate()
        val longRes = result.toLong()
        if (result == longRes.toDouble()) {

            bindingClass.textField.setText("$longRes")
        } else {
            bindingClass.textField.setText("$result")
        }
    }

    fun setTextField(str: String) {

        bindingClass.textField.append(str)
    }

}