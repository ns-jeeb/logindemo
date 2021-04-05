package com.example.techmahindrademoapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techmahindrademoapp.dummy.SmartPhone

class DetailsViewModel(var details: String,var position: Int) : ViewModel() {
    val mDetails: LiveData<SmartPhone>
    var _mDetails: MutableLiveData<SmartPhone> = MutableLiveData()

    val mUpdateDetails: LiveData<SmartPhone>
    var _mUpdateDetails: MutableLiveData<SmartPhone> = MutableLiveData()

    val mPosition: LiveData<Int>
    var _mPosition: MutableLiveData<Int> = MutableLiveData()

    var mPostion: MutableLiveData<Int> = MutableLiveData()
    init {
        mPostion.value = position
        mPosition = _mPosition
        mUpdateDetails = _mUpdateDetails
        _mDetails.value?.name = details
        mDetails = _mDetails
    }
    fun updateDetails(details: String, position: Int){
        _mUpdateDetails.value?.name = details
    }
}