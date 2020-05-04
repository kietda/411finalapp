package com.example.flashcard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.showcard_item.view.*

class ShowcardViewHolder constructor(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    constructor(parent: ViewGroup) :
            this(LayoutInflater.from(parent.context).inflate(R.layout.showcard_item, parent, false))
    fun bind(card: Card) {
        itemView.categoryTitle.text = card.title
        itemView.categoryContent.text = card.content
    }

}