package com.hedroid.constructionlineproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hedroid.constructionlineproject.Model.SellFlatItem
import com.hedroid.constructionlineproject.R

class SellFlatsAdapter(
    var flats: ArrayList<SellFlatItem>,
    private val onSellClick: (SellFlatItem) -> Unit
) : RecyclerView.Adapter<SellFlatsAdapter.FlatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_flat, parent, false)
        return FlatViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlatViewHolder, position: Int) {
        val flat = flats[position]
        holder.bind(flat, onSellClick)
    }

    override fun getItemCount(): Int = flats.size

    class FlatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgFlat: ImageView = itemView.findViewById(R.id.ivFlatImage)
        private val txtFlatName: TextView = itemView.findViewById(R.id.tvFlatName)
        private val txtFlatAddress: TextView = itemView.findViewById(R.id.tvFlatAddress)
        private val txtFlatPrice: TextView = itemView.findViewById(R.id.tvFlatPrice)
        private val btnSellFlat: Button = itemView.findViewById(R.id.btnSellFlat)

        fun bind(flat: SellFlatItem, onSellClick: (SellFlatItem) -> Unit) {
            imgFlat.setImageResource(flat.imageRes) // Set image correctly
            txtFlatName.text = flat.name
            txtFlatAddress.text = flat.address
            txtFlatPrice.text = "Price: Rs.${flat.price}"

            btnSellFlat.setOnClickListener {
                onSellClick(flat)
            }
        }
    }
}
