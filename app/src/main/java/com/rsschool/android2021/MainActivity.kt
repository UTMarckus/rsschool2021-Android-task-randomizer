package com.rsschool.android2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(), FirstFragment.SendRangeListener, SecondFragment.SendRandomNumberListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFirstFragment(0)
    }

    private fun openFirstFragment(previousNumber: Int) {
        val firstFragment: Fragment = FirstFragment.newInstance(previousNumber)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, firstFragment)
        transaction.commit()
    }

    private fun openSecondFragment(min: Int, max: Int) {
        val secondFragment: Fragment = SecondFragment.newInstance(min, max)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, secondFragment)
        transaction.commit()
    }

    override fun onSendRange(min: Int, max: Int) {
        openSecondFragment(min, max)
    }

    override fun onSendRandom(randomNumber: Int) {
        openFirstFragment(randomNumber)
    }
}
