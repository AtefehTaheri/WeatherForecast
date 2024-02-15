package ir.atefehtaheri.weatherforecasts.feature.LocationManager

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.navigation.Screen
import ir.atefehtaheri.weatherforecasts.navigation.Screen.LocationManager.navigateToWeatherScreen
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.Blue
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC2
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.Yellow


@Composable
fun LocationManagerScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    LocationManagerViewModel: LocationManagerViewModel
) {
    var showDialog by remember { mutableStateOf(false) }

    val ctx = LocalContext.current

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val GpsLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult()
        ) {}

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { isGranted ->
            val allGranted = isGranted.values.reduce { permission1, permission2 ->
                permission1 && permission2
            }

            if (allGranted) {
                LocationManagerViewModel.getCurrentLocation(ctx)
            } else {
                showDialog = true
            }
        })

    val fontpoppins = FontFamily(
        Font(R.font.poppins_bold, FontWeight.Bold),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_light, FontWeight.Light),
    )
    val gradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.5f to GradientC1,
            1f to GradientC2
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                painter = painterResource(id = R.drawable.weather_icon),
                contentDescription = "",
                Modifier
                    .size(400.dp)
                    .padding(30.dp), contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        ParagraphStyle(lineHeight = 77.12.sp)
                    ) {
                        withStyle(
                            SpanStyle(
                                fontSize = 64.sp,
                                fontFamily = fontpoppins,
                                fontWeight = FontWeight.Black,
                                color = Color.White,
                            )
                        ) {
                            append("Weather \n")
                        }
                        withStyle(
                            SpanStyle(
                                fontSize = 64.sp,
                                fontFamily = fontpoppins,
                                fontWeight = FontWeight.Medium,
                                color = Yellow,
                            )
                        ) {
                            append("ForeCasts")
                        }
                    }

                }, textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))

            ElevatedButton(
                onClick = {

                    if (LocationManagerViewModel.PermissionUseCase.checkPermission(ctx) &&
                        LocationManagerViewModel.PermissionUseCase.checkGps(ctx)
                    ) {
                        LocationManagerViewModel.getCurrentLocation(ctx)

                    } else if (!LocationManagerViewModel.PermissionUseCase.checkGps(ctx)) {
                        LocationManagerViewModel.PermissionUseCase.enableGps(ctx, GpsLauncher)
                    } else {
                        locationPermissionLauncher.launch(locationPermissions)
                    }
                },
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Blue

                )
            ) {
                Text(
                    text = "Current Location",
                    style = TextStyle(
                        fontSize = 22.sp,
                        lineHeight = 29.71.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                )
            }
            TextButton(
                onClick = {
                    navController.navigate(Screen.SearchCity.route){
                        launchSingleTop = true
                    }
                }
            ) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Manual selection",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 29.71.sp,
                        fontFamily = fontpoppins,
                        fontWeight = FontWeight.Light,
                        color = Color.Black
                    )
                )
            }
        }

        AnimatedVisibility(
            visible = !LocationManagerViewModel.location.value.isReady,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
    if (showDialog) {
        PermissionDialog(
            onDismiss = { showDialog = false },
            onGoToAppSettingsClick = ::openAppSettings
        )

    }
    LaunchedEffect(
        key1 = LocationManagerViewModel.location.value.latitude
    ) {
        if (
            LocationManagerViewModel.location.value.latitude != null
        ) {
            navController.navigate(
                navigateToWeatherScreen(
                    LocationManagerViewModel.location.value.latitude!!,
                    LocationManagerViewModel.location.value.longitude!!
                )
            ) {
                launchSingleTop = true
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }
}

private fun openAppSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    )
    context.startActivity(intent)
}
