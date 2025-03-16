package com.example.vroomvroomf1.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vroomvroomf1.data.model.Race
import com.example.vroomvroomf1.data.repository.F1Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class F1ViewModel : ViewModel() {
    private val repository = F1Repository()

    private val _races = MutableStateFlow<List<Race>>(emptyList())
    val races: StateFlow<List<Race>> = _races

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchRaces()
    }

    private fun fetchRaces() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = repository.getCurrentSeasonRaces()
                _races.value = response.MRData.RaceTable.Races
            } catch (e: Exception) {
                _races.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
