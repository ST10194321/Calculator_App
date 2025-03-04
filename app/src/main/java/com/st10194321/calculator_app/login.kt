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

class login : AppCompatActivity() {

    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnLogin : Button
    private lateinit var btnRegister : Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //initializes FirebaseAuth
        auth = FirebaseAuth.getInstance()

        //bind views
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener{

            val i = Intent(this,register::class.java)
            startActivity(i)
        }



        //Login button functionality
        btnLogin.setOnClickListener{
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Email and Password can't be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Use Firebase to sign in user
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Login successful", Toast.LENGTH_SHORT).show()
                    val i = Intent(this,SplashActivity::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(this, "Login failed: ${task.exception?.message}",Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}