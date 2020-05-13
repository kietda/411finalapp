package com.example.flashcard


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent;
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "ABC_MainActivity"
private const val SLIDE_SHOW: Int = 0
private const val ADD_CARD: Int = 1
const val ADDCARD_RESULT = "com.example.flashcard.addcard_result"
class MainActivity : AppCompatActivity() {

    val cards = mutableListOf<Card>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")

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
            val intent = Intent(this@MainActivity, ShowcardActivity::class.java)
            startActivityForResult(intent, SLIDE_SHOW)  //setting a result 1st
        }

        val btnAddCard: FloatingActionButton = findViewById(R.id.fabAddCard)

        btnAddCard.setOnClickListener {
            val intent = Intent(this@MainActivity, AddcardActivity::class.java)
            startActivityForResult(intent, ADD_CARD)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)

        Log.i(TAG,"not return result ok")
        if (requestCode == ADD_CARD){
            if (resultCode != Activity.RESULT_OK){
                val toast: Toast =  Toast.makeText(this, "Error! This card existed!", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                return
            }

            val tempReturnedResult = data?.getStringExtra(ADDCARD_RESULT) ?: ""
            val gson = Gson()
            val aCard: Card = gson.fromJson(tempReturnedResult,Card::class.java)
            cards.add(aCard)
            rvCards.adapter?.notifyItemInserted(cards.size-1)
        }
    }
}
