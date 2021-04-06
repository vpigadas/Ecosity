package com.vpigadas.ecosity.abstraction

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

abstract class WizardFragment(contentLayoutId: Int) : AbstractFragment(contentLayoutId) {

    protected fun setAutoCompleteList(view: AutoCompleteTextView, list: List<String>) {
        context?.apply {
            view.setAdapter(
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
            )
        }
    }

    protected fun showError(view: TextInputLayout, message: String) {
        view.error = message
    }

    protected fun hide(view: TextInputLayout) {
        view.error = ""
    }

    protected fun addTextChanges(edit: TextInputEditText, view: TextInputLayout) {
        edit.addTextChangedListener { hide(view) }
    }
}