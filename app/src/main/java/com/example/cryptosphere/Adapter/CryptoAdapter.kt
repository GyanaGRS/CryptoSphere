package com.example.cryptosphere.Adapter

import android.graphics.Color
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptosphere.Model.Model
import com.example.cryptosphere.databinding.ViewholderCryptoBinding

class CryptoAdapter(private val dataList: ArrayList<Model>) :
    RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    private val formatter = DecimalFormat("###,###,###,###.##")

    class ViewHolder(val binding: ViewholderCryptoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            cryptoName.text = item.name
            cryptoPrice.text = "$${formatter.format(item.price)}"
            changePercent.text = "${formatter.format(item.changePercent)}%"
            propertySizeTxt.text = item.symbol
            propertyAmt.text = "$${formatter.format(item.propertyAmount)}"
            sparklineLayout.setData(item.lineData)

            val changeColor = when {
                item.changePercent > 0 ->Color.parseColor("#12c737")
                item.changePercent < 0 -> Color.parseColor("#ff0000")
                else -> Color.WHITE

            }
            changePercent.setTextColor(changeColor)
            sparklineLayout.sparkLineColor = changeColor

            val drawableResourceId= holder.itemView.context.resources.getIdentifier(
                item.name,"drawable",holder.itemView.context.packageName
            )

            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(logoImg)
        }
    }

    override fun getItemCount(): Int = dataList.size
}
