package com.example.mozzartkino.presentation.view_models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mozzartkino.R
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.use_case.GetDrawsUseCase
import com.example.mozzartkino.domain.use_case.GetResultsUseCase
import com.example.mozzartkino.domain.use_case.GetSavedDrawsUseCase
import com.example.mozzartkino.domain.use_case.SaveDrawUseCase
import com.example.mozzartkino.presentation.draws.DrawEvent
import com.example.mozzartkino.presentation.draws.DrawState
import com.example.mozzartkino.util.date.DateTimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class KinoViewModel @Inject constructor(
    private val app: Application,
    private val getDrawsUseCase: GetDrawsUseCase,
    private val getResultsUseCase: GetResultsUseCase,
    private val saveDrawUseCase: SaveDrawUseCase,
    private val getSavedDrawsUseCase: GetSavedDrawsUseCase
) : ViewModel(), DateTimeUtils {

    private var state = DrawState()

    fun triggerEvent(event: DrawEvent) {
        when (event) {
            DrawEvent.GetDraws -> getDraws()
            DrawEvent.GetResults -> getResults()
            DrawEvent.GetSavedDraws -> getSavedDraws()
        }
    }

    private var stateChangeListener: ((DrawState) -> Unit)? = null

    fun setStateChangeListener(stateChangeListener: (DrawState) -> Unit) {
        this.stateChangeListener = stateChangeListener
    }

    private fun getDraws() {
        viewModelScope.launch {
            getDrawsUseCase().collect { result ->
                state = when (result) {
                    is Resource.Error -> state.copy(
                        isLoading = false,
                        error = result.message ?: app.resources.getString(R.string.error_occurred)
                    )
                    is Resource.Loading -> state.copy(isLoading = true)
                    is Resource.Success -> state.copy(
                        isLoading = false,
                        draws = result.data ?: emptyList()
                    )
                }
            }
            stateChangeListener?.let {
                it(state)
            }
        }
    }

    private fun getResults() {
        viewModelScope.launch {
            getResultsUseCase(getToday(), getToday()).collect { result ->
                state = when (result) {
                    is Resource.Error -> state.copy(
                        isLoading = false,
                        error = result.message ?: app.resources.getString(R.string.error_occurred)
                    )
                    is Resource.Loading -> state.copy(isLoading = true)
                    is Resource.Success -> state.copy(
                        isLoading = false,
                        draws = result.data ?: emptyList()
                    )
                }
            }
            stateChangeListener?.let {
                it(state)
            }
        }
    }

    private fun getSavedDraws() {
        viewModelScope.launch {
            getSavedDrawsUseCase().collect { draws ->
                state = state.copy(draws = draws)
                stateChangeListener?.let {
                    it(state)
                }
            }
        }
    }

    fun saveDraw(draw: Draw) = viewModelScope.launch {
        saveDrawUseCase(draw)
    }

    override fun getToday(): String {
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        dateFormatter.isLenient = false
        val today = Date()
        return dateFormatter.format(today)
    }
}