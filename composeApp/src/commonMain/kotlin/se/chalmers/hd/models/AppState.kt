package se.chalmers.hd.models

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp

object AppState {
    val paddingValueState = mutableStateOf(PaddingValues(0.dp))
}