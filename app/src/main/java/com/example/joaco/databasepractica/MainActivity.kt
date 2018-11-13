package com.example.joaco.databasepractica

import android.app.Application
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ClassRepository.getInstance(application).allWords.observe(this, Observer {
            Log.v("Tag", "Aqui esta:$it")
        })

    }
}
