package com.example.greetingcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greetingcard.ui.theme.GreetingCardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface (modifier = modifier, color = Color(0,210,255,150)) {
        Text(
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            lineHeight = 44.sp,
            color = Color(255,255, 0, 225),
            text = stringResource(R.string.greeting_message, formatArgs = arrayOf(name)),
            modifier = modifier.padding(24.dp)
        )
    }
}

@Composable
fun Signature(name: String, modifier: Modifier = Modifier) {
    Surface (modifier = modifier, color = Color(133, 200, 50, 200)) {
        Text(
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            lineHeight = 33.sp,
            color = Color(65, 30, 0, 150),
            text = stringResource(R.string.signature_message, formatArgs = arrayOf(name)),
            modifier = modifier.padding(24.dp)
        )
    }
}

@Composable
fun GreetingImage(modifier: Modifier = Modifier) {
    val houseImg =
        painterResource(R.drawable.front_of_a_house_with_many_tree_isolated_on_white_background_free_vector)
    Surface (modifier) {
        Image(
            painter = houseImg,
            contentDescription = stringResource(
                R.string.white_house_with_tree_description
            )
        )
    }
}

@Composable
fun GreetingCard(recipientName: String, senderName: String, modifier: Modifier = Modifier) {
    var funFactId by remember { mutableIntStateOf(1) }
    var showFunFact by remember {mutableStateOf(false)}
    val curFunFact = when (funFactId) {
        1 -> R.string.fun_fact_one
        2 -> R.string.fun_fact_two
        3 -> R.string.fun_fact_three
        4 -> R.string.fun_fact_four
        else -> R.string.fun_fact_five
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting(recipientName, modifier)
        Box (modifier.padding(10.dp)) {
            GreetingImage(modifier)
            if (showFunFact)
            ElevatedCard (
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
                modifier = modifier
                    .fillMaxWidth(),
                colors = CardColors(Color(150, 0, 250), Color(255, 255, 255, 255), Color(0, 255, 123, 255), Color(89, 65, 225, 255)
                )
            ) {
                Text(
                    text = stringResource(curFunFact),
                    modifier = modifier
                        .padding(26.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    lineHeight = 17.sp,
                )
                Button(onClick = {showFunFact = false}, modifier.align(Alignment.End), shape = CircleShape) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "close", modifier)
                }
            }
        }
        Signature(senderName, modifier)
        Surface (modifier.align(Alignment.CenterHorizontally)) {
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {showFunFact = true; funFactId = (1..5).random()}) {
                    Text(
                        stringResource(R.string.fun_fact_button_name),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        GreetingCard("Ruby", "Bridgette")
    }
}