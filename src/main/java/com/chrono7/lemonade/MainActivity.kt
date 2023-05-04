package com.chrono7.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chrono7.lemonade.ui.theme.LemonadeTheme

val states = listOf(
    listOf(R.string.tap_to_select, R.drawable.lemon_tree, R.string.lemon_tree_desc),
    listOf(R.string.keep_tapping, R.drawable.lemon_squeeze, R.string.lemon_desc),
    listOf(R.string.tap_to_drink, R.drawable.lemon_drink, R.string.glass_desc),
    listOf(R.string.tap_to_restart, R.drawable.lemon_restart, R.string.empty_glass_desc),
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonGame()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonGame(modifier: Modifier = Modifier) {

    var stateIndex by remember { mutableStateOf(0) }

    LemonadeTheme {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = states[stateIndex][0]), fontSize = 18.sp)
            Spacer(modifier = Modifier.size(16.dp))
            Button(
                modifier = Modifier.border(border = BorderStroke(2.dp, Color(105, 205, 216))).padding(20.dp),
                onClick = {
                    stateIndex = when (stateIndex) {
                        1 -> if ((1..4).random() == 1) {
                            stateIndex + 1
                        } else stateIndex

                        states.size - 1 -> 0
                        else -> stateIndex + 1
                    }
                }) {
                Image(
                    painter = painterResource(id = states[stateIndex][1]),
                    contentDescription = stringResource(states[stateIndex][1]),
                )
            }
        }
    }
}