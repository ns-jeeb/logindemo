package com.example.logincodedemo.data.model

import android.util.Log
import com.example.logincodedemo.BuildConfig
import io.reactivex.Flowable
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.create
import okio.IOException
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

class MokInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var response: Response? = null
        if (BuildConfig.DEBUG) {
            val responseString: String  = "{\n" +
                    "   \"isSuccessful\":true,\n" +
                    "   \"username\":\"najeeb\"\n" +
                    "}"
            response = Response.Builder()
                    .code(200)
                    .message(responseString)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(create("application/json".toMediaTypeOrNull(), responseString.toByteArray()))
                    .addHeader("content-type", "application/json")
                    .build()
        } else {
            Log.e("wrong_call","should not called here")
            response = chain.proceed(chain.request())
        }
        return response
    }
}
interface IRestService {
    @GET("login")
    fun login(@Query("username") username: String,@Query("password") password: String): Call<LoggedInUserRespons>

    companion object {
        const val ENDPOINT = "http://blablabla.to/"
    }
}