package com.example.androidsimpleboilerplates.presentation

import android.os.Bundle
import com.example.androidsimpleboilerplates.core.base.BaseActivity
import com.example.androidsimpleboilerplates.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    override fun getActivityName(): String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}