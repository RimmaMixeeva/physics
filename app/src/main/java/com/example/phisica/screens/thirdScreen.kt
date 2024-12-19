package com.example.phisica.screens

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phisica.DrawTable
import com.example.phisica.DrawTable2
import com.example.phisica.calculations.Case2Calculations
import com.example.phisica.calculations.Case3Calculations
import com.example.phisica.defaultData.DefaultData
import com.example.phisica.functionalHelp.countAverage
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun thirdScreen(activity: Activity){
    var info by remember { mutableStateOf(arrayListOf<Map<String, Double>>()) }
    var hasAverage by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(
            rememberScrollState()
        )
    ) {
        Column(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            Row() {
                DefaultData.thirdHeader.forEach { value ->
                    Text(
                        text = value, modifier = Modifier
                            .width(80.dp)
                            .background(Color.Green)
                    )
                }
            }
            DrawTable2(info = info, hasAverage)
        }
        var m by remember {
            mutableStateOf("")
        }
        var r by remember {
            mutableStateOf("")
        }
        var a by remember {
            mutableStateOf("")
        }
        var b by remember {
            mutableStateOf("")
        }
        var t by remember {
            mutableStateOf("")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Введите R (м)", fontSize = 25.sp)
        TextField(
            value = r,
            onValueChange = { newValue -> r = newValue })
        Text(text = "Введите m (кг)", fontSize = 25.sp)
        TextField(
            value = m,
            onValueChange = { newValue -> m = newValue })
        Text(text = "Введите a (градусы)", fontSize = 25.sp)
        TextField(
            value = a,
            onValueChange = { newValue -> a = newValue })
        Text(text = "Введите b (градусы)", fontSize = 25.sp)
        TextField(
            value = b,
            onValueChange = { newValue -> b = newValue })
        Text(text = "Введите t (сек)", fontSize = 25.sp)
        TextField(
            value = t,
            onValueChange = { newValue -> t = newValue })
        Button(
            onClick = {
                try {
                    if (!hasAverage) {
                        info.add(
                            Case3Calculations(
                                m.toDouble(),
                                r.toDouble(),
                                a.toDouble(),
                                b.toDouble(),
                                t.toDouble(),
                            )
                        )
                    } else {
                        Toast.makeText(
                            activity,
                            "Очистите среднее",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                } finally {
                    r = ""
                    m = ""
                    a = ""
                    b = ""
                    t = ""
                }
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "СОХРАНИТЬ")
        }
        Button(
            onClick = {
                if (!hasAverage){
                    var lstWithAverage = countAverage(info)
                    info = ArrayList(info).apply {
                        add(
                            Case3Calculations(
                                lstWithAverage["m"] ?: 0.0,
                                lstWithAverage["r"] ?: 0.0,
                                lstWithAverage["a"] ?: 0.0,
                                lstWithAverage["b"] ?: 0.0,
                                lstWithAverage["t"] ?: 0.0,
                            )
                        )
                    }
                    hasAverage = true
                }
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text(text = "ПОСЧИТАТЬ СРЕДНЕЕ")
        }
        Button(
            onClick = {
                if (hasAverage) {
                    //deleting average row
                    info = arrayListOf(*info.dropLast(1).toTypedArray())
                    hasAverage = false
                }
            },
            modifier = Modifier.padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(Color.DarkGray)
        ) {
            Text(text = "ОЧИСТИТЬ СРЕДНЕЕ")
        }
        Button(
            onClick = {
                info = arrayListOf()
                r = ""
                m = ""
                a = ""
                b = ""
                t = ""
            },
            modifier = Modifier.padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(text = "ОЧИСТИТЬ ВСЕ ДАННЫЕ")
        }
    }
}