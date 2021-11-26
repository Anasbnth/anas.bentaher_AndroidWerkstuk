package com.example.androidwerkstuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.androidwerkstuk.viewmodel.UserViewModel
import com.example.androidwerkstuk.entities.User
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity()
{
    private lateinit var auth: FirebaseAuth
    private lateinit var btnRegister: Button
    private lateinit var loginLink: TextView
    private lateinit var usernameField : EditText
    private lateinit var emailField : EditText
    private lateinit var nameField : EditText
    private lateinit var passwordField : EditText
    private lateinit var confirmPasswordField : EditText
    private lateinit var userViewModel: UserViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        btnRegister = findViewById<Button>(R.id.button_register)
        loginLink = findViewById<TextView>(R.id.textView_goToLoginPage)
        usernameField = findViewById<EditText>(R.id.textfield_username)
        emailField = findViewById<EditText>(R.id.textfield_email)
        nameField = findViewById<EditText>(R.id.textfield_name)
        passwordField = findViewById<EditText>(R.id.textfield_password)
        confirmPasswordField = findViewById<EditText>(R.id.textfield_confirmPwd)

        auth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener {
            createUser();
        }

        loginLink.setOnClickListener{
            startActivity(Intent(this,LoginActivity::class.java))
        }


    }

    private fun createUser() {
        val email = emailField.text.toString()
        val username = usernameField.text.toString()
        val name = nameField.text.toString()
        val password = passwordField.text.toString()
        val confirmPassword = confirmPasswordField.text.toString()

        if(TextUtils.isEmpty(username)){
            usernameField.setError("Email cannot be empty.")
            usernameField.requestFocus()
            return
        }

        else if(TextUtils.isEmpty(email)){
            emailField.setError("Email cannot be empty.")
            emailField.requestFocus()
            return
        }


        else if(TextUtils.isEmpty(name)){
            nameField.setError("Full name cannot be empty")
            nameField.requestFocus()
            return
        }

        else if(TextUtils.isEmpty(password)){
            passwordField.setError("Password cannot be empty")
            passwordField.requestFocus()
            return
        }

        else if(password.length < 6){
            passwordField.setError("Password must be greater than 6 characters")
            passwordField.requestFocus()
            return
        }

        else if(!(password.equals(confirmPassword))){
            confirmPasswordField.setError("Passwords does not match")
            confirmPasswordField.requestFocus()
            return
        }

        else {



            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = User(email,username,name)
                        userViewModel.addUser(user)
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,"User is registered succesfully",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,LoginActivity::class.java))
                    } else {
                        Toast.makeText(this,"Registration Error: " + task.exception!!.message,Toast.LENGTH_SHORT).show()

                    }
                }

        }
    }


}