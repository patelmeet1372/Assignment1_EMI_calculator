package com.example.assignment1_emi_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declare UI elements
    private lateinit var etMortgageAmount: EditText
    private lateinit var etTenure: EditText
    private lateinit var etInterestRate: EditText
    private lateinit var btnCalculateEMI: Button
    private lateinit var tvEMIResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Reference to your layout file

        // Initialize UI elements
        etMortgageAmount = findViewById(R.id.etMortgageAmount)
        etTenure = findViewById(R.id.etTenure)
        etInterestRate = findViewById(R.id.etInterestRate)
        btnCalculateEMI = findViewById(R.id.btnCalculateEMI)
        tvEMIResult = findViewById(R.id.tvEMIResult)

        // Set onClickListener for the button
        btnCalculateEMI.setOnClickListener {
            calculateEMI()
        }
    }

    // Function to calculate EMI
    private fun calculateEMI() {
        // Get user input
        val mortgageAmount = etMortgageAmount.text.toString().toDoubleOrNull()
        val tenureInYears = etTenure.text.toString().toDoubleOrNull()
        val annualInterestRate = etInterestRate.text.toString().toDoubleOrNull()

        // Validate inputs
        if (mortgageAmount == null || mortgageAmount <= 0) {
            tvEMIResult.text = "Please enter a valid mortgage amount."
            return
        }
        if (tenureInYears == null || tenureInYears <= 0) {
            tvEMIResult.text = "Please enter a valid tenure in years."
            return
        }
        if (annualInterestRate == null || annualInterestRate < 0) {
            tvEMIResult.text = "Please enter a valid interest rate."
            return
        }

        // Convert annual interest rate to monthly and decimal
        val monthlyInterestRate = annualInterestRate / 12 / 100
        val tenureInMonths = tenureInYears * 12

        // Calculate EMI
        val emi = (mortgageAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureInMonths)) /
                (Math.pow(1 + monthlyInterestRate, tenureInMonths) - 1)

        // Display the result
        tvEMIResult.text = String.format("Your monthly EMI is: %.2f", emi)
    }

}
