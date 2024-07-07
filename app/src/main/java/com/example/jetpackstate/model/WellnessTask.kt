package com.example.jetpackstate.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WellnessTask(val id: Int, val name: String,var checked: MutableState<Boolean> = mutableStateOf(false))
