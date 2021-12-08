package com.example.androidwerkstuk.API


import com.example.androidwerkstuk.entities.Quote
import retrofit2.Call
import retrofit2.http.GET

interface QuoteController {

    @GET("quotes")
    fun getPosts(): Call<List<Quote>>
}