package com.example.caripartner.ui.screens.recommendationScreen//package com.example.caripartner.ui.screens.recommendationScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.example.caripartner.R
import com.example.caripartner.data.model.User
import com.example.caripartner.ui.theme.CariPartnerTheme
import com.example.caripartner.ui.theme.SwipeableCardTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch


//data class MatchProfile(
//    val name: String,
//    @DrawableRes val drawableResId: Int,
//)

//var profiles = listOf(
//    MatchProfile("Erlich Bachman", R.drawable.profil),
//    MatchProfile("Richard Hendricks", R.drawable.profil),
//    MatchProfile("Laurie Bream", R.drawable.profil),
//    MatchProfile("Russ Hanneman", R.drawable.profil),
//    MatchProfile("Dinesh Chugtai", R.drawable.profil),
//    MatchProfile("Monica Hall", R.drawable.profil),
//    MatchProfile("Bertram Gilfoyle", R.drawable.profil),
//
//    MatchProfile("Peter Gregory", R.drawable.pana),
//    MatchProfile("Jared Dunn", R.drawable.pana),
//    MatchProfile("Nelson Bighetti", R.drawable.pana),
//    MatchProfile("Gavin Belson", R.drawable.pana),
//    MatchProfile("Jian Yang", R.drawable.pana),
//    MatchProfile("Jack Barker", R.drawable.pana),
//)

//var profiles:List<User> = mutableListOf()



@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun Recommendation(recommendationViewModel: RecommendationViewModel? = null){
    var profiles: List<User> by remember { mutableStateOf(emptyList()) }

    recommendationViewModel?.GetRecommendation(){ matchingUsers ->
//        print("++++++++++++++")
//        println(matchingUsers)

        profiles = matchingUsers
//        print("-------Hey--------")
//        print(profiles[0].profil!!)
//        recommendationViewModel.getImageUrl(profiles[0].profil!!){url->
//            println(url)
//        }
    }
    print("-------Hey--------")
    var userData: User by remember { mutableStateOf(User()) }

    recommendationViewModel?.GetUserData(){user ->
        userData = user
    }

    var selectedUser by remember { mutableStateOf<String?>(null) }
    var swipeIndex by remember { mutableStateOf(0) }

    SwipeableCardTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(android.graphics.Color.parseColor("#D0E4FF"))
                )
                .systemBarsPadding()
        ) {
            Box {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .background(color = Color.White),
                ) {
                    Text(text = "Rekomendasi",modifier= Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF101828),
                            textAlign = TextAlign.Center,
                        )
                    )
                }

                val states = profiles.reversed()
                    .map { it to rememberSwipeableCardState() }
                var hint by remember {
                    mutableStateOf("Swipe a card or press a button below")
                }

                Hint(hint)

                val scope = rememberCoroutineScope()
                Box(
                    Modifier
                        .padding(0.dp)
                        .fillMaxSize()
                        .aspectRatio(0.715f)
                        .align(Alignment.Center)) {
                    states.forEach { (matchProfile, state) ->
                        if (state.swipedDirection == null) {
                            ProfileCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .swipableCard(
                                        state = state,
//                                        blockedDirections = listOf(Direction.Down,Direction.Up),
                                        onSwiped = {
                                            // swipes are handled by the LaunchedEffect
                                            // so that we track button clicks & swipes
                                            // from the same place
                                        },
                                        onSwipeCancel = {
                                            Log.d("Swipeable-Card", "Cancelled swipe")
                                            hint = "You canceled the swipe"
                                        }
                                    ),
                                matchProfile = matchProfile,
                                recommendationViewModel = recommendationViewModel
                            )
                        }
                        LaunchedEffect(matchProfile, state.swipedDirection) {
                            if (state.swipedDirection != null) {
                                hint = "You swiped ${stringFrom(state.swipedDirection!!)}"
                            }
                        }
                    }
                }
                Row(
                    Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 24.dp, vertical = 32.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CircleButton(
                        onClick = {
                            scope.launch {
                                val last = states.reversed()
                                    .firstOrNull {
                                        it.second.offset.value == Offset(0f, 0f)
                                    }?.second
                                last?.swipe(Direction.Left)
                                swipeIndex++
                            }

                        },
                        icon = Icons.Rounded.ArrowForward
                    )
                    CircleButton(
                        onClick = {
                            scope.launch {
                                val last = states.reversed()
                                    .firstOrNull {
                                        it.second.offset.value == Offset(0f, 0f)
                                    }?.second

                                last?.swipe(Direction.Right)
                                swipeIndex++
                            }
                        },
                        icon = Icons.Rounded.AccountCircle
                    )
                }
            }
        }
    }
}


@Composable
private fun CircleButton(
    onClick: () -> Unit,
    icon: ImageVector,
) {
    IconButton(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.Blue)
            .size(70.dp)
            .border(2.dp, Color.Blue, CircleShape),
        onClick = onClick
    ) {
        Icon(icon, null,
            tint = Color.White,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
private fun ProfileCard(
    modifier: Modifier,
//    matchProfile: MatchProfile,
    matchProfile:User,
    recommendationViewModel: RecommendationViewModel? = null
) {
    val imagePaths = listOf(
        "R.drawable.tesdan",
        "R.drawable.tesg",
        "R.drawable.tesjek",
        "R.drawable.tesgab"
    )

    var currentImageIndex by remember { mutableStateOf(0) }

    Card(modifier) {
        Box() {
            Image(
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                painter = rememberImagePainter(data = imagePaths.getOrElse(currentImageIndex) { "" }),
                contentDescription = null)
            Scrim(Modifier.align(Alignment.BottomCenter))
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.BottomStart)
                    .padding(bottom = 80.dp)
                ) {
                Text(text = matchProfile.name!!,
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 36.sp),
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF)
                )
                Text(
                    text = "Minat Lomba:",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),

                        )
                )
                matchProfile.preferences?.forEach { preference ->
                    // Lakukan sesuatu dengan setiap elemen dalam list
                    Text(
                        text = "- $preference",
                        style = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),

                            )
                        )
                }
            }
        }
    }
}

@Composable
private fun Hint(text: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 80.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun TransparentSystemBars() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = useDarkIcons,
            isNavigationBarContrastEnforced = false
        )
        onDispose {}
    }
}

private fun stringFrom(direction: Direction): String {
    return when (direction) {
        Direction.Left -> "Left 👈"
        Direction.Right -> "Right 👉"
        Direction.Up -> "Up 👆"
        Direction.Down -> "Down 👇"
    }
}


@Composable
fun Scrim(modifier: Modifier = Modifier) {
    Box(
        modifier
            .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
            .height(180.dp)
            .fillMaxWidth())
}


@Preview(showSystemUi = true)
@Composable
fun PrevRecommendationScreen(){
    CariPartnerTheme {
        Recommendation()
    }
}


@Composable
fun UserDetailScreen(userId: String, onNavigateUp: () -> Unit) {
    // Tampilkan detail user
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Detail User: $userId")

        // Tambahkan opsi untuk kembali ke halaman daftar user
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateUp) {
            Text("Back to User List")
        }
    }
}
