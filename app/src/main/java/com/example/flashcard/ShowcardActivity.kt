package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_showcard.*

private const val TAG = "ShowCard_MainActivity"
class ShowcardActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TASK_DESCRIPTION = "task"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showcard)

//        val cards = listOf(
//            Card("First", "Your Recording"),
//            Card("Second", "Film"),
//            Card("Third", "Series"),
//            Card("Forth", "Kids"),
//            Card("Fifth", "Sport")
//        )

//        val cards = mutableListOf<Card>()
//        val cardAdapter = CardAdapter(this,cards)
//        rvContacts.adapter = ContactAdapter(this, createCards())
//        rvContacts.layoutManager = LinearLayoutManager(this)



//        val cardAdapter = ShowcardAdapter(createCards())
//        viewPager2.adapter = cardAdapter




        val cards = mutableListOf<Card>()
        val cardAdapter = ShowcardAdapter(cards)
        viewPager2.adapter = cardAdapter

        val model: MainActivityViewModel by viewModels()
        model.getCards().observe(this, Observer<List<Card>>{ booksLiveData ->
            Log.i(TAG, "Received show_cards from view model" )
            cards.clear()
            cards.addAll(booksLiveData)
            cardAdapter.notifyDataSetChanged()
        })


    }
//    private fun createCards(): List<Card> {
//        Log.i(TAG, "create ShowCard")
//        val contacts = mutableListOf<Card>()
//        for (i in 1..150) contacts.add(Card("Title #$i", "Title #$i"))
//        return contacts
//    }
//
//    fun doneClicked(view: View) {
//
//    }
}
