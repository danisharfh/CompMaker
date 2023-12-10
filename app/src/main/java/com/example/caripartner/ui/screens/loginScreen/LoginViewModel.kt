package com.example.caripartner.ui.screens.loginScreen

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caripartner.data.repository.AuthRepository
import com.example.caripartner.data.repository.StorageRepository
import com.example.caripartner.data.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val AuthRepository: AuthRepository = AuthRepository(),
    private val UserRepository: UserRepository = UserRepository(),
    private val StorageRepository: StorageRepository = StorageRepository()
):ViewModel() {

    val currentUser = AuthRepository.currentUser

    val userId:String
        get() = AuthRepository.getUserId()

    val hasUser:Boolean
        get() = AuthRepository.isLogin()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(email:String){
        loginUiState = loginUiState.copy(email = email)
    }

    fun onEmailSignUpChange(email:String){
        loginUiState = loginUiState.copy(emailSignUp = email)
    }

    fun onKtmChange(bitmap:Bitmap, imageUri:Uri, img: String){
        loginUiState = loginUiState.copy(bitmap = bitmap)
        loginUiState = loginUiState.copy(img = img)
        loginUiState = loginUiState.copy(imageUri = imageUri)
    }

    fun onPasswordChange(password:String){
        loginUiState = loginUiState.copy(password = password)
    }

    fun onUserNameChangeSignUp(userName:String){
        loginUiState = loginUiState.copy(userNameSignUp = userName)
    }

    fun onPasswordChangeSignUp(password:String){
        loginUiState = loginUiState.copy(passwordSignUp = password)
    }

    fun onConfirmPasswordChange(password:String){
        loginUiState = loginUiState.copy(confirmPasswordSignUp = password)
    }

    private fun validateLoginForm()=
        loginUiState.email.isNotBlank() &&
                loginUiState.password.isNotBlank()

    private fun validateemail()=
        loginUiState.email.endsWith("@student.ub.ac.id")

    private fun validateSignUpForm() =
        loginUiState.userNameSignUp.isNotBlank() &&
                loginUiState.passwordSignUp.isNotBlank() &&
                loginUiState.confirmPasswordSignUp.isNotBlank()

    private fun showErrorDialog(context: Context, errorMessage: String?) {
        AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(errorMessage ?: "An error occurred")
            .setPositiveButton("OK", null)
            .show()
    }
    fun  createUser(context: Context) = viewModelScope.launch {
        try {
            if(!loginUiState.userNameSignUp.isNotBlank()){
                showErrorDialog(context, "Nama harus diisi")
                return@launch
            }
            if (!validateSignUpForm()) {
                showErrorDialog(context, "Email dan Password harus diisi")
                return@launch
            }
            if (loginUiState.passwordSignUp != loginUiState.confirmPasswordSignUp) {
                showErrorDialog(context, "Konfirmasi password tidak sama")
                return@launch
            }
            if (loginUiState.imageUri == null) {
                showErrorDialog(context, "Upload foto anda terlebih dahulu")
                return@launch
            }
            loginUiState = loginUiState.copy(isLoading = true)

            loginUiState = loginUiState.copy(signUpError = null)


            AuthRepository.createUser(
                loginUiState.emailSignUp,
                loginUiState.passwordSignUp
            ){ isSucessful ->
                if(isSucessful){
                    UserRepository.CreateUser(loginUiState.emailSignUp,loginUiState.userNameSignUp,false,loginUiState.img,loginUiState.passwordSignUp,AuthRepository.getUserId()) { success ->
                        if(success){
                            Toast.makeText(
                                context,
                                "Success Create User",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            Toast.makeText(
                                context,
                                "Failed Create User",
                                Toast.LENGTH_SHORT
                            ).show()
                            loginUiState = loginUiState.copy(isSuccessLogin = false)
                        }
                    }
                    StorageRepository.uploadImageToFirebase(loginUiState.bitmap!!,context as ComponentActivity) { success ->
                        if(success){
                            Toast.makeText(
                                context,
                                "Success Upload KTM",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    }
                    Toast.makeText(
                        context,
                        "Success Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Failed Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        }catch(e:Exception){
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }


    fun  loginUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateemail()){
                showErrorDialog(context, "Gunakan email UB")
                return@launch
            }
            if(!validateLoginForm()){
                showErrorDialog(context, "Kredensial tidak sesuai")
                return@launch
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null)
            AuthRepository.login(
                loginUiState.email,
                loginUiState.password
            ){ isSucessful ->
                if(isSucessful){
                    Toast.makeText(
                        context,
                        "Success Login",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else{
                    showErrorDialog(context, "Failed Login")
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        }catch(e:Exception){
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }
}

data class LoginUiState(
    val email:String = "",
    val password:String = "",
    val emailSignUp:String = "",
    val userNameSignUp:String = "",
    val passwordSignUp:String = "",
    val confirmPasswordSignUp:String = "",
    val isLoading:Boolean = false,
    val isSuccessLogin:Boolean = false,
    val signUpError:String? = null,
    val loginError:String? = null,
    val bitmap: Bitmap? =null,
    val img: String ="",
    var imageUri: Uri? = null
)