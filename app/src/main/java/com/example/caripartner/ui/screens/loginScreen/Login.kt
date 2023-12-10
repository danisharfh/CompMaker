package com.example.caripartner.ui.screens.loginScreen

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestinationDsl
import androidx.navigation.NavHost
import coil.compose.rememberImagePainter
import com.example.caripartner.R
import com.example.caripartner.ui.screens.randomOffset
import com.example.caripartner.ui.theme.CariPartnerTheme
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream


@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        CompMakerLogo()
        BlueDotsBackground()
        NavigateToLoginButton(onNavigateToLogin)
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

@Composable
fun NavigateToLoginButton(onNavigateToLogin: () -> Unit) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { onNavigateToLogin.invoke() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(color = Color.White, shape = RoundedCornerShape(8.dp)),
        ) {

            Text(text = "Get Started")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToSignUpPage:() -> Unit,
){
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.loginError!= null
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
    ){
        if(isError){
            Text(
                text = loginUiState?.loginError ?: "Unknown error",
                color = Color.Red,
            )
        }

        Text(
            text = "Selamat Datang",
            fontSize = 18.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828),
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            textAlign = TextAlign.Right,
        )

        Text(
            text = "Masuk untuk menjelajah lebih jauh",
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 16.dp, bottom = 38.dp),
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF667085),
            textAlign = TextAlign.Right,
        )


        Image(
            painter = painterResource(id = R.drawable.pana),
            modifier = Modifier
                .fillMaxWidth()
                .width(153.66031.dp)
                .height(150.dp),
            contentDescription = ""
        )

        Text(
            text = "Email",
            modifier = Modifier.padding(start=16.dp, top=30.dp),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            value = loginUiState?.email ?: "",
            onValueChange = {loginViewModel?.onEmailChange(it)},
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null,
//                )
//            },
            label = {
                Text(
                    text = "Masukkan Email",
                    fontSize = 14.sp
                )
            },
            isError = isError
        )

        var showPassword by remember { mutableStateOf(value = false) }

        Text(
            text = "Password",
            modifier = Modifier.padding(start=16.dp, top=16.dp),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            value = loginUiState?.password ?: "",
            onValueChange = {loginViewModel?.onPasswordChange(it)},
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null,
//                )
//            },
            label = {
                Text(
                    text = "Masukkan Password",
                    fontSize = 14.sp
                )
            },
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
            isError = isError
        )

        Button(onClick = { loginViewModel?.loginUser(context) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 40.dp, bottom = 25.dp, end = 16.dp)
                .background(color = Color(0xFF4B4EFC), shape = RoundedCornerShape(size = 8.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B4EFC))
        ) {
            Text(text = "Login",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),)
        }

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            Text(text = "Belum memiliki akun? ",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF101828),
                modifier = Modifier.padding(top=15.dp))
            TextButton(onClick = { onNavToSignUpPage.invoke() }) {
                Text(text = "Daftar")
            }
        }

        if(loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser){
            if(loginViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToHomePage:() -> Unit,
    onNavToLoginPage:() -> Unit,
){
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError!= null
    val context = LocalContext.current
    val isUploading = remember{ mutableStateOf(false) }
    val img:Bitmap = BitmapFactory.decodeResource(Resources.getSystem(),android.R.drawable.ic_menu_report_image)
    val bitmap = remember { mutableStateOf(img)}

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) {
        if(it !=null){
            bitmap.value = it
        }
    }

//    val launchImage = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()){
//        imageUri = it
//        if(Build.VERSION.SDK_INT<34){
//           bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver,it)
//        }else{
//            val source = it?.let{it1 ->
//                ImageDecoder.createSource(context.contentResolver,it)
//            }
//            bitmap.value = source?.let {it1->
//                ImageDecoder.decodeBitmap(it1)
//            }!!
//        }
//    }
    val launchImage = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            imageUri = it
            if (Build.VERSION.SDK_INT < 34) {
                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            } else {
                val source = ImageDecoder.createSource(context.contentResolver, it)
                bitmap.value = ImageDecoder.decodeBitmap(source)
            }
            loginViewModel?.onKtmChange(bitmap.value, imageUri!!, img.toString())
        }
    }



    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start,
    ){
        if(isError){
            Text(
                text = loginUiState?.signUpError ?: "Unknown error",
                color = Color.Red,
            )
        }

        Text(
            text = "Halo!",
            fontSize = 18.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828),
            modifier = Modifier.padding(vertical = 12.dp),
            textAlign = TextAlign.Right,
        )

        Text(
            text = "Buat akun untuk menjelajah lebih jauh",
            fontSize = 12.sp,
            modifier = Modifier.padding( bottom = 38.dp),
            lineHeight = 18.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF667085),
            textAlign = TextAlign.Right,
        )

        Text(
            text = "Nama",
            modifier = Modifier.padding( top=20.dp),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = loginUiState?.userNameSignUp ?: "",
            onValueChange = {loginViewModel?.onUserNameChangeSignUp(it)},
            label = {
                Text(
                    text = "Masukkan Nama",
                    fontSize = 14.sp
                )
            },
            isError = isError
        )

        Text(
            text = "Email",
            modifier = Modifier.padding( top=16.dp),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = loginUiState?.emailSignUp ?: "",
            onValueChange = {loginViewModel?.onEmailSignUpChange(it)},
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = null,
//                )
//            },
            label = {
                Text(
                    text = "Masukkan Email",
                    fontSize = 14.sp
                )
            },
            isError = isError
        )

