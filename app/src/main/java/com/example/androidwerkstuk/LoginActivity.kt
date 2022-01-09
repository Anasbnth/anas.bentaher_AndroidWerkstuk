package com.example.androidwerkstuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.androidwerkstuk.entities.Quote
import com.example.androidwerkstuk.API.QuoteController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin : Button
    private lateinit var emailField : EditText
    private lateinit var passwordField : EditText
    private lateinit var quoteField : TextView
    private lateinit var goToRegisterButton : Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        btnLogin = findViewById<Button>(R.id.button_login)
        goToRegisterButton = findViewById<Button>(R.id.button_goToRegisterPage)
        emailField = findViewById<EditText>(R.id.textfield_email_login)
        passwordField = findViewById<EditText>(R.id.textfield_password_login)
        quoteField = findViewById<TextView>(R.id.qotd)
        auth = FirebaseAuth.getInstance();

        val retrofit = Retrofit.Builder().baseUrl("https://type.fit/api/").addConverterFactory(
            GsonConverterFactory.create())
            .build()

        val quoteController = retrofit.create(QuoteController::class.java)
        val call = quoteController.getPosts()
        call.enqueue(object : Callback<List<Quote>> {
            override fun onResponse(call: Call<List<Quote>>, response: Response<List<Quote>>) {
                if(!response.isSuccessful())
                {
                    println(response.code())
                }
                val quotes = response.body()
                val rnds = (0..10).random()
                quoteField.setText("``" + quotes!![rnds].text + "``")
            }

            override fun onFailure(call: Call<List<Quote>>, t: Throwable) {
                println(t.message)
            }

        })


        
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
            emailField.setError("Email " + R.string.notEmpty + ".")
            emailField.requestFocus()
            return
        }
        else if(TextUtils.isEmpty(password)){
            passwordField.setError(R.string.password.toString() + " " + R.string.notEmpty + ".")
            passwordField.requestFocus()
            return
        }
        else{
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(this,R.string.userLogged, Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    } else {
                        Toast.makeText(this,"Log in Error: " + task.exception!!.message,
                            Toast.LENGTH_SHORT).show()

                    }
                }

        }
    }


}