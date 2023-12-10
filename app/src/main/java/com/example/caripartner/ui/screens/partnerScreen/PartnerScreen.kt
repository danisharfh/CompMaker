package com.example.caripartner.ui.screens.partnerScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.caripartner.R
import com.example.caripartner.ui.theme.CariPartnerTheme

@Composable
fun PartnerScreen() {
    var showProdi by remember { mutableStateOf(false) }
    val prodiOptions = listOf("Teknik Informatika", "Sistem Informasi", "Teknologi Informasi")

    var showBidang by remember { mutableStateOf(false) }
    val bidangOptions = listOf("Poster", "Debat", "Pidato", "UI/UX", "Infografik")

    var showBersedia by remember { mutableStateOf(false) }
    val bersediaOptions = listOf("Bersedia", "Tidak bersedia")

    CariPartnerTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    "Cari Partner",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                SearchBar(
                    onSearchTextChanged = { _ ->
                        // Lakukan sesuatu dengan nilai searchText (misalnya, filter data berdasarkan pencarian)
                    },
                    placeholder = "Ketik untuk mencari partner"
                )
                Box(
                    modifier = Modifier
                        .size(width = 358.dp, height = 50.dp)
                        .background(
                            Color.Blue,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Row {
                        Text(
                            text = "Rekomendasi partner lomba",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 16.dp, bottom = 16.dp)
                        )
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
                        ) {
                            Text(
                                text = "Coba Sekarang",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Blue
                            )
                        }
                    }
                }
                Row (
                    modifier = Modifier
                        .padding(start = 16.dp, top = 24.dp, bottom = 16.dp)
                        .size(width = 358.dp, height = 36.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Filter:",
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(end = 4.dp)
                    )
                    Button(
                        onClick = { showProdi = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .padding(vertical = 0.dp, horizontal = 4.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Blue,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "Prodi",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Blue
                        )
                    }
                    ProdiAlertDialogue(prodiOptions, showProdi) { showProdi = it }

                    Button(
                        onClick = { showBidang = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .padding(vertical = 0.dp, horizontal = 4.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Blue,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "Bidang",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Blue
                        )
                    }
                    BidangAlertDialogue(bidangOptions, showBidang) { showBidang = it }

                    Button(
                        onClick = { showBersedia = true },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .padding(vertical = 0.dp, horizontal = 4.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Blue,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = "Status",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Blue
                        )
                    }
                }
                BersediaAlertDialogue(bersediaOptions, showBersedia) { showBersedia = it }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.tesdan),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(68.dp)
                            .height(68.dp)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                shape = RoundedCornerShape(12.dp)
                            )
                    )
                    Column (
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Albert Albar",
                            fontSize = 14.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF101828)
                        )
                        Text(
                            text = "Sistem Informasi",
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF667085)
                        )
                        Text(
                            text = "Business Plan",
                            fontSize = 12.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF1E22FB)
                        )
                    }
                    //Bersedia / Tidak bersedia
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
                if (text.text.isEmpty()) {
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

@Composable
fun ProdiAlertDialogue(prodiOptions: List<String>, showProdi: Boolean, setShowProdi: (Boolean) -> Unit) {
    if (showProdi) {
        AlertDialog(
            onDismissRequest = { setShowProdi(false) },
            title = { Text(text = "Pilih Program Studi") },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { setShowProdi(false) }) {
                        Text("Tutup")
                    }
                }
            },
            text = {
                Column {
                    prodiOptions.forEach { prodiOption ->
                        Text(
                            text = prodiOption,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun BidangAlertDialogue(bidangOptions: List<String>, showBidang: Boolean, setShowBidang: (Boolean) -> Unit) {
    if (showBidang) {
        AlertDialog(
            onDismissRequest = { setShowBidang(false) },
            title = { Text(text = "Pilih Bidang") },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { setShowBidang(false) }) {
                        Text("Tutup")
                    }
                }
            },
            text = {
                Column {
                    bidangOptions.forEach { bidangOption ->
                        Text(
                            text = bidangOption,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun BersediaAlertDialogue(bersediaOptions: List<String>, showBersedia: Boolean, setShowBersedia: (Boolean) -> Unit) {
    if (showBersedia) {
        AlertDialog(
            onDismissRequest = { setShowBersedia(false) },
            title = { Text(text = "Pilih Status") },
            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { setShowBersedia(false) }) {
                        Text("Tutup")
                    }
                }
            },
            text = {
                Column {
                    bersediaOptions.forEach { bersediaOption ->
                        Text(
                            text = bersediaOption,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        )
    }
}
