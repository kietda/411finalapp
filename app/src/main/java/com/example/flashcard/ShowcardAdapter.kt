package com.example.flashcard

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.showcard_item.view.*

class ShowcardAdapter( private val cards: List<Card>) : RecyclerView.Adapter<ShowcardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowcardViewHolder {
        return ShowcardViewHolder(parent)
    }
    override fun onBindViewHolder(holder: ShowcardViewHolder, position: Int) {
        holder.bind(cards[position])

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
    override fun getItemCount(): Int = cards.size

}

