package com.vpigadas.ecosity.ui.login

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.vpigadas.ecosity.data.UserModel
import com.vpigadas.ecosity.data.login.UserCredentialsModel

class LoginViewModel : ViewModel() {
    private val credentialsModel: UserCredentialsModel = UserCredentialsModel.MOCK

    private val streamUser: MutableLiveData<UserModel> = MutableLiveData()

    fun observer(owner: LifecycleOwner, action: Observer<UserModel>) {
        streamUser.observe(owner, action)
    }

    fun release(owner: LifecycleOwner) {
        streamUser.removeObservers(owner)
    }

    fun loginUser(username: String, password: String) {
        when (credentialsModel.validate(username, password)) {
            true -> streamUser.postValue(UserModel(username))
            false -> streamUser.postValue(null)
        }
    }

}