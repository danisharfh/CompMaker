package com.example.caripartner.ui.screens.profileScreen

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material.icons.outlined.Subtitles
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.caripartner.HomeRoutes
import com.example.caripartner.LoginRoutes
import com.example.caripartner.R
import com.example.caripartner.ui.screens.homeScreen.BottomNavRoutes
import com.example.caripartner.data.model.User
import com.example.caripartner.ui.screens.loginScreen.LoginScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    fun getToggleState(): Boolean {
        return sharedPreferences.getBoolean("toggle_state", false)
    }

    fun setToggleState(isChecked: Boolean) {
        sharedPreferences.edit().putBoolean("toggle_state", isChecked).apply()
    }
}

//@Preview(showBackground = true)
@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel?=null, navController: NavController) {
    val context = LocalContext.current
    val preferenceManager = remember(context) { PreferenceManager(context) }
    val checkedState = remember { mutableStateOf(preferenceManager.getToggleState()) }
    var profiles: User by remember { mutableStateOf(User()) }
    profileViewModel?.GetUserData(){user ->
        profiles = user
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .shadow(
                    elevation = 8.dp,
                    spotColor = Color(0x296481DC),
                    ambientColor = Color(0x296481DC)
                )
                .width(390.dp)
                .height(100.dp)
                .padding(top = 16.dp, bottom = 16.dp)
        ){
            Text(
                text = "Profile",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(600),
                color = Color(0xFF383B46),
                textAlign = TextAlign.Center
            )
        }

        Spacer(
            modifier = Modifier
                .padding(top = 24.dp)
        )

        //Card Profile
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(358.dp)
                .height(100.dp)
                .background(color = Color(0xFFF5F5FF), shape = RoundedCornerShape(size = 8.dp))
                .padding(start = 16.dp, bottom = 16.dp, end = 16.dp, top = 16.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Image(
                painter = painterResource(id = R.drawable.tesg),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp)
                    .background(
                        MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            )
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
                    .padding(start = 24.dp)
            ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Column (
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = profiles.name?:"",
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF101828),
                            modifier = Modifier
                                .width(72.dp)
                                .height(18.dp)
                        )
                        Text(
                            text = "UI/UX",
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF1E22FB)
                        )
                    }
                }
            }
            Row (
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            ){
                if(checkedState.value){
                    Image(
                        painter = painterResource(id = R.drawable.bersedia),
                        contentDescription = "",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(12.dp)
                            .height(18.dp)
                    )
                }else{
                    Image(
                        painter = painterResource(id = R.drawable.tidak_bersedia),
                        contentDescription = "",
                        contentScale = ContentScale.None,
                        modifier = Modifier
                            .width(12.dp)
                            .height(18.dp)
                    )
                }
                Text(
                    text = if(checkedState.value) "Bersedia" else "Tidak Bersedia",
                    fontSize = 11.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(600),
                    color = if(checkedState.value) Color(0xFF4B4EFC) else Color(0xFFB42318),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .width(100.dp)
                        .height(18.dp)
                )
            }
        }
        Text(
            text = "Personal",
            fontSize = 20.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, top = 16.dp, bottom = 16.dp)
        )

        //Personal Menu
        Column (
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .width(357.dp)
                .height(57.dp)
                .background(color = Color(0xFFF2F4F7))
                .padding(start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Row (
                horizontalArrangement = Arrangement.spacedBy(120.dp)
            ){
                Row {
                    Icon(
                        Icons.Default.LightMode,
                        contentDescription = "",
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp)
                    )
                    Text(
                        text = "Status Kesediaan",
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF101828),
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                }

                Switch(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                        preferenceManager.setToggleState(it)
                                      },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color(0xFF4B4EFC),
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color.Gray
                    ),
                    modifier = Modifier
                        .width(48.dp)
                        .height(25.dp)
                )
            }
        }
        ClickablePersonal(Icons.Outlined.Person,"Biodata", "biodata", navController)
        ClickablePersonal(Icons.Outlined.StarOutline,"Bidang Dikuasai", "bidangDikuasai", navController)

        Text(
            text = "Pengaturan",
            fontSize = 20.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 20.dp, top = 16.dp, bottom = 16.dp)
        )
        Button(
            onClick = {
                Firebase.auth.signOut().also {
                    navController.navigate(LoginRoutes.SignIn.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFF04438)),
            modifier = Modifier
                .width(357.dp)
                .height(56.dp)
                .background(color = Color(0xFFF04438), shape = RoundedCornerShape(size = 12.dp))

        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(imageVector = Icons.Default.Logout, contentDescription = "")
                Text(
                    text = "Keluar",
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }
        }
    }
}

@Composable
fun ClickablePersonal(icon: ImageVector, label: String, destination: String, navController: NavController) {
    Column(
        modifier = Modifier
            .width(357.dp)
            .height(57.dp)
            .background(color = Color(0xFFF2F4F7))
            .padding(start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp)
            .clickable { navController.navigate(destination) }
    ) {
        Row {
            Icon(
                icon,
                contentDescription = ""
            )
            Text(
                text = label,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF101828),
                modifier = Modifier
                    .padding(start = 12.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
