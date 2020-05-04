package com.example.flashcard

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ShowcardAdapter( private val cards: List<Card>) : RecyclerView.Adapter<ShowcardViewHolder>() {
//    var list: List<Card> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcardViewHolder {
        return ShowcardViewHolder(parent)
    }
    override fun onBindViewHolder(holder: ShowcardViewHolder, position: Int) {
        holder.bind(cards[position])
    }
//    fun setItem(list: List<Card>) {
//        this.cards = list
//        notifyDataSetChanged()
//    }
    override fun getItemCount(): Int = cards.size

}