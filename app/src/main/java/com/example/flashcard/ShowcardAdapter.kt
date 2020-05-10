package com.example.flashcard

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.showcard_item.view.*

class ShowcardAdapter( private val cards: List<Card>) : RecyclerView.Adapter<ShowcardViewHolder>() {
//    var list: List<Card> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcardViewHolder {
        return ShowcardViewHolder(parent)
    }
    override fun onBindViewHolder(holder: ShowcardViewHolder, position: Int) {
        holder.bind(cards[position])

        //holder.toggle()
        holder.itemView.categoryTitle.setOnClickListener {
            holder.toggleView()
        };

        holder.itemView.viewRoot.setOnClickListener {
            holder.toggleView()
        };

        holder.itemView.categoryContent.setOnClickListener {
            holder.toggleView()
        }
        holder.itemView.viewContent.setOnClickListener {
            holder.toggleView()
        }

    }
//    fun setItem(list: List<Card>) {
//        this.cards = list
//        notifyDataSetChanged()
//    }
    override fun getItemCount(): Int = cards.size

}

