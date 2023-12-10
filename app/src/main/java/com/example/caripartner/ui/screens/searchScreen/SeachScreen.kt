package com.example.caripartner.ui.screens.searchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caripartner.R
import com.example.caripartner.ui.theme.CariPartnerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var presses by remember { mutableIntStateOf(0) }
    CariPartnerTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
                Row (horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .shadow
                            (
                            elevation = 8.dp,
                            spotColor = Color(0x296481DC),
                            ambientColor = Color(0x296481DC)
                        )
                        .width(390.dp)
                        .height(100.dp)
                        .padding(top = 16.dp, bottom = 16.dp)
                    )
                {
                    Text(
                        text = "Chat",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF383B46),
                        textAlign = TextAlign.Center
                    )
                }
                SearchBarLomba(
                    onSearchTextChanged = { _ ->
                        // Lakukan sesuatu dengan nilai searchText (misalnya, filter data berdasarkan pencarian)
                    },
                    placeholder = "Search Partner"
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tesdan),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(CircleShape)
                    )
                    Column (
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Danisha Rafifah",
                            fontSize = 14.sp,
                            fontFamily = FontFamily.SansSerif,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF101828)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tesa),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(CircleShape)
                    )
                    Column (
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Rosa",
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF101828)
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 8.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tesjek),
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .clip(CircleShape)
                    )
                    Column (
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Dzaki",
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF101828)
                        )
                    }
                }

//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 16.dp, top = 8.dp, end = 16.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.muhzaqi),
//                        contentDescription = "",
//                        contentScale = ContentScale.FillWidth,
//                        modifier = Modifier
//                            .width(50.dp)
//                            .height(50.dp)
//                            .clip(CircleShape)
//                    )
//                    Column (
//                        horizontalAlignment = Alignment.Start,
//                        verticalArrangement = Arrangement.Center,
//                        modifier = Modifier
//                            .padding(start = 10.dp)
//                            .fillMaxWidth()
//                    ) {
//                        Text(
//                            text = "Rayhan Syahrizal Ami Putra",
//                            fontSize = 14.sp,
//                            lineHeight = 18.sp,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF101828)
//                        )
//                        Text(
//                            text = "Info lomba UI/UX diadain sama UI cuma setahun sekali nih, ada yang tertarik? #UIFestbyUI",
//                            fontSize = 16.sp,
//                            lineHeight = 18.sp,
//                            fontWeight = FontWeight(600),
//                            color = Color(0xFF101828)
//                        )
//                    }
//                }

                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = { presses++ },  shape = CircleShape,) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
//                        Text(
//                            modifier = Modifier.padding(8.dp),
//                            text =
//                            """ You have pressed the floating action button $presses times. """.trimIndent(),
//                        )
                    }
                }
//                floatingActionButton = {
//                    FloatingActionButton(onClick = { presses++ }) {
//                        Icon(Icons.Default.Add, contentDescription = "Add")
//                    }
//                }
            }
        }
    }
}



@Composable
fun SearchBarLomba(
    onSearchTextChanged: (String) -> Unit,
    placeholder: String = "..."
) {
    var text by remember { mutableStateOf(TextFieldValue()) }

    Box(
        modifier = Modifier
            .size(width = 400.dp, height = 90.dp)
            .padding(horizontal = 16.dp, vertical = 22.dp)
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