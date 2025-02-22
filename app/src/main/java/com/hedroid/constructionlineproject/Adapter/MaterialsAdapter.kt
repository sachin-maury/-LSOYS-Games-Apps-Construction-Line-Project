package com.hedroid.constructionlineproject.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hedroid.constructionlineproject.Model.MaterialItem
import com.hedroid.constructionlineproject.R

// Adapter for displaying construction materials
class MaterialsAdapter(
    private val materials: List<MaterialItem>,
    private val onBuyClick: (MaterialItem) -> Unit
) : RecyclerView.Adapter<MaterialsAdapter.MaterialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_material, parent, false)
        return MaterialViewHolder(view)
    }

    override fun onBindViewHolder(holder: MaterialViewHolder, position: Int) {
        val material = materials[position]
        holder.bind(material, onBuyClick)
    }

    override fun getItemCount(): Int = materials.size

    class MaterialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgMaterial: ImageView = itemView.findViewById(R.id.imgMaterial)
        private val txtMaterialName: TextView = itemView.findViewById(R.id.txtMaterialName)
        private val txtMaterialPrice: TextView = itemView.findViewById(R.id.txtMaterialPrice)
        private val btnBuyMaterial: Button = itemView.findViewById(R.id.btnBuyMaterial)

        fun bind(material: MaterialItem, onBuyClick: (MaterialItem) -> Unit) {
            imgMaterial.setImageResource(material.imageResId)
            txtMaterialName.text = material.name
            txtMaterialPrice.text = material.price

            btnBuyMaterial.setOnClickListener {
                onBuyClick(material)
            }
        }
    }
}