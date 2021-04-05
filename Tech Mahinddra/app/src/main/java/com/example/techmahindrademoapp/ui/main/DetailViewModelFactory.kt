package com.example.techmahindrademoapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techmahindrademoapp.SmartPhoneViewModel

class DetailViewModelFactory(private val details: String, val postion: Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(details,postion) as T
        }else if (modelClass.isAssignableFrom(SmartPhoneViewModel::class.java)){
            return SmartPhoneViewModel(details,postion) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}
