package com.vpigadas.ecosity.ui.home

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.vpigadas.ecosity.network.ApiClient
import com.vpigadas.ecosity.network.models.SpecAreaResponse

class HomeViewModel : ViewModel() {
    private val streamArea: MutableLiveData<List<SpecAreaResponse>> = MutableLiveData()

    fun setup(){
        ApiClient.getAreas(streamArea)
    }

    fun observerArea(owner: LifecycleOwner, action: Observer<List<SpecAreaResponse>>) {
        streamArea.observe(owner, action)
    }

    fun release(owner: LifecycleOwner) {
        streamArea.removeObservers(owner)
    }
}