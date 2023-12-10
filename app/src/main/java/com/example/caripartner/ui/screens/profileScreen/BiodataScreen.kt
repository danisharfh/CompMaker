package com.example.caripartner.ui.screens.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caripartner.data.model.User

data class DataUser(
    var uid: String = "",
    var name: String = "",
    var nomorwa: String = "",
    var prodi: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true)
@Composable
fun BiodataScreen(BiodataViewModel: BiodataViewModel) {
    var nama by remember{ mutableStateOf("") }
    var nomorwa by remember{ mutableStateOf("") }
    var prodi by remember{ mutableStateOf("") }
    var profiles: User by remember { mutableStateOf(User()) }
    BiodataViewModel?.GetUserData(){user ->
        profiles = user
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
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
        ) {
            Icon(
                Icons.Default.ChevronLeft,
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 16.dp, end = 120.dp)
            )
            Text(
                text = "Biodata",
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight(600),
                color = Color(0xFF383B46),
                textAlign = TextAlign.Center
            )
        }
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Nama*",
                modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                fontSize = 16.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF101828)
            )
            TextField(
                value = profiles.name?:"",
                onValueChange = {nama=it},
                modifier = Modifier
                    .width(358.dp)
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
                label = {
                    Text(
                        text = "Masukkan nama",
                        fontSize = 14.sp
                    )
                }
            )
            Text(
                text = "Nomor WA Aktif",
                modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                fontSize = 16.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF101828)
            )
            TextField(
                value = nomorwa,
                onValueChange = {nomorwa=it},
                modifier = Modifier
                    .width(358.dp)
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
                label = {
                    Text(
                        text = "Masukkan nomor WAmu",
                        fontSize = 14.sp
                    )
                }
            )
            Text(
                text = "Prodi*",
                modifier = Modifier.padding(top = 20.dp, start = 16.dp),
                fontSize = 16.sp,
                lineHeight = 12.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF101828)
            )
            TextField(
                value = prodi,
                onValueChange = {prodi=it},
                modifier = Modifier
                    .width(358.dp)
                    .height(55.dp)
                    .padding(start = 10.dp, end = 10.dp),
                label = {
                    Text(
                        text = "Masukkan nama prodimu",
                        fontSize = 14.sp
                    )
                }
            )
        }

        Spacer(
            modifier = Modifier
                .padding(top = 340.dp)
        )
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(Color(0xFF4B4EFC)),
            modifier = Modifier
                .width(358.dp)
                .height(52.dp)
                .background(color = Color(0xFF4B4EFC), shape = RoundedCornerShape(size = 12.dp))
        ) {
            Text(
                text = "Simpan",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 20.dp)
            )
        }
    }
}
