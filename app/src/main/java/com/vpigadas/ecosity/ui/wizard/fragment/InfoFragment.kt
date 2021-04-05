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

class InfoFragment : AbstractFragment(R.layout.fragment_info) {
    private val viewmodel: AddViewTreeViewModel by activityViewModels<AddViewTreeViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() = InfoFragment()
    }

    override fun initLayout(view: View) {
        view.findViewById<Button>(R.id.next).setOnClickListener {
            viewmodel.dataModel.diameter =
                view.findViewById<EditText>(R.id.edit_diameter).text.toString().toIntOrNull() ?: 0

            viewmodel.dataModel.photo =
                view.findViewById<EditText>(R.id.edit_photo).text.toString()

            viewmodel.gotoNext(WizardStatus.INFO)
        }

        view.findViewById<Button>(R.id.cancel).setOnClickListener {
            viewmodel.gotoBack(WizardStatus.INFO)
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_leaf)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.leaftype = position
            }
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_address)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.municipalityId = position
            }
        }

        view.findViewById<AutoCompleteTextView?>(R.id.edit_territory)?.also {
            it.setOnItemClickListener { _, _, position, _ ->
                viewmodel.dataModel.groundtypeid = position
            }
        }
    }

    override fun startOperations() {
        viewmodel.observerLeafType(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView>(R.id.edit_leaf)?.also {
                    it.setAdapter(
                        ArrayAdapter<String>(
                            this, android.R.layout.simple_list_item_1, list.map { it.getName() })
                    )
                }
            }
        })

        viewmodel.observerArea(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView>(R.id.edit_address)?.also {
                    it.setAdapter(
                        ArrayAdapter<String>(
                            this,
                            android.R.layout.simple_list_item_1,
                            list.map { it.municiapalityid.toString() })
                    )
                }
            }
        })

        viewmodel.observerGround(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView>(R.id.edit_territory)?.also {
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