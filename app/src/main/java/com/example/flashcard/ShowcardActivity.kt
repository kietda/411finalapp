package com.example.flashcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
}
