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

class InfoFragment : WizardFragment(R.layout.fragment_info) {
    private val viewmodel: AddViewTreeViewModel by activityViewModels<AddViewTreeViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment()
    }

    override fun initLayout(view: View) {
        view.findViewById<Button>(R.id.next).setOnClickListener {
            validateAndContinue(view)
        }

        view.findViewById<Button>(R.id.cancel).setOnClickListener {
            viewmodel.gotoBack(WizardStatus.INFO)
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_leaf)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.leaftype = position
                view.findViewById<TextInputLayout?>(R.id.textField_leaf)?.apply {
                    hide(this)
                }
            }
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_address)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.municipalityId = position
                view.findViewById<TextInputLayout?>(R.id.textField_address)?.apply {
                    hide(this)
                }
            }
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_territory)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.groundtypeid = position
                view.findViewById<TextInputLayout?>(R.id.textField_territory)?.apply {
                    hide(this)
                }
            }
        }
    }

    private fun validateAndContinue(view: View) {

        when (viewmodel.dataModel.leaftype < 0) {
            true -> view.findViewById<TextInputLayout>(R.id.textField_leaf)?.apply {
                showError(this, getString(R.string.error_wizard_empty))
            }
        }

        when (viewmodel.dataModel.municipalityId < 0) {
            true -> view.findViewById<TextInputLayout>(R.id.textField_address)?.apply {
                showError(this, getString(R.string.error_wizard_empty))
            }
        }

        when (viewmodel.dataModel.groundtypeid < 0) {
            true -> view.findViewById<TextInputLayout>(R.id.textField_territory)?.apply {
                showError(this, getString(R.string.error_wizard_empty))
            }
        }

        viewmodel.dataModel.photo = view.findViewById<EditText>(R.id.edit_photo).let {
            val value = it.text.toString()

            when (value.isNullOrEmpty()) {
                true -> view.findViewById<TextInputLayout>(R.id.textField_photo)?.apply {
                    showError(this, getString(R.string.error_wizard_empty))
                }
            }

            value
        }

        viewmodel.gotoNext(WizardStatus.INFO)
    }

    override fun startOperations() {
        viewmodel.observerLeafType(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView>(R.id.edit_leaf)?.apply {
                setAutoCompleteList(this, list.map { it.getName() })
            }
        })

        viewmodel.observerArea(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView>(R.id.edit_address)?.apply {
                setAutoCompleteList(this, list.map { it.municiapalityid.toString() })
            }
        })

        viewmodel.observerGround(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView>(R.id.edit_territory)?.apply {
                setAutoCompleteList(this, list.map { it.getName() })
            }
        })
    }

    override fun stopOperations() {
        viewmodel.release(this)
    }
}