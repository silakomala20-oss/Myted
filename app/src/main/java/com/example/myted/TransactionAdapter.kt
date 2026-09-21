package com.example.myted

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.abs

class TransactionAdapter(private val list: List<Transaction>) :
    RecyclerView.Adapter<TransactionAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val tvType: TextView = v.findViewById(R.id.tvType)
        val tvNote: TextView = v.findViewById(R.id.tvNote)
        val tvAmount: TextView = v.findViewById(R.id.tvAmount)
        val tvDate: TextView = v.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_transaction, parent, false)
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val t = list[position]
        holder.tvType.text = t.type
        holder.tvNote.text = t.note
        holder.tvAmount.text = (if (t.amount >= 0) "+ " else "- ") +
                WalletManager.formatRupiah(abs(t.amount))
        holder.tvAmount.setTextColor(
            if (t.amount >= 0) 0xFF4CAF50.toInt() else 0xFFF44336.toInt()
        )
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale("id"))
        holder.tvDate.text = sdf.format(Date(t.timestamp))
    }

    override fun getItemCount() = list.size
}