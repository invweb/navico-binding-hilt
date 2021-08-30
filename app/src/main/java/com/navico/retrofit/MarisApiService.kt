package com.navico.retrofit

import com.navico.data.Item
import dagger.Provides
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MarisApiService {

    @GET("pois.json?name=vasilii")
    fun getPois(): Call<List<Item?>>

    class Factory {
            companion object {
                fun create(): MarisApiService {
                    val retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://spb.maris.no")
                        .build();

                    return retrofit.create(MarisApiService::class.java)
                }
            }
        }
}