package com.vpigadas.ecosity.abstraction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class AbstractFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout(view)
    }

    abstract fun initLayout(view: View)

    override fun onResume() {
        super.onResume()
        startOperations()
    }

    abstract fun startOperations()

    override fun onPause() {
        super.onPause()
        stopOperations()
    }

    abstract fun stopOperations()
}