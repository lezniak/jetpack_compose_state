package com.example.jetpackstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackstate.ui.theme.JetpackstateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackstateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WatherCount(Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun WatherCount(modifier: Modifier){
        var count by rememberSaveable {
            mutableIntStateOf(0)
        }
        Column {
            if (count > 0) {
                Text(text = "You have $count", modifier = modifier.padding(16.dp))
            }

            Button(onClick = { count++ }, enabled = count < 10) {
                Text(text = "Increment")
            }
        }
        
    }
}
