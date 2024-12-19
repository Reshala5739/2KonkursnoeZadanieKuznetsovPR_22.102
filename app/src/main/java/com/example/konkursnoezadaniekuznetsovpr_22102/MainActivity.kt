package com.example.konkursnoezadaniekuznetsovpr_22102

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.konkursnoezadaniekuznetsovpr_22102.ui.theme.KonkursnoeZadanieKuznetsovPR_22102Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KonkursnoeZadanieKuznetsovPR_22102Theme {
                AppNavigation()
            }
        }
    }
}
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "intro_screen") {
        composable("intro_screen") {
            IntroScreen(navController)
        }
        composable("login_screen") {
            LoginScreen(navController)
        }
        composable("register_screen") {
            RegisterScreen()
        }
        composable("start_screen") {
            StartScreen(navController)
        }
        composable("start2_screen") {
            Start2Screen(navController)
        }
        composable("welcome_screen") {
            WelcomeScreen(navController)
        }
    }
}
@Composable
fun IntroScreen(navController: NavController) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    rememberCoroutineScope()

    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        when (page) {
            0 -> WelcomeScreen(navController)
            1 -> StartScreen(navController)
        }
    }
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == 1) {
            navController.navigate("start_screen")
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(2) { index ->
            val isSelected = pagerState.currentPage == index
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (isSelected) 12.dp else 8.dp)
                    .background(
                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f),
                        shape = MaterialTheme.shapes.small
                    )
            )
        }
    }
}
@Composable
fun WelcomeScreen(navController: NavController) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var alpha by remember { mutableFloatStateOf(1f) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount < 0) { // Swipe left, move forward
                        offsetX = (offsetX + dragAmount).coerceIn(-300f, 0f)
                        alpha = 1 - (-offsetX / 300f).coerceIn(0f, 1f)
                    }
                }
            }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF2196F3)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.img_1),
                    contentDescription = "Image 1",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(x = 20.dp, y = 50.dp),
                    colorFilter = ColorFilter.tint(Color(0x99FFFFFF))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = "Image 2",
                    modifier = Modifier
                        .size(120.dp)
                        .offset(x = 200.dp, y = 150.dp),
                    colorFilter = ColorFilter.tint(Color(0xCCFFFFFF))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_3),
                    contentDescription = "Image 3",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(x = 20.dp, y = 450.dp),
                    colorFilter = ColorFilter.tint(Color(0xB3FFFFFF))
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 60.dp)
                    ) {
                        Text(
                            text = "ДОБРО\nПОЖАЛОВАТЬ",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 34.sp,
                                lineHeight = 40.sp,
                                textAlign = TextAlign.Center,
                                letterSpacing = 4.sp,
                                fontFamily = FontFamily.Cursive
                            ),
                            color = Color.White
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "Кроссовок",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .align(Alignment.End)
                            .offset(x = 50.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .height(4.dp)
                                .width(24.dp)
                                .background(
                                    color = Color.Black,
                                    shape = RoundedCornerShape(2.dp)
                                )
                        )
                        repeat(2) { index ->
                            val isSelected = index == 0
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .height(4.dp)
                                    .width(16.dp)
                                    .background(
                                        color = if (isSelected) Color.White else Color.White.copy(alpha = 0.4f),
                                        shape = RoundedCornerShape(2.dp)
                                    )
                            )
                        }
                    }
                    Button(
                        onClick = { navController.navigate("start_screen") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 32.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Начать",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    translationX = offsetX,
                    alpha = alpha
                )
        )
        LaunchedEffect(offsetX) {
            if (offsetX <= -300f) {
                navController.navigate("start_screen") {
                    popUpTo("welcome_screen") { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
    }
}
@Composable
fun StartScreen(navController: NavController) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var alpha by remember { mutableFloatStateOf(1f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount < 0) {
                        offsetX = (offsetX + dragAmount).coerceIn(-300f, 0f)
                        alpha = 1 - (-offsetX / 300f).coerceIn(0f, 1f)
                    }
                }
            }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF2196F3)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.img_1),
                    contentDescription = "Image 1",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(x = 20.dp, y = 50.dp),
                    colorFilter = ColorFilter.tint(Color(0x99FFFFFF))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = "Image 2",
                    modifier = Modifier
                        .size(120.dp)
                        .offset(x = 200.dp, y = 150.dp),
                    colorFilter = ColorFilter.tint(Color(0xCCFFFFFF))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_3),
                    contentDescription = "Image 3",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(x = 20.dp, y = 450.dp),
                    colorFilter = ColorFilter.tint(Color(0xB3FFFFFF))
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_4),
                            contentDescription = "Second sneaker",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1250.dp)
                                .align(Alignment.Center)
                                .offset(y = 100.dp)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(top = 120.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "НАЧНЕМ\nПУТЕШЕСТВИЕ",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                lineHeight = 40.sp,
                                textAlign = TextAlign.Center,
                                letterSpacing = 4.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color.White
                            )
                        )
                    }
                    Text(
                        text = "Умная, великолепная, модная\nколлекция. Изучите сейчас!",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(1) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .height(4.dp)
                                    .width(16.dp)
                                    .background(
                                        color = Color.White.copy(alpha = 0.4f),
                                        shape = RoundedCornerShape(2.dp)
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .height(4.dp)
                                .width(24.dp)
                                .background(
                                    color = Color.Black,
                                    shape = RoundedCornerShape(2.dp)
                                )
                        )
                        repeat(1) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .height(4.dp)
                                    .width(16.dp)
                                    .background(
                                        color = Color.White.copy(alpha = 0.4f),
                                        shape = RoundedCornerShape(2.dp)
                                    )
                            )
                        }
                    }
                    Button(
                        onClick = { navController.navigate("start2_screen") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 32.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Далее",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    translationX = offsetX,
                    alpha = alpha
                )
        )
        LaunchedEffect(offsetX) {
            if (offsetX <= -300f) {
                navController.navigate("start2_screen") {
                    popUpTo("start_screen") { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
    }
}
@Composable
fun Start2Screen(navController: NavController) {
    val offsetX by remember { mutableFloatStateOf(0f) }
    val alpha by remember { mutableFloatStateOf(1f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    if (dragAmount < 0) {
                        navController.navigate("login_screen") {
                            launchSingleTop = true
                            popUpTo("start2_screen") { inclusive = true }
                        }
                    }
                }
            }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF2196F3)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                // Background images
                Image(
                    painter = painterResource(id = R.drawable.img_1),
                    contentDescription = "Image 1",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(x = 20.dp, y = 50.dp),
                    colorFilter = ColorFilter.tint(Color(0x99FFFFFF))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_2),
                    contentDescription = "Image 2",
                    modifier = Modifier
                        .size(120.dp)
                        .offset(x = 200.dp, y = 150.dp),
                    colorFilter = ColorFilter.tint(Color(0xCCFFFFFF))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_3),
                    contentDescription = "Image 3",
                    modifier = Modifier
                        .size(80.dp)
                        .offset(x = 20.dp, y = 450.dp),
                    colorFilter = ColorFilter.tint(Color(0xB3FFFFFF))
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_5),
                            contentDescription = "Second sneaker",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1250.dp)
                                .align(Alignment.Center)
                                .offset(y = 100.dp)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(top = 120.dp)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "НАЧНЕМ\nПУТЕШЕСТВИЕ",
                            style = MaterialTheme.typography.headlineLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp,
                                lineHeight = 40.sp,
                                textAlign = TextAlign.Center,
                                letterSpacing = 4.sp,
                                fontFamily = FontFamily.Serif,
                                color = Color.White
                            )
                        )
                    }
                    Text(
                        text = "Умная, великолепная, модная\nколлекция. Изучите сейчас!",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Normal,
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(2) {
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .height(4.dp)
                                    .width(16.dp)
                                    .background(
                                        color = Color.White.copy(alpha = 0.4f),
                                        shape = RoundedCornerShape(2.dp)
                                    )
                            )
                        }
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .height(4.dp)
                                .width(24.dp)
                                .background(
                                    color = Color.Black,
                                    shape = RoundedCornerShape(2.dp)
                                )
                        )
                    }
                    Button(
                        onClick = { navController.navigate("login_screen") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 32.dp)
                            .height(50.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            text = "Далее",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer(
                    translationX = offsetX,
                    alpha = alpha
                )
        )
    }
}
@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val passwordVisibility = remember { mutableStateOf(false) }
    val emailError = remember { mutableStateOf(false) }
    val passwordError = remember { mutableStateOf(false) }
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Привет!",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Заполните Свои Данные Или\nПродолжите Через Социальные Медиа",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )
            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                    emailError.value = !isValidEmail(it)
                },
                label = { Text("Email") },
                placeholder = { Text("xyz@gmail.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = emailError.value
            )
            if (emailError.value) {
                Text(
                    text = "Некорректный email",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            OutlinedTextField(
                value = password.value,
                onValueChange = {
                    password.value = it
                    passwordError.value = it.isEmpty()
                },
                label = { Text("Пароль") },
                placeholder = { Text("*******") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                        Text(
                            if (passwordVisibility.value) "Скрыть" else "Показать",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                isError = passwordError.value
            )
            if (passwordError.value) {
                Text(
                    text = "Пароль не может быть пустым",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            TextButton(
                onClick = {  },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Восстановить", color = MaterialTheme.colorScheme.primary)
            }
            Button(
                onClick = {
                    if (email.value.isEmpty() || password.value.isEmpty() || emailError.value) {
                        Toast.makeText(
                            context,
                            "Пожалуйста, заполните все поля корректно",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Вход выполнен успешно!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Войти", fontSize = 16.sp)
            }
            TextButton(onClick = { navController.navigate("register_screen") }) {
                Text(
                    text = "Вы впервые? Создать пользователя",
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }
        }
    }
}
@Composable
fun RegisterScreen() {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Регистрация",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Заполните Свои Данные Или\nПродолжите Через Социальные Медиа",
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Ваше имя") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                placeholder = { Text("xyz@gmail.com") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Пароль") },
                placeholder = { Text("*******") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        Text(
                            if (passwordVisibility) "Скрыть" else "Показать",
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Checkbox(
                    checked = true,
                    onCheckedChange = { }
                )
                Text("Даю согласие на обработку персональных данных")
            }
            Button(
                onClick = {
                    Toast.makeText(context, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Зарегистрироваться", fontSize = 16.sp)
            }
        }
    }
}
fun isValidEmail(email: String): Boolean {
    val emailPattern = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$"
    return email.matches(Regex(emailPattern))
}
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    KonkursnoeZadanieKuznetsovPR_22102Theme {
        AppNavigation()
    }
}