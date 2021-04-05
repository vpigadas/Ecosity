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

class DetailsFragment : AbstractFragment(R.layout.fragment_details) {
    private val viewmodel: AddViewTreeViewModel by activityViewModels<AddViewTreeViewModel>()

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }

    override fun initLayout(view: View) {
        view.findViewById<Button>(R.id.next).setOnClickListener {
            viewmodel.dataModel.diameter =
                view.findViewById<EditText>(R.id.edit_diameter).text.toString().toIntOrNull() ?: -1

            viewmodel.sendData()
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

    override fun startOperations() {
        viewmodel.observerSurface(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView?>(R.id.edit_surface)?.also {
                    it.setAdapter(
                        ArrayAdapter<String>(
                            this, android.R.layout.simple_list_item_1, list.map { it.getName() })
                    )
                }
            }
        })

        viewmodel.observerPlant(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView?>(R.id.edit_plant)?.also {
                    it.setAdapter(
                        ArrayAdapter<String>(
                            this, android.R.layout.simple_list_item_1, list.map { it.getName() })
                    )
                }
            }
        })

        viewmodel.observerBranch(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView?>(R.id.edit_branch)?.also {
                    it.setAdapter(
                        ArrayAdapter<String>(
                            this, android.R.layout.simple_list_item_1, list.map { it.getName() })
                    )
                }
            }
        })

        viewmodel.observerCondition(this, Observer { list ->
            context?.apply {
                view?.findViewById<AutoCompleteTextView?>(R.id.edit_special)?.also {
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