package com.example.mozzartkino.presentation.util

import com.example.mozzartkino.domain.model.Draw

interface FragmentUtils {
    fun initRecyclerView()
    fun navigateToInfo(draw: Draw) {}
    fun showProgressBar() {}
    fun hideProgressBar() {}
    fun getDrawsList() {}
}