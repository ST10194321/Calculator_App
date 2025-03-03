package com.st10194321.calculator_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class register : AppCompatActivity() {
    private lateinit var etEmailReg : EditText
    private lateinit var etPasswordReg : EditText
    private lateinit var btnReg : Button
    private lateinit var btnLogin : Button

    //declare firebase auth as variable
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //use firebase auth to get instance of
        auth = FirebaseAuth.getInstance()

        //in
        btnReg = findViewById(R.id.btnReg)
        etEmailReg = findViewById(R.id.etEmailReg)
        etPasswordReg = findViewById(R.id.etPasswordReg)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener{

            val i = Intent(this,login::class.java)
            startActivity(i)
        }

        btnReg.setOnClickListener{
            val email = etEmailReg.text.toString().trim()
            val password = etPasswordReg.text.toString().trim()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Email and password can't be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //use firebase to create new user
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}