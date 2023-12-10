

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.caripartner.R
import com.example.caripartner.ui.screens.loginScreen.LoginViewModel
import com.example.caripartner.ui.screens.partnerScreen.PartnerScreen
import com.example.caripartner.ui.screens.profileScreen.ProfileScreen
import com.example.caripartner.ui.screens.searchScreen.SearchScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.caripartner.data.model.User
import com.example.caripartner.ui.screens.homeScreen.HomeViewModel
import com.example.caripartner.ui.screens.partnerScreen.SearchBar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun HomeScreen( homeViewModel: HomeViewModel?=null){

    var profiles: User by remember { mutableStateOf(User()) }
    homeViewModel?.GetUserData(){user ->
        profiles = user
    }
    println(profiles.isAvailable)
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .padding(top = 12.dp, bottom = 12.dp)

                    ){
                        Row(modifier = Modifier.padding(start = 16.dp)){
                            Image(
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp),
                                painter = painterResource(id = R.drawable.small_logo),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                            )
                            Text(
                                text = "CariPartner",
                                fontSize = 20.sp,
                                lineHeight = 30.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF4B4EFC),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Column (
                        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterVertically),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(154.dp)
                            .background(
                                color = Color(0xFF4B4EFC),
                                shape = RoundedCornerShape(size = 0.dp)
                            )
                    ) {
                        Column(modifier = Modifier.padding(start=16.dp,end=16.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Selamat datang kembali Ghina Tri👋",
                                    fontSize = 25.sp,
                                    lineHeight = 20.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFFFFFFF)
                                )
//                            Image(
//                                painter = painterResource(id = R.drawable.notif),
//                                contentDescription = "image description",
//                                contentScale = ContentScale.FillBounds,
//                                modifier = Modifier.height(30.dp).width(30.dp),
//                            )
                            }
                        }
                    }
                    //Partner Lomba
                    Column(modifier = Modifier.padding(start=16.dp,end=16.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Partner Lomba",
                                fontSize = 20.sp,
                                lineHeight = 30.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF101828),
                            )
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(
                                    text = "Lihat semua",
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF4B4EFC),
                                )
                            }
                        }
                        Column() {
                            Card(
                                modifier = Modifier
                                    .shadow(
                                        elevation = 2.dp,
                                        spotColor = Color(0x40000000),
                                        ambientColor = Color(0x40000000)
                                    )
                                    .width(180.dp)
                                    .height(202.98267.dp)
                                    .background(
                                        color = Color(0xFFFFFFFF),
                                        shape = RoundedCornerShape(size = 4.dp)
                                    )
                                    .padding(
                                        start = 10.dp,
                                        top = 10.dp,
                                        end = 10.dp,
                                        bottom = 10.dp
                                    )
                            ) {
                                Column (modifier = Modifier.height(101.dp)) {
                                    Image(
                                        painter = painterResource(id =  R.drawable.tesgab),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth()
                                    )
                                }
                                Column (modifier = Modifier
                                    .fillMaxWidth()
                                    .height(101.dp)
                                    .background(color = Color(0xFFFFFFFF)),
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)) {
                                    Text(
                                        text = "Karena Musika",
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF101828),
                                        modifier = Modifier.padding(top=8.dp)
                                    )
                                    Text(
                                        text ="Teknologi, Seni",
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF4B4EFC),
                                    )
                                    Text(
                                        text = if(profiles.isAvailable == true || profiles.isAvailable == null) "Bersedia" else "Tidak Bersedia",
                                        fontSize = 14.sp,
                                        lineHeight = 40.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF027A48),
                                        textAlign = TextAlign.Right,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }

                            }
                        }
                        Column() {
                            Card(
                                modifier = Modifier
                                    .shadow(
                                        elevation = 2.dp,
                                        spotColor = Color(0x40000000),
                                        ambientColor = Color(0x40000000)
                                    )
                                    .width(180.dp)
                                    .height(202.98267.dp)
                                    .background(
                                        color = Color(0xFFFFFFFF),
                                        shape = RoundedCornerShape(size = 4.dp)
                                    )
                                    .padding(
                                        start = 10.dp,
                                        top = 10.dp,
                                        end = 10.dp,
                                        bottom = 10.dp
                                    )
                            ) {
                                Column (modifier = Modifier.height(101.dp)) {
                                    Image(
                                        painter = painterResource(id =  R.drawable.tesjek),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth()
                                    )
                                }
                                Column (modifier = Modifier
                                    .fillMaxWidth()
                                    .height(101.dp)
                                    .background(color = Color(0xFFFFFFFF)),
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)) {
                                    Text(
                                        text = "Dzaki",
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF101828),
                                        modifier = Modifier.padding(top=8.dp)
                                    )
                                    Text(
                                        text = profiles.field?:"default",
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF4B4EFC),
                                    )
                                    Text(
                                        text = if(profiles.isAvailable == true || profiles.isAvailable == null) "Bersedia" else "Tidak Bersedia",
                                        fontSize = 14.sp,
                                        lineHeight = 40.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF027A48),
                                        textAlign = TextAlign.Right,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }

                            }
                        }
                        Column() {
                            Card(
                                modifier = Modifier
                                    .shadow(
                                        elevation = 2.dp,
                                        spotColor = Color(0x40000000),
                                        ambientColor = Color(0x40000000)
                                    )
                                    .width(180.dp)
                                    .height(202.98267.dp)
                                    .background(
                                        color = Color(0xFFFFFFFF),
                                        shape = RoundedCornerShape(size = 4.dp)
                                    )
                                    .padding(
                                        start = 10.dp,
                                        top = 10.dp,
                                        end = 10.dp,
                                        bottom = 10.dp
                                    )
                            ) {
                                Column (modifier = Modifier.height(101.dp)) {
                                    Image(
                                        painter = painterResource(id =  R.drawable.tesgab),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth()
                                    )
                                }
                                Column (modifier = Modifier
                                    .fillMaxWidth()
                                    .height(101.dp)
                                    .background(color = Color(0xFFFFFFFF)),
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)) {
                                    Text(
                                        text = profiles.name?:"default",
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF101828),
                                        modifier = Modifier.padding(top=8.dp)
                                    )
                                    Text(
                                        text = profiles.field?:"default",
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF4B4EFC),
                                    )
                                    Text(
                                        text = if(profiles.isAvailable == true || profiles.isAvailable == null) "Bersedia" else "Tidak Bersedia",
                                        fontSize = 14.sp,
                                        lineHeight = 40.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF027A48),
                                        textAlign = TextAlign.Right,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }

                            }
                        }
                        Column() {
                            Card(
                                modifier = Modifier
                                    .shadow(
                                        elevation = 2.dp,
                                        spotColor = Color(0x40000000),
                                        ambientColor = Color(0x40000000)
                                    )
                                    .width(180.dp)
                                    .height(202.98267.dp)
                                    .background(
                                        color = Color(0xFFFFFFFF),
                                        shape = RoundedCornerShape(size = 4.dp)
                                    )
                                    .padding(
                                        start = 10.dp,
                                        top = 10.dp,
                                        end = 10.dp,
                                        bottom = 10.dp
                                    )
                            ) {
                                Column (modifier = Modifier.height(101.dp)) {
                                    Image(
                                        painter = painterResource(id =  R.drawable.tesg),
                                        contentDescription = "image description",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth()
                                    )
                                }
                                Column (modifier = Modifier
                                    .fillMaxWidth()
                                    .height(101.dp)
                                    .background(color = Color(0xFFFFFFFF)),
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top)) {
                                    Text(
                                        text = profiles.name?:"default",
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF101828),
                                        modifier = Modifier.padding(top=8.dp)
                                    )
                                    Text(
                                        text = profiles.field?:"default",
                                        fontSize = 12.sp,
                                        lineHeight = 16.sp,
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF4B4EFC),
                                    )
                                    Text(
                                        text = if(profiles.isAvailable == true || profiles.isAvailable == null) "Bersedia" else "Tidak Bersedia",
                                        fontSize = 14.sp,
                                        lineHeight = 40.sp,
                                        fontWeight = FontWeight(600),
                                        color = Color(0xFF027A48),
                                        textAlign = TextAlign.Right,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }

                            }
                        }
                    }
                }
            }
@Composable
fun SearchBar(
    onSearchTextChanged: (String) -> Unit,
    placeholder: String = "Ketik untuk mencari partner"
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier
            .size(width = 400.dp, height = 90.dp)
            .padding(horizontal = 0.dp, vertical = 22.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearchTextChanged(it.text)
            },
            singleLine = true,
            textStyle = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .width(358.dp)
                .height(44.dp),
            decorationBox = { innerTextField ->
                if(text.text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(horizontal = 36.dp, vertical = 12.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(8.dp),
                    content = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        innerTextField()
                    }
                )
            }
        )
    }
}