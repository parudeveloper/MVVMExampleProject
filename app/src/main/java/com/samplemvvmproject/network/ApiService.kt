package com.samplemvvmproject.network

import com.samplemvvmproject.data.SchoolListModelItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("resource/tt4n-kn4t.json")
    suspend fun getData(@Query("\$where") query: String): List<SchoolListModelItem>
}