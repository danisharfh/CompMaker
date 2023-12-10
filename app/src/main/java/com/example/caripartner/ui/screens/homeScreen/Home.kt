package com.example.caripartner.ui.screens.homeScreen

import HomeScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.caripartner.HomeRoutes
import com.example.caripartner.LoginRoutes
import com.example.caripartner.ui.screens.loginScreen.LoginScreen
import com.example.caripartner.ui.screens.loginScreen.LoginViewModel
import com.example.caripartner.ui.screens.partnerScreen.PartnerScreen
import com.example.caripartner.ui.screens.profileScreen.BiodataViewModel
import com.example.caripartner.ui.screens.profileScreen.ProfileScreen
import com.example.caripartner.ui.screens.profileScreen.ProfileViewModel
import com.example.caripartner.ui.screens.searchScreen.SearchScreen
import com.example.caripartner.ui.screens.recommendationScreen.Recommendation
import com.example.caripartner.ui.screens.recommendationScreen.RecommendationViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

//fun Home(loginViewModel: LoginViewModel?){
//    val loginUiState = loginViewModel?.loginUiState
////    println(loginUiState)
//    Text(text="This is home screen"+loginViewModel?.userId)
//
//    Button(onClick = {
//    val logout = Firebase.auth.signOut()
//    logout },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 100.dp, top = 200.dp, bottom = 100.dp, end = 100.dp)
//            .background(color = Color(0xFF4B4EFC), shape = RoundedCornerShape(size = 12.dp)),
//        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B4EFC))
//    ) {
//        Text(text = "Logout",
//            fontSize = 18.sp,
//            lineHeight = 28.sp,
//            fontWeight = FontWeight(600),
//            color = Color(0xFFFFFFFF),)
//    }
//}

// Obtain an instance of FirebaseAuth
//val firebaseAuth = FirebaseAuth.getInstance()
//val logout = Firebase.auth.signOut()


enum class BottomNavRoutes{
    Home,
    Search,
    Partner,
    Chat,
    Profile
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(loginViewModel: LoginViewModel?, homeViewModel: HomeViewModel?, profileViewModel: ProfileViewModel?,recommendationViewModel: RecommendationViewModel?){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentDestination?.route != LoginRoutes.SignIn.name) {
                NavigationBar {
                    BottomNavigationItem().bottomNavigationItems().forEachIndexed { _, navigationItem ->
                        NavigationBarItem(
                            selected = navigationItem.route == currentDestination?.route,
                            label = {
                                Text(navigationItem.label)
                            },
                            icon = {
                                Icon(
                                    navigationItem.icon,
                                    contentDescription = navigationItem.label
                                )
                            },
                            onClick = {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavRoutes.Home.name,
            modifier = Modifier.padding(paddingValues = paddingValues))
        {
            composable(route = LoginRoutes.SignIn.name){

                LoginScreen(onNavToHomePage = {
                    navController.navigate(HomeRoutes.Home.name){
                        launchSingleTop = true
                        popUpTo(route = LoginRoutes.SignIn.name){
                            inclusive = true
                        }
                    }
                },
                    loginViewModel = loginViewModel

                ) {
                    navController.navigate(LoginRoutes.SignUp.name){
                        launchSingleTop = true
                        popUpTo(LoginRoutes.SignIn.name){
                            inclusive = true
                        }
                    }
                }
            }
            composable(BottomNavRoutes.Home.name){
                HomeScreen(homeViewModel=homeViewModel)
            }
            composable(BottomNavRoutes.Search.name) {
                SearchScreen()
            }
            composable(BottomNavRoutes.Partner.name) {
                PartnerScreen()
                Recommendation(recommendationViewModel = recommendationViewModel)
            }
            composable(BottomNavRoutes.Profile.name) {
                ProfileScreen(profileViewModel = profileViewModel, navController)
            }
        }
    }

}

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Beranda",
                icon = Icons.Filled.Home,
                route = BottomNavRoutes.Home.name
            ),
            BottomNavigationItem(
                label = "Chat",
                icon = Icons.Filled.Search,
                route = BottomNavRoutes.Search.name
            ),
            BottomNavigationItem(
                label = "Partner",
                icon = Icons.Filled.AddCircle,
                route = BottomNavRoutes.Partner.name
            ),
//            BottomNavigationItem(
//                label = "Pesan",
//                icon = Icons.Filled.MailOutline,
//                route = BottomNavRoutes.Chat.name
//            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                route = BottomNavRoutes.Profile.name
            ),
        )
    }
}