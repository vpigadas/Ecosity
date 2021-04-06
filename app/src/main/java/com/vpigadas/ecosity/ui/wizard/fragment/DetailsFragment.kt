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

class DetailsFragment : WizardFragment(R.layout.fragment_details) {
    private val viewmodel: AddViewTreeViewModel by activityViewModels<AddViewTreeViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }

    override fun initLayout(view: View) {
        view.findViewById<Button>(R.id.next).setOnClickListener {
            validateAndContinue(view)
        }

        view.findViewById<Button>(R.id.cancel).setOnClickListener {
            viewmodel.gotoBack(WizardStatus.DETAILS)
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_surface)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.trunksurfaceid = position
            }
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_plant)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.plantbasinid = position
            }
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_branch)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.leafbranch = position
            }
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_special)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.conditionid = position
            }
        }
    }

    private fun validateAndContinue(view: View) {
        viewmodel.dataModel.diameter = view.findViewById<EditText>(R.id.edit_diameter).let {
                val value = it.text.toString().toIntOrNull() ?: -1

                when (value > -1) {
                    true -> view.findViewById<TextInputLayout>(R.id.textField_diameter)?.apply {
                        showError(this, getString(R.string.error_wizard_empty))
                    }
                }

                value
            }

        viewmodel.sendData()
    }

    override fun startOperations() {
        viewmodel.observerSurface(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView?>(R.id.edit_surface)?.apply {
                setAutoCompleteList(this, list.map { it.getName() })
            }
        })

        viewmodel.observerPlant(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView?>(R.id.edit_plant)?.apply {
                setAutoCompleteList(this, list.map { it.getName() })
            }
        })

        viewmodel.observerBranch(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView?>(R.id.edit_branch)?.apply {
                setAutoCompleteList(this, list.map { it.getName() })
            }
        })

        viewmodel.observerCondition(this, Observer { list ->
            view?.findViewById<AutoCompleteTextView?>(R.id.edit_special)?.apply {
                setAutoCompleteList(this, list.map { it.getName() })
            }
        })
    }

    override fun stopOperations() {
        viewmodel.release(this)
    }
}