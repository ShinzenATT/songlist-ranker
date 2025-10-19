package se.chalmers.hd

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var colorScheme: ColorScheme? = null
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                colorScheme = if (isSystemInDarkTheme()){
                    dynamicDarkColorScheme(applicationContext)
                } else {
                    dynamicLightColorScheme(applicationContext)
                }
            }

            App(colorScheme)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}