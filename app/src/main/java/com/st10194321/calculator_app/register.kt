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
    //delcare variables
    private lateinit var etEmailReg : EditText
    private lateinit var etPasswordReg : EditText
    private lateinit var etConPasswordReg : EditText
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


        btnReg = findViewById(R.id.btnReg)
        etEmailReg = findViewById(R.id.etEmailReg)
        etPasswordReg = findViewById(R.id.etPasswordReg)
        etConPasswordReg = findViewById(R.id.etConPasswordReg)
        btnLogin = findViewById(R.id.btnLogin)

        //button to send to login page
        btnLogin.setOnClickListener{

            val i = Intent(this,login::class.java)
            startActivity(i)
        }

        //button to confirm registration
        btnReg.setOnClickListener{
            val email = etEmailReg.text.toString().trim()
            val password = etPasswordReg.text.toString().trim()
            val conPass = etConPasswordReg.text.toString().trim()

            //validation to check if fields are filled
            if(email.isEmpty() || password.isEmpty()||conPass.isEmpty()){
                Toast.makeText(this, "Email and password can't be empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            //validation to check if passwords match
            if (password != conPass) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //use firebase to create new user
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                   task ->
                //if reg successful displays a message and takes user to login
                if(task.isSuccessful){
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    val i = Intent(this,login::class.java)
                    startActivity(i)
                    finish()
                    //if registration fails , prompts user to try again
                }else{
                    Toast.makeText(this,"Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}