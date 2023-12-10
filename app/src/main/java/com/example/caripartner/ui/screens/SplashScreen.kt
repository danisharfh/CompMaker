package com.example.caripartner.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caripartner.R
import com.example.caripartner.ui.theme.CariPartnerTheme

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        CompMakerLogo()
        BlueDotsBackground()
    }
}

@Composable
fun CompMakerLogo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Customize the appearance of your logo
        // Here, I'm using a simple Text with a large font size and white color
        Text(
            text = "COMPMAKER",
            color = Color.White,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun BlueDotsBackground() {
    val dotColor = Color(0xFF1E88E5) // Blue color
    val dotRadius = 8.dp

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        // You can adjust the number and arrangement of dots based on your design
        for (i in 0 until 100) {
            val x = randomOffset(size.width)
            val y = randomOffset(size.height)
            drawCircle(dotColor, center = Offset(x, y), radius = dotRadius.toPx())
        }
    }
}

fun randomOffset(max: Float): Float {
    return (0..max.toInt()).random().toFloat()
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    CariPartnerTheme {
        SplashScreen()
    }
}
