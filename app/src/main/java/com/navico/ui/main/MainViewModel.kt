package com.navico.ui.main

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navico.R
import com.navico.data.Item
import com.navico.retrofit.MarisApiService
import com.navico.ui.adapter.MainListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    fun getAndShowPoints(adapter: MainListAdapter, context: Context) {
        val marisApiService = MarisApiService.Factory.create()

        viewModelScope.launch(Dispatchers.Main) {
            marisApiService.pois().enqueue(object : Callback<List<Item?>> {
                override fun onResponse(
                    call: Call<List<Item?>?>,
                    response: Response<List<Item?>?>
                ) {
                    response.body()?.let {
                        viewModelScope.launch(Dispatchers.Main) {
                            adapter.addItems(it)
                        }
                    }
                }

                override fun onFailure(call: Call<List<Item?>?>, t: Throwable) {
                    //Произошла ошибка
                    Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}


