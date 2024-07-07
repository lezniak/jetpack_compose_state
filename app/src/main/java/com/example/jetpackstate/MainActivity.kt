package com.example.jetpackstate

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackstate.model.WellnessTask
import com.example.jetpackstate.ui.theme.JetpackstateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackstateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CounterScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun CounterScreen(modifier: Modifier) {
        var waterCounter by rememberSaveable {
            mutableStateOf(0)
        }
        var list = remember  { getWellnessTasks().toMutableStateList() }
        Column(modifier) {
            WaterCount(
                count = waterCounter,
                onIncrement = { waterCounter++ },
                onClear = { waterCounter = 0 })
           WellnessTasksList(list = list, onCloseTask = { task -> list.remove(task)} )
        }
    }

    @Composable
    private fun WaterCount(
        modifier: Modifier = Modifier,
        count: Int,
        onIncrement: () -> Unit,
        onClear: () -> Unit
    ) {

        Column(modifier) {
            if (count > 0) {
                Text(text = "You have $count", modifier = modifier.padding(16.dp))
            }
            Row() {
                Button(onClick = { onIncrement() }, enabled = count < 10) {
                    Text(text = "Increment")
                }


                Button(onClick = { onClear() }, enabled = count > 0) {
                    Text(text = "Clear")
                }
            }
        }

    }

    fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }

    @Composable
    fun WellnessTasksList(
        modifier: Modifier = Modifier,
        list: List<WellnessTask>,
        onCloseTask: (WellnessTask)-> Unit
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            items(items = list) { item ->
                WellnessTaskItem(taskName = item.name, onClose = { onCloseTask(item)})
            }
        }
    }

    @Composable
    fun WellnessTaskItem(taskName: String,onClose: () -> Unit, modifier: Modifier = Modifier) {
        var checkedState by rememberSaveable { mutableStateOf(false) }

        WellnessTaskItem(
            taskName = taskName,
            checked = checkedState,
            onCheckedChange = { newValue -> checkedState = newValue },
            onClose = {onClose()},
            modifier = modifier,
        )
    }

    @Composable
    fun WellnessTaskItem(
        taskName: String,
        checked: Boolean,
        onCheckedChange: (Boolean) -> Unit,
        onClose: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        Row(
            modifier = modifier, verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                text = taskName
            )
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        }
    }
}