//        var password by remember { mutableStateOf(value = "") }
        var showPasswordFirst by remember { mutableStateOf(value = false) }
        var showPasswordSecond by remember { mutableStateOf(value = false) }

        Text(
            text = "Password",
            modifier = Modifier.padding( top=16.dp),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = loginUiState?.passwordSignUp ?: "",
            onValueChange = {loginViewModel?.onPasswordChangeSignUp(it)},
            label = {
                Text(
                    text = "Masukkan Password",
                    fontSize = 14.sp
                )
            },
//            visualTransformation = PasswordVisualTransformation(),
            isError = isError,
            visualTransformation = if (showPasswordFirst) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (showPasswordFirst) {
                    IconButton(onClick = { showPasswordFirst = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPasswordFirst = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
        )

        Text(
            text = "Konfirmasi Password",
            modifier = Modifier.padding(top=16.dp),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828)
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = loginUiState?.confirmPasswordSignUp ?: "",
            onValueChange = {loginViewModel?.onConfirmPasswordChange(it)},
            label = {
                Text(
                    text = "Masukkan Password",
                    fontSize = 14.sp
                )
            },
            visualTransformation = if (showPasswordSecond) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                if (showPasswordSecond) {
                    IconButton(onClick = { showPasswordSecond = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPasswordSecond = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            },
            isError = isError
        )

        Text(
            text = "Unggah Foto",
            modifier = Modifier.padding( top=16.dp,bottom=12.dp),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            fontWeight = FontWeight(600),
            color = Color(0xFF101828)
        )

        Column (modifier = Modifier.border(1.dp, Color.Gray, shape = RectangleShape)) {
            imageUri?.let { uri ->
                Image(

                    painter = rememberImagePainter(uri),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .background(MaterialTheme.colorScheme.background)
                        .align(Alignment.CenterHorizontally)
                )
            }
            TextButton(onClick = { launchImage.launch("image/*") },
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
                    .background(color = Color.White, shape = RectangleShape)) {

                Column (modifier=Modifier.background(color= Color.White, shape = RectangleShape)){

                    Icon(imageVector =Icons.Default.AddCircle,modifier = Modifier.fillMaxWidth(), contentDescription ="Tambah Foto",
                    )
                    Text(text = "Upload Gambar .jpeg",modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
            }
        }


//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier.fillMaxWidth()
//        ){
//            Button(onClick = {
//                isUploading.value = true
//                bitmap.value.let {bitmap ->
//                    uploadImageToFirebase(bitmap,context as ComponentActivity){success->
//                        isUploading.value = false
//                        if (success){
//                            Toast.makeText(context,"Uploaded Successfully",Toast.LENGTH_SHORT).show()
//                        }
//                        else{
//                            Toast.makeText(context,"Failed to Upload",Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            },
//                colors = ButtonDefaults.buttonColors()
//            ) {
//                Text(text = "Upload KTM")
//            }
//        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (isUploading.value){
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            }

        }

        Button(onClick = {
//            loginViewModel?.createUser(context)
            loginViewModel?.createUser(context as ComponentActivity)
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 40.dp, bottom = 25.dp, end = 16.dp)
                .background(color = Color(0xFF4B4EFC), shape = RoundedCornerShape(size = 8.dp)),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B4EFC))
        ) {
            Text(text = "Buat Akun",
                fontSize = 18.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFFFFFFFF),)
        }

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ){
            Text(text = "Sudah memiliki akun? ",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF101828),
                modifier = Modifier.padding(top=15.dp))
            TextButton(onClick = { onNavToLoginPage.invoke()}) {
                Text(text = "Login")
            }
        }

        if(loginUiState?.isLoading == true){
            CircularProgressIndicator()
        }

        LaunchedEffect(key1 = loginViewModel?.hasUser){
            if(loginViewModel?.hasUser == true){
                onNavToHomePage.invoke()
            }
        }
    }
}

fun uploadImageToFirebase(bitmap: Bitmap, context: Context, callback: (Boolean)->Unit) {
    val storageRef = Firebase.storage.reference
    val imageRef = storageRef.child("ktm/${bitmap}")
    val baos = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
    val imageData = baos.toByteArray()
    imageRef.putBytes(imageData).addOnSuccessListener {
        callback(true)
    }.addOnFailureListener{
        callback(false)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    CariPartnerTheme {
        SplashScreen {}
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen(){
    CariPartnerTheme {
        LoginScreen(onNavToHomePage = { /*TODO*/ }) {

        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PrevSignUpScreen(){
    CariPartnerTheme {
        SignUpScreen(onNavToHomePage = { /*TODO*/ }) {

        }
    }
}