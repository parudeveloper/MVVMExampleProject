package com.samplemvvmproject.repository

import com.samplemvvmproject.data.SchoolListModelItem
import com.samplemvvmproject.network.ApiService
import com.samplemvvmproject.ui.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService) {
    val query = "starts_with(job_titles, 'CHIEF')"

    suspend fun fetchData(): Flow<UIState<List<SchoolListModelItem>>> = flow {
        emit(UIState.Loading)
        try {
            val data = apiService.getData(query) // Assume this returns List<MyData>
            emit(UIState.Success(data))
        } catch (e: Exception) {
            emit(UIState.Error(e))
        }
    }
}