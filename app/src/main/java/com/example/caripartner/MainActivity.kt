package com.example.caripartner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.caripartner.ui.screens.homeScreen.HomeViewModel
import com.example.caripartner.ui.screens.loginScreen.LoginViewModel
import com.example.caripartner.ui.screens.profileScreen.BiodataViewModel
import com.example.caripartner.ui.screens.recommendationScreen.RecommendationViewModel
import com.example.caripartner.ui.screens.profileScreen.ProfileViewModel
import com.example.caripartner.ui.theme.CariPartnerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val loginViewModel = viewModel(modelClass = LoginViewModel::class.java)
            val recommendationViewModel = viewModel(modelClass = RecommendationViewModel::class.java)
            val homeViewModel = viewModel(modelClass = HomeViewModel::class.java)
            val profileViewModel = viewModel(modelClass = ProfileViewModel::class.java)
            CariPartnerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Navigation(loginViewModel = loginViewModel, homeViewModel = homeViewModel, recommendationViewModel = recommendationViewModel, profileViewModel = profileViewModel)
//                    BiodataScreen(profileViewModel = profileViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CariPartnerTheme {
        Greeting("Android")
    }
}