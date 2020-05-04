package com.example.flashcard

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_card.view.*

class CardAdapter(private val context: Context, private val cards: List<Card>)
    : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false))
    }
    // Returns the total count of items in the list
    override fun getItemCount() = cards.size

    // Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card) {
            itemView.tvTitle.text = card.title
            itemView.tvContent.text = "Age: ${card.content}"
//            Glide.with(context).load(card.imageUrl).into(itemView.ivProfile)
        }
    }
}