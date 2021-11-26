package com.example.androidwerkstuk

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager2.widget.ViewPager2
import com.example.androidwerkstuk.ui.main.SectionsPagerAdapter
import com.example.androidwerkstuk.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance();


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs,viewPager){
            tab,position ->
            when(position)
            {
                0 -> tab.setIcon(R.drawable.ic_baseline_event_available_24)
                1 -> tab.setIcon(R.drawable.ic_baseline_event_24)
                2 -> tab.setIcon(R.drawable.ic_baseline_search_24)
                3 -> tab.setIcon(R.drawable.ic_baseline_account_circle_24)

            }
        }.attach()
        val fab: FloatingActionButton = findViewById(R.id.button_redirect_createNewEvent)

        fab.setOnClickListener {
            startActivity(Intent(this, CreateNewEventActivity::class.java))
            
        }


    }



    public override fun onStart() {


        super.onStart()



        val currentUser: FirebaseUser? = auth.currentUser


        if (currentUser == null) {

            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

}