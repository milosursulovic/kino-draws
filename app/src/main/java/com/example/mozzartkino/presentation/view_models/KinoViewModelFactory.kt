package com.example.mozzartkino.presentation.view_models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mozzartkino.domain.use_case.GetDraws
import com.example.mozzartkino.domain.use_case.GetResults
import com.example.mozzartkino.domain.use_case.GetSavedDraws
import com.example.mozzartkino.domain.use_case.SaveDraw

class KinoViewModelFactory(
    private val app: Application,
    private val getDraws: GetDraws,
    private val getResults: GetResults,
    private val saveDraw: SaveDraw,
    private val getSavedDraws: GetSavedDraws
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KinoViewModel(
            app,
            getDraws,
            getResults,
            saveDraw,
            getSavedDraws
        ) as T
    }
}