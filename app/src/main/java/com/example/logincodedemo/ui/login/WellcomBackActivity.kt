package com.example.logincodedemo.ui.login

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.logincodedemo.R
import com.example.logincodedemo.databinding.ActivityWellcomBackBinding

class WellcomBackActivity : AppCompatActivity() {

    lateinit var binding: ActivityWellcomBackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_wellcom_back)
       UserLoggedIn.getInstance().displayName.observe(this, Observer {
           binding.txtMesUsername.text = it.username
        })
    }
}