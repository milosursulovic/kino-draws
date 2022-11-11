package com.example.mozzartkino.presentation.view_models

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mozzartkino.domain.use_case.GetDrawById
import com.example.mozzartkino.domain.use_case.GetDraws

class KinoViewModelFactory(
    private val app: Application,
    private val getDraws: GetDraws,
    private val getDrawById: GetDrawById
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KinoViewModel(
            app,
            getDraws,
            getDrawById
        ) as T
    }
}