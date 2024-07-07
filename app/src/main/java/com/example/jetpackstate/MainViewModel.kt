package com.example.jetpackstate

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.jetpackstate.model.WellnessTask

class MainViewModel : ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask>
        get() = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        _tasks.find { it.id == item.id }?.let { task ->
            task.checked.value = checked
        }

    fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
}