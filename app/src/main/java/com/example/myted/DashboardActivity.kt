package com.example.myted

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        findViewById<MaterialButton>(R.id.btnTransfer).setOnClickListener {
            startActivity(Intent(this, TransferActivity::class.java))
        }
        findViewById<MaterialButton>(R.id.btnTopUp).setOnClickListener {
            startActivity(Intent(this, TopUpActivity::class.java))
        }
        findViewById<MaterialButton>(R.id.btnHistory).setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        findViewById<TextView>(R.id.tvBalance).text =
            WalletManager.formatRupiah(WalletManager.getBalance(this))
    }
}