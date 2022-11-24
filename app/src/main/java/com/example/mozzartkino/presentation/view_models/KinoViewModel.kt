package com.example.mozzartkino.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.domain.use_case.GetDrawsUseCase
import com.example.mozzartkino.domain.use_case.GetResultsUseCase
import com.example.mozzartkino.domain.use_case.GetSavedDrawsUseCase
import com.example.mozzartkino.domain.use_case.SaveDrawUseCase
import com.example.mozzartkino.util.date.DateTimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class KinoViewModel @Inject constructor(
    private val getDrawsUseCase: GetDrawsUseCase,
    private val getResultsUseCase: GetResultsUseCase,
    private val saveDrawUseCase: SaveDrawUseCase,
    private val getSavedDrawsUseCase: GetSavedDrawsUseCase
) : ViewModel(), DateTimeUtils {

    fun getDraws() = getDrawsUseCase()
    fun getResults() = getResultsUseCase(getToday(), getToday())
    fun getSavedDraws() = getSavedDrawsUseCase()

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