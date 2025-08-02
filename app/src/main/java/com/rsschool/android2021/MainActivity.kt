package com.rsschool.android2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.rsschool.android2021.FirstFragment.Companion.newInstance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun openFirstFragment(previousNumber: Int) {
        val firstFragment: Fragment = newInstance(previousNumber)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, firstFragment)
        // TODO: invoke function which apply changes of the transaction
    }

    private fun openSecondFragment(min: Int, max: Int) {
        // TODO: implement it
    }
}
