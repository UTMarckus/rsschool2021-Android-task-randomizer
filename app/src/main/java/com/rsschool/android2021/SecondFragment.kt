package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.rsschool.android2021.FirstFragment.SendRangeListener

class SecondFragment : Fragment(R.layout.fragment_second) {

    private lateinit var backButton: Button
    private lateinit var result: TextView
    private var randomNumber = 0
    private lateinit var listener: SendRandomNumberListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SendRandomNumberListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        randomNumber = generate(min, max)
        result.text = randomNumber.toString()

        backButton.setOnClickListener {
            listener.onSendRandom(randomNumber)
        }

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                listener.onSendRandom(randomNumber)
            }
        })
    }

    private fun generate(min: Int, max: Int): Int = (min..max).random()

    interface SendRandomNumberListener {
        fun onSendRandom(randomNumber: Int)
    }
    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            val args = Bundle()
            args.putInt(MIN_VALUE_KEY, min)
            args.putInt(MAX_VALUE_KEY, max)
            fragment.arguments = args
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
}