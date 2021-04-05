package com.vpigadas.ecosity.network

import com.vpigadas.ecosity.network.models.*
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {

    @GET("api/areas")
    fun getAreas(): Call<AreaResponse>

    @GET("api/treetypes")
    fun getTreeTypes(): Call<List<TreeTypeResponse>>

    @GET("api/treeangles")
    fun getTreeAngles(): Call<List<TreeAngleResponse>>

    @GET("api/leaftypes")
    fun getLeadTypes(): Call<List<LeafTypeResponse>>

    @GET("api/leafbranchings")
    fun getBranching(): Call<List<BranchingResponse>>

    @GET("api/trunksurfaces")
    fun geTrunkSurfaces(): Call<List<ΤrunkSurfaceResponse>>

    @GET("api/plantbasins")
    fun getPantBasins(): Call<List<PantBasingResponse>>

    @GET("api/groundtypes")
    fun getGroundTypes(): Call<List<GroundTypeResponse>>

    @GET("api/treeconditions")
    fun getTreeConditions(): Call<List<TreeConditionsResponse>>
}