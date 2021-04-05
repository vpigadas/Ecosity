package com.vpigadas.ecosity.network

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.vpigadas.ecosity.network.models.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {
    private val retrofit: Retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor()).build())
        .baseUrl("https://geolocation.ecosity.gr")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    private val service: Endpoints = retrofit.create(Endpoints::class.java)

    fun getAreas(areasLiveData: MutableLiveData<List<SpecAreaResponse>>) {
        service.getAreas().enqueue(object : Callback<AreaResponse> {
            override fun onResponse(call: Call<AreaResponse>, response: Response<AreaResponse>) {
                response.body()?.apply {
                    areasLiveData.postValue(this.areas)
                }
            }

            override fun onFailure(call: Call<AreaResponse>, t: Throwable) {
                t
            }
        })
    }

    fun getTreeTypes(areasLiveData: MutableLiveData<List<TreeTypeResponse>>) {
        service.getTreeTypes().enqueue(object : Callback<List<TreeTypeResponse>> {
            override fun onResponse(
                call: Call<List<TreeTypeResponse>>,
                response: Response<List<TreeTypeResponse>>
            ) {
                response.body()?.apply {
                    areasLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<TreeTypeResponse>>, t: Throwable) {
                t
            }
        })
    }

    fun getTreeAngles(areasLiveData: MutableLiveData<List<TreeAngleResponse>>) {
        service.getTreeAngles().enqueue(object : Callback<List<TreeAngleResponse>> {
            override fun onResponse(
                call: Call<List<TreeAngleResponse>>,
                response: Response<List<TreeAngleResponse>>
            ) {
                response.body()?.apply {
                    areasLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<TreeAngleResponse>>, t: Throwable) {
                t
            }
        })
    }

    fun getLeadTypes(areasLiveData: MutableLiveData<List<LeafTypeResponse>>) {
        service.getLeadTypes().enqueue(object : Callback<List<LeafTypeResponse>> {
            override fun onResponse(
                call: Call<List<LeafTypeResponse>>,
                response: Response<List<LeafTypeResponse>>
            ) {
                response.body()?.apply {
                    areasLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<LeafTypeResponse>>, t: Throwable) {
                t
            }
        })
    }

    fun getBranching(areasLiveData: MutableLiveData<List<BranchingResponse>>) {
        service.getBranching().enqueue(object : Callback<List<BranchingResponse>> {
            override fun onResponse(
                call: Call<List<BranchingResponse>>,
                response: Response<List<BranchingResponse>>
            ) {
                response.body()?.apply {
                    areasLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<BranchingResponse>>, t: Throwable) {
                t
            }
        })
    }

    fun geTrunkSurfaces(areasLiveData: MutableLiveData<List<ΤrunkSurfaceResponse>>) {
        service.geTrunkSurfaces().enqueue(object : Callback<List<ΤrunkSurfaceResponse>> {
            override fun onResponse(
                call: Call<List<ΤrunkSurfaceResponse>>,
                response: Response<List<ΤrunkSurfaceResponse>>
            ) {
                response.body()?.apply {
                    areasLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<ΤrunkSurfaceResponse>>, t: Throwable) {
                t
            }
        })
    }

    fun getPantBasins(areasLiveData: MutableLiveData<List<PantBasingResponse>>) {
        service.getPantBasins().enqueue(object : Callback<List<PantBasingResponse>> {
            override fun onResponse(
                call: Call<List<PantBasingResponse>>,
                response: Response<List<PantBasingResponse>>
            ) {
                response.body()?.apply {
                    areasLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<PantBasingResponse>>, t: Throwable) {
                t
            }
        })
    }

    fun getGroundTypes(areasLiveData: MutableLiveData<List<GroundTypeResponse>>) {
        service.getGroundTypes().enqueue(object : Callback<List<GroundTypeResponse>> {
            override fun onResponse(
                call: Call<List<GroundTypeResponse>>,
                response: Response<List<GroundTypeResponse>>
            ) {
                response.body()?.apply {
                    areasLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<GroundTypeResponse>>, t: Throwable) {
                t
            }
        })
    }

    fun getTreeConditions(areasLiveData: MutableLiveData<List<TreeConditionsResponse>>) {
        service.getTreeConditions().enqueue(object : Callback<List<TreeConditionsResponse>> {
            override fun onResponse(
                call: Call<List<TreeConditionsResponse>>,
                response: Response<List<TreeConditionsResponse>>
            ) {
                response.body()?.apply {
                    areasLiveData.postValue(this)
                }
            }

            override fun onFailure(call: Call<List<TreeConditionsResponse>>, t: Throwable) {
                t
            }
        })
    }
}