package com.example.mozzartkino.presentation.util

import android.app.Application
import android.widget.TextView
import com.example.mozzartkino.R
import com.example.mozzartkino.util.date.DateTimeUtils
import kotlinx.coroutines.*
import java.util.*

class ViewHolderTimerImpl : ViewHolderTimer {
    override fun getJob(
        app: Application,
        drawTime: Date,
        leftTime: TextView,
        dateTimeUtils: DateTimeUtils
    ): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                val currentTime = Date(System.currentTimeMillis())
                if (currentTime.time <= drawTime.time) {
                    val leftTimeFormatted =
                        dateTimeUtils.convertLongToTime(true, drawTime.time - currentTime.time)
                    val splited = leftTimeFormatted.split(":")
                    val newLeftTimeFormatted = if (splited[0].toInt() == 1) {
                        "${splited[1]}:${splited[2]}"
                    } else {
                        val newValue = splited[0].toInt() - 1
                        "${newValue}:${splited[1]}:${splited[2]}"
                    }
                    withContext(Dispatchers.Main) {
                        leftTime.text = newLeftTimeFormatted
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        leftTime.text = app.resources.getString(R.string.done)
                    }
                    break
                }
                delay(1000)
            }
        }
    }
}