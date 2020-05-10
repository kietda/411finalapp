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
//        itemView.categoryTitle.text = card.title
        itemView.categoryContent.text = card.content
        itemView.categoryTitle.text = card.title
        itemView.categoryContent.visibility = View.INVISIBLE
        itemView.viewContent.visibility = View.INVISIBLE

    }

    fun toggleView() {
        itemView.viewRoot.visibility = if (itemView.viewRoot.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        itemView.categoryTitle.visibility = if (itemView.categoryTitle.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        itemView.viewContent.visibility = if (itemView.viewContent.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
        itemView.categoryContent.visibility = if (itemView.categoryContent.visibility == View.VISIBLE) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }

}
