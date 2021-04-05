package com.example.logincodedemo.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.logincodedemo.data.model.IRestService
import com.example.logincodedemo.data.model.LoggedInUserRespons
import com.example.logincodedemo.data.model.RestMockClient
import com.example.logincodedemo.ui.login.UserLoggedIn
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Request
import okio.Timeout
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository() {



    @SuppressLint("CheckResult")
    fun login(username: String, password: String): MutableLiveData<LoggedInUserRespons>? {
        // handle login
        var result =  MutableLiveData<LoggedInUserRespons>()
        var call = RestMockClient.getClient()
        call?.login(username,password)?.enqueue(object : retrofit2.Callback<LoggedInUserRespons>{
            override fun onResponse(call: retrofit2.Call<LoggedInUserRespons>,response: Response<LoggedInUserRespons>) {
                Log.e("my_response","$response ${response.code()}")
                result.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<LoggedInUserRespons>, t: Throwable) {
                Log.e("onFailure","$t ${t.message}")
            }

        })
        return result


//        var callback = RestMockClient.getClient()
//        callback?.login(username, password)?.toObservable()?.subscribeOn(Schedulers.io())?.subscribeWith(object : Observable<LoggedInUserRespons>(),
//                io.reactivex.Observer<LoggedInUserRespons> {
//
//            override fun subscribeActual(observer: io.reactivex.Observer<in LoggedInUserRespons>?) {
//                Log.e("subscribeActual", "subscribe actual")
//            }
//
//            override fun onSubscribe(d: Disposable) {
//                Log.e("onSubscribe", "$d ")
//            }
//
//            override fun onNext(t: LoggedInUserRespons) {
//                Log.e("my_response", "$t ${t.username}")
//                loggedIn = t
//            }
//
//            override fun onError(e: Throwable) {
//                Log.e("onError", "${e.message} ")
//            }
//
//            override fun onComplete() {
//                Log.e("onComplete", "it is complete ")
//            }
//
//        })

//        return result
    }

}