package ir.atefehtaheri.weatherforecasts.feature.CurrentLocation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun CurrentLocationScreen() {
    val ctx =LocalContext.current
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
//                get location
                Toast.makeText(ctx, "11111", Toast.LENGTH_SHORT).show()
                Log.d("tag","1111111")
            } else {
                Toast.makeText(ctx, "22222", Toast.LENGTH_SHORT).show()
                Log.d("tag","222222")

            }
        })
    if (isLocationPermissonGranted(ctx)) {
        Log.d("tag","3333333")
        Toast.makeText(ctx, "333333", Toast.LENGTH_SHORT).show()

//            getlocation
    } else {
        LaunchedEffect(true){locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)}
    }

}
fun isLocationPermissonGranted(context: Context): Boolean {

    return (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
                    == PackageManager.PERMISSION_GRANTED)

}