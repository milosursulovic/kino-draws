package com.example.mozzartkino.presentation.util

import android.app.Application
import android.widget.TextView
import com.example.mozzartkino.util.date.DateTimeUtils
import kotlinx.coroutines.Job
import java.util.*

interface ViewHolderTimer {
    fun getJob(
        app: Application,
        drawTime: Date,
        leftTime: TextView,
        dateTimeUtils: DateTimeUtils
    ): Job
}