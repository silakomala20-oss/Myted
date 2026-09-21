package com.example.myted

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class TransferActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)

        val etTarget = findViewById<EditText>(R.id.etTarget)
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val etNote = findViewById<EditText>(R.id.etNote)

        findViewById<MaterialButton>(R.id.btnSend).setOnClickListener {
            val target = etTarget.text.toString().trim()
            val amountStr = etAmount.text.toString().trim()
            val note = etNote.text.toString().ifBlank { "Transfer ke $target" }

            if (target.isEmpty() || amountStr.isEmpty()) {
                Toast.makeText(this, "Isi dulu sayang~ ><", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountStr.toLongOrNull() ?: 0L
            val balance = WalletManager.getBalance(this)

            if (amount <= 0) {
                Toast.makeText(this, "Nominal tidak valid kak~", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (amount > balance) {
                Toast.makeText(this, "Saldo kurang master~ :(", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            WalletManager.setBalance(this, balance - amount)
            WalletManager.addTransaction(this, Transaction("TRANSFER", -amount, note))
            Toast.makeText(this, "Berhasil transfer ke $target! ✨", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}