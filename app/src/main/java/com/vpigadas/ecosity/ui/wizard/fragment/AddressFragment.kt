package com.vpigadas.ecosity.ui.wizard.fragment

import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.vpigadas.ecosity.R
import com.vpigadas.ecosity.abstraction.WizardFragment
import com.vpigadas.ecosity.ui.wizard.AddViewTreeViewModel
import com.vpigadas.ecosity.ui.wizard.WizardStatus

class AddressFragment : WizardFragment(R.layout.fragment_address) {
    private val viewmodel: AddViewTreeViewModel by activityViewModels<AddViewTreeViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() = AddressFragment()
    }

    override fun initLayout(view: View) {
        view.findViewById<Button>(R.id.next).setOnClickListener {
            validateAndContinue(view)
        }

        view.findViewById<Button>(R.id.cancel).setOnClickListener {
            activity?.finish()
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_area)
            ?.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.areaid = position
            }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_kind)
            ?.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.typeid = position
            }
    }

    override fun startOperations() {
        viewmodel.observerArea(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView>(R.id.edit_area)?.apply {
                setAutoCompleteList(this, list.map { it.name })
            }
        })

        viewmodel.observerTreeType(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView>(R.id.edit_kind)?.apply {
                setAutoCompleteList(this, list.map { it.getName() })
            }
        })
    }

    private fun validateAndContinue(view: View) {

        when (viewmodel.dataModel.areaid < 0) {
            true -> view.findViewById<TextInputLayout>(R.id.textField_area)?.apply {
                showError(this, getString(R.string.error_wizard_empty))
            }
        }

        when (viewmodel.dataModel.typeid < 0) {
            true -> view.findViewById<TextInputLayout>(R.id.textField_kind)?.apply {
                showError(this, getString(R.string.error_wizard_empty))
            }
        }

        viewmodel.dataModel.latitude = view.findViewById<EditText>(R.id.edit_lat).let {
            val value = it.text.toString().toDoubleOrNull() ?: 0.0

            when (value <= 0.0) {
                true -> view.findViewById<TextInputLayout>(R.id.textField_lat)?.apply {
                    showError(this, getString(R.string.error_wizard_empty))
                }
            }

            value
        }

        viewmodel.dataModel.longitude =
            view.findViewById<EditText>(R.id.edit_long).let {
                val value = it.text.toString().toDoubleOrNull() ?: 0.0

                when (value <= 0.0) {
                    true -> view.findViewById<TextInputLayout>(R.id.textField_long)?.apply {
                        showError(this, getString(R.string.error_wizard_empty))
                    }
                }

                value
            }

        viewmodel.gotoNext(WizardStatus.ADDRESS)
    }

    override fun stopOperations() {
        viewmodel.release(this)
    }
}