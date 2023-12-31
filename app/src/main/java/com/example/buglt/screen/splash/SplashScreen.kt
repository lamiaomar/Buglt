package com.example.buglt.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.buglt.R
import com.example.buglt.navigation.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val rotate = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        rotate.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        delay(300)
        navController.navigate(Screens.Home.route)

    }

    Splash(rotate = rotate.value)
}

@Composable
fun Splash(rotate: Float) {
    Box(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(Color.Blue, Color.Cyan, Color.Yellow)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 70.sp,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .rotate(rotate)
        )
    }
}