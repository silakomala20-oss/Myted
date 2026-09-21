package com.example.myted

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TopUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topup)

        val etAmount = findViewById<EditText>(R.id.etAmount)

        findViewById<MaterialButton>(R.id.btnConfirm).setOnClickListener {
            val amount = etAmount.text.toString().toLongOrNull() ?: 0L
            if (amount <= 0) {
                Toast.makeText(this, "Nominal salah kak~ ><", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            WalletManager.addBalance(this, amount)
            WalletManager.addTransaction(this, Transaction("TOPUP", amount, "Top Up Saldo"))
            Toast.makeText(this, "Top up sukses! 💖", Toast.LENGTH_SHORT).show()
            finish()
        }

        findViewById<MaterialButton>(R.id.btn10k).setOnClickListener {
            etAmount.setText("10000")
        }
        findViewById<MaterialButton>(R.id.btn50k).setOnClickListener {
            etAmount.setText("50000")
        }
        findViewById<MaterialButton>(R.id.btn100k).setOnClickListener {
            etAmount.setText("100000")
        }
    }
}