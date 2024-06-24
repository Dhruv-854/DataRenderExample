package com.dhruv.datarendering

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExampleDataViewModel : ViewModel() {

    private val repo = GetNameImpl()

    private val _names = MutableStateFlow<List<ExampleData>>(emptyList())

    private val _currentIndex = MutableStateFlow(0)

    val currentName: StateFlow<ExampleData?> = _names
        .combine(_currentIndex) { names, index ->
            if (names.isNotEmpty()){
                names[index]
            }else{
                null
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    init {
        getName()
    }

    private fun getName() {
        viewModelScope.launch {
            val result = repo.getName()
            _names.value = result
        }
    }

    fun nextName() {
        _currentIndex.update { currentIndex ->
            (currentIndex + 1) % (_names.value.size)
        }
    }

    fun previousName() {
        _currentIndex.update { currentIndex ->
            if (currentIndex - 1 < 0) _names.value.size - 1 else currentIndex - 1
        }
    }
}
