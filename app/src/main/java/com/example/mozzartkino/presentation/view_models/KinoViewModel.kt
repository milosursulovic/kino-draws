package com.example.mozzartkino.presentation.view_models

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mozzartkino.data.model.Draw
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.domain.use_case.GetDrawById
import com.example.mozzartkino.domain.use_case.GetDraws
import com.example.mozzartkino.domain.use_case.GetSavedDraws
import com.example.mozzartkino.domain.use_case.SaveDraw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KinoViewModel(
    private val app: Application,
    private val getDrawsUseCase: GetDraws,
    private val getDrawByIdUseCase: GetDrawById,
    private val saveDraw: SaveDraw,
    private val getSavedDraws: GetSavedDraws
) : AndroidViewModel(app) {
    val draws = MutableLiveData<Resource<List<Draw>>>()

    fun getDraws() = viewModelScope.launch(Dispatchers.IO) {
        draws.postValue(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getDrawsUseCase.execute()
                draws.postValue(apiResult)
            } else {
                draws.postValue(Resource.Error("Internet is not available"))
            }
        } catch (e: Exception) {
            draws.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}