package com.example.flashcard


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "MainActivityViewModel"

class MainActivityViewModel:ViewModel() {
    private val booksLiveData: MutableLiveData<MutableList<Card>>

    init{
        Log.i(TAG,"init")
        booksLiveData = MutableLiveData()
        booksLiveData.value = createCards()
    }
    fun getCards(): LiveData<MutableList<Card>> {
        return booksLiveData
    }
    private fun createCards():MutableList<Card> {
        Log.i(TAG,"Create cards")
        val cards = mutableListOf<Card>()
        for (i in 1..150) cards.add(Card("Title #$i", "Content #$i"))
        return cards
    }
}