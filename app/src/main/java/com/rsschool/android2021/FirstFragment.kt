package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment(R.layout.fragment_first) {

    private lateinit var generateButton: Button
    private lateinit var previousResult: TextView
    private lateinit var listener: SendRangeListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as SendRangeListener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult.text = "Previous result: ${result.toString()}"

        generateButton.setOnClickListener {
            val min = view.findViewById<EditText>(R.id.min_value)?.text.toString()
            val max = view.findViewById<EditText>(R.id.max_value)?.text.toString()

            if (isValid(min, max)) listener.onSendRange(min.toInt(), max.toInt())
        }
    }

    private fun isValid(min: String, max: String): Boolean = when {
        min.isBlank() || max.isBlank() -> {
            Toast.makeText(context, "Please, input values", Toast.LENGTH_SHORT).show()
            false
        }

        min[0] == '-' || max[0] == '-' -> {
            Toast.makeText(context, "Error, values should be positive or 0", Toast.LENGTH_SHORT)
                .show()
            false
        }

        min.toIntOrNull() == null || max.toIntOrNull() == null -> {
            Toast.makeText(
                context,
                "Error, values should be in range [0..2147483647]",
                Toast.LENGTH_SHORT
            )
                .show()
            false
        }

        min.toInt() > max.toInt() -> {
            Toast.makeText(
                context,
                "Error, min should not be bigger than max",
                Toast.LENGTH_SHORT
            )
                .show()
            false
        }

        else -> true
    }

    interface SendRangeListener {
        fun onSendRange(min: Int, max: Int)
    }
    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}