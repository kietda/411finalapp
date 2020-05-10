package com.example.flashcard


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import java.io.IOException
import java.util.concurrent.CountDownLatch


private const val TAG = "ABC_MainActivityViewMod"
class MainActivityViewModel:ViewModel() {
        var cards = mutableListOf<Card>()
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
        Log.i(TAG, "creating card")
        val url = "http://67.205.132.25/api/v1/resources/cards/all"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        val countDownLatch = CountDownLatch(1)

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gb = GsonBuilder().create()
                cards = gb.fromJson(body, Array<Card>::class.java).toMutableList()
                Log.i(TAG, "onResponse succeeded")
                countDownLatch.countDown()
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.i(TAG, "go to on failure")
                println(e.toString())
            }
        })
        Log.i(TAG,"Create cards")
//        for (i in 1..150) cards.add(Card("Title #$i", "Content #$i"))
        countDownLatch.await()

        return cards
    }
}
