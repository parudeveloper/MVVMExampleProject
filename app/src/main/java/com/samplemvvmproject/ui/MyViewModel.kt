package com.samplemvvmproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samplemvvmproject.data.SchoolListModelItem
import com.samplemvvmproject.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MyViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<SchoolListModelItem>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<SchoolListModelItem>>> get() = _uiState.asStateFlow()

    fun getData() {
        viewModelScope.launch {
            repository.fetchData().collect { state ->
                _uiState.value = state
            }
        }
    }
}