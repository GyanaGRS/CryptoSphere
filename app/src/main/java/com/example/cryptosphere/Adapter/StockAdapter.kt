package com.example.cryptosphere.Adapter

import android.graphics.Color
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptosphere.Model.Model
import com.example.cryptosphere.databinding.ViewholderStockBinding

class StockAdapter(private val dataList: ArrayList<Model>) :
    RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    private val formatter = DecimalFormat("###,###,###,###.##")

    class ViewHolder(val binding: ViewholderStockBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            cryptoNametxt.text = item.name
            cryptoPricetxt.text = "$${formatter.format(item.price)}"
            changePrcntxt.text = "${formatter.format(item.changePercent)}%"
            sparklineLayout.setData(item.lineData)

            // Set color based on price change
            val changeColor = when {
                item.changePercent > 0 -> Color.parseColor("#12c737")
                item.changePercent < 0 -> Color.parseColor("#ff0000")
                else -> Color.WHITE
            }
            changePrcntxt.setTextColor(changeColor)
            sparklineLayout.sparkLineColor = changeColor

            val picName = when (item.name) {
                "NASDAQ100" -> "stock_1"
                "S&P 500" -> "stock_2"
                "Dow Jones" -> "stock_3"
                else -> ""
            }

            // Load image using Glide
            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                picName, "drawable", holder.itemView.context.packageName
            )
            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(logomg)
        }
    }

    override fun getItemCount(): Int = dataList.size
}
