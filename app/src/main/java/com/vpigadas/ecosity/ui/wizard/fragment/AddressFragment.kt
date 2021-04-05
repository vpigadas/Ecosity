package com.vpigadas.ecosity.ui.wizard.fragment

import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.vpigadas.ecosity.R
import com.vpigadas.ecosity.abstraction.AbstractFragment
import com.vpigadas.ecosity.ui.wizard.AddViewTreeViewModel
import com.vpigadas.ecosity.ui.wizard.WizardStatus

class AddressFragment : AbstractFragment(R.layout.fragment_address) {
    private val viewmodel: AddViewTreeViewModel by activityViewModels<AddViewTreeViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() = AddressFragment()
    }

    override fun initLayout(view: View) {
        view.findViewById<Button>(R.id.next).setOnClickListener {
            viewmodel.dataModel.latitude =
                view.findViewById<EditText>(R.id.edit_lat).text.toString().toDoubleOrNull() ?: 0.0

            viewmodel.dataModel.longitude =
                view.findViewById<EditText>(R.id.edit_long).text.toString().toDoubleOrNull() ?: 0.0

            viewmodel.gotoNext(WizardStatus.ADDRESS)
        }

        view.findViewById<Button>(R.id.cancel).setOnClickListener {
            activity?.finish()
        }
    }

    override fun startOperations() {
        viewmodel.observerArea(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView>(R.id.edit_area)?.also {
                    it.setAdapter(
                        ArrayAdapter<String>(
                            this, android.R.layout.simple_list_item_1, list.map { it.name })
                    )
                }
            }
        })

        viewmodel.observerTreeType(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView>(R.id.edit_kind)?.also {
                    it.setAdapter(
                        ArrayAdapter<String>(
                            this, android.R.layout.simple_list_item_1, list.map { it.getName() })
                    )
                }
            }
        })
    }

    override fun stopOperations() {
        viewmodel.release(this)
    }
}