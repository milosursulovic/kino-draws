package com.example.mozzartkino.presentation.draws

sealed class DrawEvent {
    object GetDraws : DrawEvent()
    object GetResults : DrawEvent()
    object GetSavedDraws : DrawEvent()
}