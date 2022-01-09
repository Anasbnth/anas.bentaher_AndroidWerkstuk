package com.example.androidwerkstuk.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment


import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidwerkstuk.R
import com.example.androidwerkstuk.viewmodel.UserViewModel
import com.example.androidwerkstuk.entities.User
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent.getIntent

import android.content.Intent
import androidx.fragment.app.activityViewModels
import com.example.androidwerkstuk.LoginActivity
import com.example.androidwerkstuk.MainActivity
import com.example.androidwerkstuk.UpdateEventActivity
import com.example.androidwerkstuk.ui.main.PageViewModel
import com.example.androidwerkstuk.ui.main.PlaceholderFragment


class ProfileFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userViewModel: UserViewModel


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance();
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)



        val fragmentView: View = inflater.inflate(R.layout.fragment_profile,container,false)


        val username = fragmentView.findViewById<EditText>(R.id.textfield_username_profile)
        val email = fragmentView.findViewById<EditText>(R.id.textfield_email_profile)
        val name = fragmentView.findViewById<EditText>(R.id.textfield_name_profile)
        val updateBtn = fragmentView.findViewById<Button>(R.id.button_update_profile)
        val logout = fragmentView.findViewById<Button>(R.id.button_logout)
        email.setText(auth.currentUser!!.email)




        userViewModel.getUserByEmail(auth.currentUser!!.email.toString())
            ?.observe(viewLifecycleOwner, Observer { user ->
                username.setText(user?.username)
                name.setText(user?.name)
                email.setText(auth.currentUser!!.email)
            })

        updateBtn.setOnClickListener {
            val user = User(email.text.toString(),username.text.toString(),name.text.toString())
            userViewModel.updateUser(user)
            Toast.makeText(this.context,R.string.userUpdated, Toast.LENGTH_SHORT).show()
        }

        logout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this.activity, MainActivity::class.java))

        }


        return fragmentView
    }
}
