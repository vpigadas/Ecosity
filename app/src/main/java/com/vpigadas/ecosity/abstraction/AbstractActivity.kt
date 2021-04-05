package com.vpigadas.ecosity.abstraction

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractActivity(layoutRes: Int) : AppCompatActivity(layoutRes) {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initLayout()
    }

    abstract fun initLayout()

    override fun onPostResume() {
        super.onPostResume()
        startOperations()
    }

    abstract fun startOperations()

    override fun onPause() {
        super.onPause()
        stopOperations()
    }

    abstract fun stopOperations()
}