package com.vpigadas.ecosity.ui.login

import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.vpigadas.ecosity.R
import com.vpigadas.ecosity.abstraction.AbstractActivity
import com.vpigadas.ecosity.data.UserModel
import com.vpigadas.ecosity.ui.home.HomeActivity

class LoginActivity : AbstractActivity(R.layout.activity_login) {
    private val viewModel: LoginViewModel by viewModels<LoginViewModel>()

    override fun initLayout() {
        findViewById<Button?>(R.id.login)?.setOnClickListener {
            val username =
                findViewById<EditText?>(R.id.edit_username)?.let { it.text.toString() } ?: ""

            val password =
                findViewById<EditText?>(R.id.edit_password)?.let { it.text.toString() } ?: ""

            viewModel.loginUser(username, password)
        }
    }

    override fun startOperations() {
        viewModel.observer(this, Observer {
            when (it) {
                null -> showError()
                else -> goto(it)
            }
        })
    }

    private fun goto(userModel: UserModel) {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun showError() {
//        TODO("Not yet implemented")
    }

    override fun stopOperations() {
        viewModel.release(this)
    }

}