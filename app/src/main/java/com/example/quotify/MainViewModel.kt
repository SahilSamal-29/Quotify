package com.example.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson


class MainViewModel(val context: Context): ViewModel() {
    private var quoteList: Array<quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<quote>::class.java)
    }

    fun getQuote() = quoteList[index]

    fun nextQuote() = quoteList[++index]    //index+1

    fun previousQuote() = if(index>0){      //index-1
        quoteList[--index]}else{
            quoteList[index]
        }


}