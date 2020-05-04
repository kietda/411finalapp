package com.example.flashcard


import android.content.ClipData.newIntent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult
import android.content.Intent;
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val SLIDE_SHOW: Int = 0
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")

        val cards = mutableListOf<Card>()
        val cardAdapter = CardAdapter(this,cards)
        rvCards.adapter = cardAdapter
        rvCards.layoutManager = LinearLayoutManager(this)

        val model: MainActivityViewModel by viewModels()
        model.getCards().observe(this, Observer<List<Card>>{ booksLiveData ->
            Log.i(TAG, "Received cards from view model" )
            cards.clear()
            cards.addAll(booksLiveData)
            cardAdapter.notifyDataSetChanged()
        })

        val btnSlideshow: FloatingActionButton = findViewById(R.id.fabSlideShow)
        btnSlideshow.setOnClickListener{
            //val mainParameter = true    // send parameter to 2nd activity
            val intent = Intent(this@MainActivity, ShowcardActivity::class.java)
//            val intent = SlideshowActivity.newIntent(this@MainActivity, mainParameter)
            startActivityForResult(intent, SLIDE_SHOW)  //setting a result 1st
        }
    }



}
