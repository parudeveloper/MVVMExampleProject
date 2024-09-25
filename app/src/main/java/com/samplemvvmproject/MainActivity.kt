package com.samplemvvmproject

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.samplemvvmproject.databinding.ActivityMainBinding
import com.samplemvvmproject.ui.MyViewModel
import com.samplemvvmproject.ui.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchData()
    }
    private fun fetchData(){
        viewModel.uiState.onEach { state ->
            when (state) {
                is UIState.Loading -> {
                    Log.d("MainActivity","Loading Data")
                }
                is UIState.Success -> {
                    Log.d("MainActivity",state.data.toString())
                }
                is UIState.Error -> {
                    Log.d("MainActivity","Error")
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.getData() // Trigger data fetch
    }


}