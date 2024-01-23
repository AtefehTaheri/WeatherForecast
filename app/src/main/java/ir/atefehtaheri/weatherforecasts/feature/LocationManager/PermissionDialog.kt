package ir.atefehtaheri.weatherforecasts.feature.LocationManager

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PermissionDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onGoToAppSettingsClick: (Context) -> Unit,
) {
    val ctx = LocalContext.current

    AlertDialog(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        onDismissRequest = onDismiss,
        title = {
            Text(text = "Permission required")
        },
        text ={
            Text(
                text ="This app needs access to your location. "+
                        "You can go to the app settings to grant it."
            )},
        confirmButton = {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Divider()
                Text(
                    text ="Setting",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onGoToAppSettingsClick(ctx)
                        }
                        .padding(16.dp)
                )
            }
        }

    )

}