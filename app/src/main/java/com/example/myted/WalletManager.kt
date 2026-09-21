package com.example.myted

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object WalletManager {
    private const val PREF = "vannpay_pref"
    private const val KEY_BALANCE = "balance"
    private const val KEY_HISTORY = "history"
    private val gson = Gson()

    fun getBalance(ctx: Context): Long {
        val sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        return sp.getLong(KEY_BALANCE, 50000L)
    }

    fun setBalance(ctx: Context, amount: Long) {
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit().putLong(KEY_BALANCE, amount).apply()
    }

    fun addBalance(ctx: Context, amount: Long) {
        setBalance(ctx, getBalance(ctx) + amount)
    }

    fun getHistory(ctx: Context): MutableList<Transaction> {
        val sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
        val json = sp.getString(KEY_HISTORY, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Transaction>>() {}.type
        return try {
            gson.fromJson(json, type)
        } catch (e: Exception) {
            mutableListOf()
        }
    }

    fun addTransaction(ctx: Context, trx: Transaction) {
        val list = getHistory(ctx)
        list.add(0, trx)
        val json = gson.toJson(list)
        ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE)
            .edit().putString(KEY_HISTORY, json).apply()
    }

    fun formatRupiah(amount: Long): String {
        return "Rp " + String.format("%,d", amount).replace(',', '.')
    }
}