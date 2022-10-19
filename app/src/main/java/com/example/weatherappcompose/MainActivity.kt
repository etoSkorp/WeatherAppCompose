package com.example.weatherappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.example.weatherappcompose.ui.mainScreen.Header
import com.example.weatherappcompose.ui.mainScreen.TabLayout

class MainActivity() : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            Column {
                Header()
                TabLayout()
            }
        }
    }
}
