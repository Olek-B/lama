package com.example.sprawdzian1

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sprawdzian1.ui.theme.Sprawdzian1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sprawdzian1Theme {
                    PreviewWeatherScreen()
            }
        }
    }
}

@Composable
fun WeatherScreen(weatherData: List<WeatherForecast>) {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF6200EA)) // Purple background
    ) {
        // Header Section
        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id =R.drawable.ic_cloud ),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "5.0°C",
                style = TextStyle(fontSize = 48.sp, color = Color.White),
            )
            Text(
                text = "Warszawa",
                style = TextStyle(fontSize = 24.sp, color = Color.White),
            )
        }

        // Forecast List
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))// Purple background
                .background(Color(0xFF3700B3))
        ) {

            // Date Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF3700B3))
                    ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Dzisiaj",
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
                Text(
                    text = "1.12.2024",
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
            }
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 32.dp))

            // Forecast List
            LazyRow(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(Color(0xFF3700B3)) // Background for the scrollable part
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                items(weatherData) { forecast ->
                    WeatherItem(forecast)
                }
            }


        }

        // Button
        Button(
            onClick = {
                Toast.makeText(context, "Pogoda będzie piękna!", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA000)) // Orange button
        ) {
            Text("Sprawdź pozostałe dni", color = Color.White)
        }
    }
}

@Composable
fun WeatherItem(forecast: WeatherForecast) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .size(78.dp)
    ) {
        Text(text = "${forecast.temperature}°C", color = Color.White)
        Icon(
            painter = painterResource(id = forecast.iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(32.dp)
        )
        Text(text = forecast.time, color = Color.White, fontSize = 12.sp)
    }
}

data class WeatherForecast(val time: String, val temperature: Double, val iconRes: Int)

@Preview
@Composable
fun PreviewWeatherScreen() {
    val sampleData = listOf(
        WeatherForecast("10:00", 4.5, R.drawable.ic_cloud),
        WeatherForecast("12:00", 4.7, R.drawable.ic_cloud),
        WeatherForecast("14:00", 5.0, R.drawable.ic_sunny),
        WeatherForecast("16:00", 5.0, R.drawable.ic_sunny),
        WeatherForecast("18:00", 4.7, R.drawable.ic_cloud)
    )
    WeatherScreen(sampleData)
}

