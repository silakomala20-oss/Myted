package com.example.myted

data class Transaction(
    val type: String,
    val amount: Long,
    val note: String,
    val timestamp: Long = System.currentTimeMillis()
)