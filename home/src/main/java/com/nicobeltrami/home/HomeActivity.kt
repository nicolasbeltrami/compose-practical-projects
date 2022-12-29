package com.nicobeltrami.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import com.nicobeltrami.home.ui.Home
import com.nicobeltrami.home.ui.theme.PracticalComposeTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Home(
                modifier = Modifier.fillMaxSize(),
                orientation = LocalConfiguration.current.orientation
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(orientation = LocalConfiguration.current.orientation)
}