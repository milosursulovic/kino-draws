package com.example.mozzartkino.presentation.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mozzartkino.domain.use_case.GetDrawById
import com.example.mozzartkino.domain.use_case.GetDraws

class KinoViewModel(
    private val app: Application,
    private val getDraws: GetDraws,
    private val getDrawById: GetDrawById
): AndroidViewModel(app) {

}