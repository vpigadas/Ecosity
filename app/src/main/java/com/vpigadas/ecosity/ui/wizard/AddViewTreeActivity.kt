package com.vpigadas.ecosity.ui.wizard

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vpigadas.ecosity.R
import com.vpigadas.ecosity.abstraction.AbstractActivity
import com.vpigadas.ecosity.ui.wizard.fragment.AddressFragment
import com.vpigadas.ecosity.ui.wizard.fragment.DetailsFragment
import com.vpigadas.ecosity.ui.wizard.fragment.InfoFragment

class AddViewTreeActivity : AbstractActivity(R.layout.activity_add_view_tree) {
    private val viewmodel: AddViewTreeViewModel by viewModels<AddViewTreeViewModel>()

    override fun initLayout() {
        viewmodel.setup()
    }

    override fun startOperations() {
        viewmodel.observer(this, Observer {
            when (it) {
                WizardStatus.INFO -> showStep(InfoFragment.newInstance())
                WizardStatus.DETAILS -> showStep(DetailsFragment.newInstance())
                WizardStatus.ADDRESS -> showStep(AddressFragment.newInstance())
            }
        })
    }

    override fun stopOperations() {
        viewmodel.release(this)
    }

    private fun showStep(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.wizard_container, fragment)
        transaction.commit()
    }
}