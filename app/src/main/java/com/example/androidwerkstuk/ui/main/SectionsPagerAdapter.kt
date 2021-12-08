package com.example.androidwerkstuk.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.androidwerkstuk.*
import com.example.androidwerkstuk.fragment.CreatedEventsFragment
import com.example.androidwerkstuk.fragment.ProfileFragment
import com.example.androidwerkstuk.fragment.SearchEventFragment
import com.example.androidwerkstuk.fragment.SubscribedEventsFragment



/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(fm: FragmentManager,lifecycle:Lifecycle) :
    FragmentStateAdapter(fm, lifecycle)
{


    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null

        when (position) {
            0 -> fragment = SubscribedEventsFragment()
            1-> fragment = CreatedEventsFragment()
            2-> fragment = SearchEventFragment()
            3-> fragment = ProfileFragment()

            else -> { // Note the block
                print("position is neither 0 nor 1")
            }
        }
        return fragment!!
    }
}