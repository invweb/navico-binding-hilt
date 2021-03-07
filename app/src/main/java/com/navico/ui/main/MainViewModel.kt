package com.navico.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navico.retrofit.MarisApiService
import com.navico.ui.adapter.MainListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    fun getAndShowPoints(adapter: MainListAdapter) {
        val marisApiService = MarisApiService.Factory.create()
        viewModelScope.launch(Dispatchers.Main) {
            adapter.addItems(marisApiService.pois())
        }
    }
}


