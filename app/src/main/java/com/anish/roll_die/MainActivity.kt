package com.anish.roll_die

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.anish.roll_die.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private var isRollTwice = false

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize shared preferences
        sharedPreferences = getSharedPreferences("MyDicePrefs", Context.MODE_PRIVATE)

        // Set up dice spinner
        val diceOptions = arrayOf("4", "6", "8", "10", "12", "20")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, diceOptions)
        binding.spinnerDice.adapter = adapter

        // Set up roll button click listener
        binding.buttonRoll.setOnClickListener {
            if (isRollTwice) {
                // Roll the dice twice
                val selectedDice = binding.spinnerDice.selectedItem.toString()
                val result1 = rollDice(selectedDice.toInt())
                val result2 = rollDice(selectedDice.toInt())

                // Display the results
                binding.textViewResult.text = getString(
                    R.string.two_results,
                    result1.toString(),
                    result2.toString()
                )
            } else {
                // Roll the dice once
                val selectedDice = binding.spinnerDice.selectedItem.toString().toInt()
                val result = rollDice(selectedDice)

                // Display the result
                binding.textViewResult.text = getString(R.string.result, result.toString())
            }
        }

        // Set up clear button click listener
        binding.buttonClear.setOnClickListener {
            // Clear the result text
            binding.textViewResult.text = ""
        }

        // Set up roll twice checkbox click listener
        binding.checkBoxRollTwice.setOnClickListener {
            // Toggle the roll twice flag
            isRollTwice = !isRollTwice
        }
    }

    // Roll a dice with the given maximum value and return the result
    private fun rollDice(maxValue: Int): Int {
        val random = Random
        return random.nextInt(maxValue) + 1
    }


}