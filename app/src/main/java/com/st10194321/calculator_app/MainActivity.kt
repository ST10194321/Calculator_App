package com.st10194321.calculator_app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() { //Where
    private lateinit var btnOne : Button
    private lateinit var etValOne : EditText
    private lateinit var etValTwo : EditText
    private lateinit var tvResult : TextView
    private lateinit var btnPlus : Button
    private lateinit var btnMinus : Button
    private lateinit var btnTimes : Button
    private lateinit var btnDivide : Button
    var sum: Double= 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnOne = findViewById(R.id.button)
        etValOne = findViewById(R.id.etValOne)
        etValTwo = findViewById(R.id.etValTwo)
        tvResult = findViewById(R.id.tvRes)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnTimes = findViewById(R.id.btnTimes)
        btnDivide = findViewById(R.id.btnDivide)

        btnOne.setOnClickListener {
            // Use toDoubleOrNull to avoid crashes from invalid input.

            tvResult.text = sum.toString()

        }
        btnPlus.setOnClickListener{
            val num1 = etValOne.text.toString().toDouble()
            val num2 = etValTwo.text.toString().toDouble()
             sum = num1 + num2
        }

        btnMinus.setOnClickListener{
            val num1 = etValOne.text.toString().toDouble()
            val num2 = etValTwo.text.toString().toDouble()
             sum = num1 - num2
        }
        btnTimes.setOnClickListener{
            val num1 = etValOne.text.toString().toDouble()
            val num2 = etValTwo.text.toString().toDouble()
            sum = num1 * num2
        }
        btnDivide.setOnClickListener{
            val num1 = etValOne.text.toString().toDouble()
            val num2 = etValTwo.text.toString().toDouble()
             sum = num1 / num2
        }

    }
}