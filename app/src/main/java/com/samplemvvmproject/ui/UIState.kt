package com.samplemvvmproject.ui

sealed class UIState<out T> {
    object Loading : UIState<Nothing>()
    data class Success<out T>(val data: T) : UIState<T>()
    data class Error(val exception: Exception) : UIState<Nothing>()
}