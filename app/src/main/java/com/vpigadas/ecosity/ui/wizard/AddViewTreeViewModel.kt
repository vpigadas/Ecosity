package com.vpigadas.ecosity.ui.wizard

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.vpigadas.ecosity.network.ApiClient
import com.vpigadas.ecosity.network.PostTreeModel
import com.vpigadas.ecosity.network.models.*

class AddViewTreeViewModel : ViewModel() {

    val dataModel: PostTreeModel = PostTreeModel()

    private val streamStatus: MutableLiveData<WizardStatus> = MutableLiveData()

    private val streamArea: MutableLiveData<List<SpecAreaResponse>> = MutableLiveData()
    private val streamTreeType: MutableLiveData<List<TreeTypeResponse>> = MutableLiveData()
    private val streamTreeAngle: MutableLiveData<List<TreeAngleResponse>> = MutableLiveData()
    private val streamLeafType: MutableLiveData<List<LeafTypeResponse>> = MutableLiveData()
    private val streamBranch: MutableLiveData<List<BranchingResponse>> = MutableLiveData()
    private val streamSurface: MutableLiveData<List<ΤrunkSurfaceResponse>> = MutableLiveData()
    private val streamPlant: MutableLiveData<List<PantBasingResponse>> = MutableLiveData()
    private val streamGround: MutableLiveData<List<GroundTypeResponse>> = MutableLiveData()
    private val streamCondition: MutableLiveData<List<TreeConditionsResponse>> = MutableLiveData()

    fun setup() {
        ApiClient.getAreas(streamArea)
        ApiClient.getTreeTypes(streamTreeType)
        ApiClient.getTreeAngles(streamTreeAngle)
        ApiClient.getLeadTypes(streamLeafType)
        ApiClient.getBranching(streamBranch)
        ApiClient.geTrunkSurfaces(streamSurface)
        ApiClient.getPantBasins(streamPlant)
        ApiClient.getGroundTypes(streamGround)
        ApiClient.getTreeConditions(streamCondition)

        streamStatus.postValue(WizardStatus.ADDRESS)
    }

    fun observer(owner: LifecycleOwner, action: Observer<WizardStatus>) {
        streamStatus.observe(owner, action)
    }

    fun observerArea(owner: LifecycleOwner, action: Observer<List<SpecAreaResponse>>) {
        streamArea.observe(owner, action)
    }

    fun observerTreeType(owner: LifecycleOwner, action: Observer<List<TreeTypeResponse>>) {
        streamTreeType.observe(owner, action)
    }


    fun observerTreeAngle(owner: LifecycleOwner, action: Observer<List<TreeAngleResponse>>) {
        streamTreeAngle.observe(owner, action)
    }


    fun observerLeafType(owner: LifecycleOwner, action: Observer<List<LeafTypeResponse>>) {
        streamLeafType.observe(owner, action)
    }

    fun observerBranch(owner: LifecycleOwner, action: Observer<List<BranchingResponse>>) {
        streamBranch.observe(owner, action)
    }

    fun observerSurface(owner: LifecycleOwner, action: Observer<List<ΤrunkSurfaceResponse>>) {
        streamSurface.observe(owner, action)
    }


    fun observerPlant(owner: LifecycleOwner, action: Observer<List<PantBasingResponse>>) {
        streamPlant.observe(owner, action)
    }


    fun observerGround(owner: LifecycleOwner, action: Observer<List<GroundTypeResponse>>) {
        streamGround.observe(owner, action)
    }

    fun observerCondition(owner: LifecycleOwner, action: Observer<List<TreeConditionsResponse>>) {
        streamCondition.observe(owner, action)
    }

    fun release(owner: LifecycleOwner) {
        streamStatus.removeObservers(owner)
        streamArea.removeObservers(owner)
        streamTreeType.removeObservers(owner)
        streamTreeAngle.removeObservers(owner)
        streamLeafType.removeObservers(owner)
        streamBranch.removeObservers(owner)
        streamSurface.removeObservers(owner)
        streamPlant.removeObservers(owner)
        streamGround.removeObservers(owner)
        streamCondition.removeObservers(owner)
    }

    fun gotoNext(status: WizardStatus) {
        when (status) {
            WizardStatus.ADDRESS -> when (dataModel.isValidAddress()) {
                true -> streamStatus.postValue(WizardStatus.INFO)
            }
            WizardStatus.INFO -> when (dataModel.isValidInfo()) {
                true -> streamStatus.postValue(WizardStatus.DETAILS)
            }
            WizardStatus.DETAILS -> throw IllegalStateException("you can not go next from this state")
        }
    }

    fun gotoBack(status: WizardStatus) {
        when (status) {
            WizardStatus.ADDRESS -> throw IllegalStateException("you can not go back from this state")
            WizardStatus.INFO -> streamStatus.postValue(WizardStatus.ADDRESS)
            WizardStatus.DETAILS -> streamStatus.postValue(WizardStatus.INFO)
        }
    }

    fun sendData() {
        //TODO("Not yet implemented")
    }
}