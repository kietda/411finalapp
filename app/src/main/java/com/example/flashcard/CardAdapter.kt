package com.example.flashcard


import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_card.view.*
private const val TAG = "ABC_CardAdapter"
class CardAdapter(private val context: Context, private val cards: List<Card>)
    : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false))
    }
    // Returns the total count of items in the list
    override fun getItemCount() = cards.size

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(card: Card) {
            itemView.tvTitle.text = card.title
            itemView.tvContent.text = card.content
            itemView.setOnClickListener {
                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage(card.content)
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                        // do action here >> delete the card, but we don't have time to finish
                    })
                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle(card.title)
                // show alert dialog
                alert.show()
            }
        }
    }
}