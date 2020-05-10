package com.example.flashcard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_addcard.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.CountDownLatch


private const val TAG = "ABC_AddcardActitity"
class AddcardActivity : AppCompatActivity() {
    private lateinit var addingSubmitButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcard)
        Log.i(TAG, "inside addcard activity")
        addingSubmitButton = findViewById(R.id.btSubmit)
        addingSubmitButton.setOnClickListener setOnClickcListener@{
        val title = editTitle.text.toString().trim()
        val content = editContent.text. toString().trim()
        if (title.isEmpty()){
            editTitle.error = "Title Required"
            editTitle.requestFocus()
            return@setOnClickcListener
        }
        if (content.isEmpty()){
            editContent.error = "Content Required"
            editContent.requestFocus()
            return@setOnClickcListener
        }
            val JSON = "application/json; charset=utf-8".toMediaType()
            val url = "http://67.205.132.25/api/v1/resources/cards/"
            var actualData = JSONObject()
            actualData.put("title",title)
            actualData.put("content",content)
            val body: RequestBody = actualData.toString().toRequestBody(JSON)
            val request = Request.Builder()
                .url(url)
                .post(body)
                .build()
            val client = OkHttpClient()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.i(TAG, "Post Request failure from AddcardActivity")
                }

                override fun onResponse(call: Call, response: Response) {
                    Log.i(TAG, "post success from AddcardActivity")
                try {
                    val aresponse = response.body?.string()
                    Log.i(TAG, "dont know aresponse type$aresponse")
                    if (aresponse == "") {
                        // addingResultTextView.text = "same card errors!"
                        Log.i(TAG, "same card error!")
                        setReturnedResult("",Activity.RESULT_CANCELED)
                    } else {
                        Log.i(TAG, "get card success$aresponse")
                        //addingResultTextView.text = ""
                        val body = aresponse.toString()
                        val gb = GsonBuilder().create()
                        val acard = gb.fromJson(body, Card::class.java)
                        setReturnedResult(acard.toString(),Activity.RESULT_OK)
                    }
                } catch (e: IOException ){
                    Log.i(TAG, "unknown error: $e.message")
                }


                }

            })
        }
    }
    private fun setReturnedResult(returnedResult: String?, activity_result : Int) {
        val data = Intent().apply {
            putExtra(ADDCARD_RESULT, returnedResult)
        }
        setResult(activity_result, data)
        finish()
    }
}
