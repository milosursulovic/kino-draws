package com.example.mozzartkino.presentation.draws

import com.example.mozzartkino.domain.model.Draw

data class DrawState(
    val draws: List<Draw> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
