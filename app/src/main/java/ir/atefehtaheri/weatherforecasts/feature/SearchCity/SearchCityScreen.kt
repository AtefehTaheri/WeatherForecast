package ir.atefehtaheri.weatherforecasts.feature.SearchCity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ir.atefehtaheri.weatherforecasts.R
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.LocationManagerViewModel
import ir.atefehtaheri.weatherforecasts.feature.LocationManager.PermissionDialog
import ir.atefehtaheri.weatherforecasts.navigation.Screen
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.Blue
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC1
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.GradientC2
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.LoadingBackground
import ir.atefehtaheri.weatherforecasts.presentation.ui.theme.Yellow


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchCityScreen(
    navController: NavController,
    LocationManagerViewModel: LocationManagerViewModel = hiltViewModel()
) {
    var isFirstRun by remember { mutableStateOf(true) }
    val ctx = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
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

    val gradient = Brush.verticalGradient(
        colorStops = arrayOf(
            0.5f to GradientC1,
            1f to GradientC2
        )
    )
    val softwareKeyboardController = LocalSoftwareKeyboardController.current
    val SearchbarText = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable{
                softwareKeyboardController?.hide()

            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = SearchbarText.value,
                onValueChange = { SearchbarText.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 15.dp),
                shape = RoundedCornerShape(25.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Yellow,
                    textColor = Blue,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_bold))
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        softwareKeyboardController?.hide()
                        LocationManagerViewModel.getsearch(SearchbarText.value)
                    }
                ), singleLine = true,

                trailingIcon = {
                    Icon(Icons.Filled.Search,
                        tint = LoadingBackground,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable {
                                softwareKeyboardController?.hide()
                                LocationManagerViewModel.getsearch(SearchbarText.value)
                            }
                            .padding(8.dp)
                    )
                }, placeholder = { Text("Enter city name") }
            )

            LocationManagerViewModel.SuggestedListState.value.error?.let { error ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .background(LoadingBackground),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = error,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                        )
                    )
                }

            }
            LocationManagerViewModel.location.value.error?.let { error ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .background(LoadingBackground),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = error,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
            LocationManagerViewModel.SuggestedListState.value.SuggestedItems?.let {
                if (it.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .background(LoadingBackground),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "The city was not found.",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                } else {
                    LazyColumn {
                        items(it) {
                            SuggestListItemScreen(navController, it)
                        }
                    }
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            containerColor = Yellow,
            shape = RoundedCornerShape(20.dp),
            contentColor = Blue,

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
            }


        ) {
            Text(
                text = "Current Location",
                modifier = Modifier.padding(5.dp),
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium))
                )
            )
        }

        AnimatedVisibility(
            visible = LocationManagerViewModel.SuggestedListState.value.isLoading
                    || !LocationManagerViewModel.location.value.isReady,
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
            LocationManagerViewModel.location.value.latitude != null && !isFirstRun
        ) {
            navController.navigate(
                Screen.LocationManager.navigateToWeatherScreen(
                    LocationManagerViewModel.location.value.latitude!!,
                    LocationManagerViewModel.location.value.longitude!!
                )
            ) {
                launchSingleTop = true
                popUpTo(0) {
                    inclusive = true
                }
            }
        }else{
            isFirstRun=false
        }
    }
}
fun openAppSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", context.packageName, null)
    )
    context.startActivity(intent)
}
