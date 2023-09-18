package com.example.animationemporium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.animationemporium.ui.theme.AnimationEmporiumTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationEmporiumTheme (useDarkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimationEmporiumApp()
                }
            }
        }
    }
}

@Composable
@Preview
fun  AnimationEmporiumPreview() {
    AnimationEmporiumTheme(useDarkTheme = false){
        AnimationEmporiumApp()
    }
}

@Composable
fun AnimationEmporiumApp() {
    Column (
        modifier = Modifier
    ) {
        DemoPicker(
            Modifier
                .weight(1f)
                .padding(8.dp))


        Column (
            modifier = Modifier
                .padding(24.dp)
                .weight(6f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            AnimVisibilityDemo(Modifier.weight(3f))
            InfoSecton(Modifier.weight(1f))
        }


    }
}

@Composable
fun DemoPicker(modifier: Modifier = Modifier) {
    Card(
        modifier
            .fillMaxSize()) {

        Row(
        ) {
            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Last Animation",
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .size(100.dp)
                    .background(Brush.horizontalGradient(listOf(Color.White, Color.Transparent)))
            )
            Spacer(modifier.fillMaxWidth())
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next Animation",
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .size(100.dp)
                    .background(Brush.horizontalGradient(listOf(Color.Transparent, Color.White)))
            )
        }
    }
}

@Composable
fun InfoSecton(modifier: Modifier = Modifier) {
    Card(
        modifier
            .fillMaxSize()) {
        Column (
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(ScrollState(0))
        ) {
            Text(
                text = stringResource(R.string.info_section_text),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(R.string.info_section_body_example),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AnimVisibilityDemo(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(true) }

    Card (
        modifier = modifier
            .fillMaxSize(),
        onClick = { visible = !visible }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(
                R.string.tap_to_the_text, if (visible) "hide" else "show" ),)

            AnimatedVisibility(
                visible,
                enter = slideInVertically() + expandVertically() + fadeIn(),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Card (
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                        contentColor = MaterialTheme.colorScheme.onTertiaryContainer)
                ) {
                    Text(
                        text = stringResource(R.string.demo_1_text_2),
                        Modifier.padding(12.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun CardText(text: String, standOut: Boolean, modifier: Modifier = Modifier) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
    ) {
        Text(
            text = text,
            color = if(standOut) Color.Red else Color.Black,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = if(standOut) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(16.dp)
            )
    }

}