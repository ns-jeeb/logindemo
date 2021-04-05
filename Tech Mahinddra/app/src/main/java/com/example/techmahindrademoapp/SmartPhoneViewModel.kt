package com.example.techmahindrademoapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.techmahindrademoapp.dummy.SmartPhone

class SmartPhoneViewModel(var update:String, val position:Int) : ViewModel() {
    val lst: LiveData<ArrayList<SmartPhone>>
    var _list: MutableLiveData<ArrayList<SmartPhone>> = MutableLiveData()
    var mSmartPhone: MutableLiveData<SmartPhone>? = MutableLiveData()
    var newlist = arrayListOf<SmartPhone>()
    var details: LiveData<String>
    var _details : MutableLiveData<String> = MutableLiveData()
    var mPostion: MutableLiveData<Int> = MutableLiveData()

    init {
        mPostion.value = position
        lst = _list
        details = _details
        data()
    }

    fun data() {
        var lg = SmartPhone("LG-android")
        var samsong = SmartPhone("samson-android")
        var mororola = SmartPhone("motorola-android")
        var ios_11 = SmartPhone("IOS-11")
        var ios_10 = SmartPhone("IOS-10")
        var ios_9 = SmartPhone("IOS-9")
        var ios_8 = SmartPhone("IOS-8")
        var ios_7 = SmartPhone("IOS-7")
        var pixl = SmartPhone("Google-android")
        newlist.add(lg)
        newlist.add(samsong)
        newlist.add(mororola)
        newlist.add(ios_11)
        newlist.add(ios_10)
        newlist.add(ios_9)
        newlist.add(ios_8)
        newlist.add(ios_7)
        newlist.add(pixl)
        _list.value = newlist
    }

    fun setHighLightedItem(smartPhone: SmartPhone){
        mSmartPhone?.postValue(smartPhone)
    }
    fun getHighLightedItem(smartPhone: SmartPhone): MutableLiveData<SmartPhone>? {

        if (smartPhone == mSmartPhone?.value){
            return mSmartPhone!!
        }
        return null
    }
    fun updateList(detail: String,postion: Int): ArrayList<SmartPhone>{
        Log.e("details", "$detail and $postion")
        var smartPhone = SmartPhone(detail)
        for (i in 0 until lst.value?.size!!){
            when(i){
                postion -> lst.value?.add(postion,smartPhone)
            }
        }
        return lst.value!!
    }
    fun updated(): Boolean{
        return true
    }
}