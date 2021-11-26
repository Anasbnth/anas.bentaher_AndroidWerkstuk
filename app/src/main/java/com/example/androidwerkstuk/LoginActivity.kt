package com.example.androidwerkstuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin : Button
    private lateinit var emailField : EditText
    private lateinit var passwordField : EditText
    private lateinit var goToRegisterButton : Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin = findViewById<Button>(R.id.button_login)
        goToRegisterButton = findViewById<Button>(R.id.button_goToRegisterPage)
        emailField = findViewById<EditText>(R.id.textfield_email_login)
        passwordField = findViewById<EditText>(R.id.textfield_password_login)
        auth = FirebaseAuth.getInstance();
        
        btnLogin.setOnClickListener {
            loginUser()
        }

        goToRegisterButton.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }



    }


    private fun loginUser() {
        val email = emailField.text.toString()
        val password = passwordField.text.toString()


        if(TextUtils.isEmpty(email)){
            emailField.setError("Email cannot be empty.")
            emailField.requestFocus()
            return
        }
        else if(TextUtils.isEmpty(password)){
            passwordField.setError("Password cannot be empty.")
            passwordField.requestFocus()
            return
        }
        else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"User logged in succesfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    } else {
                        Toast.makeText(this,"Log in Error: " + task.exception!!.message,
                            Toast.LENGTH_SHORT).show()

                    }
                }

        }
    }


}