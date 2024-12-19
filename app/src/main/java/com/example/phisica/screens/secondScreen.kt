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
import com.example.phisica.calculations.Case1Calculations
import com.example.phisica.calculations.Case2Calculations
import com.example.phisica.defaultData.DefaultData
import com.example.phisica.functionalHelp.countAverage
import java.lang.Exception

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun secondScreen(activity: Activity) {
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
                DefaultData.secondHeader.forEach { value ->
                    Text(
                        text = value, modifier = Modifier
                            .width(80.dp)
                            .background(Color.Green)
                    )
                }
            }
            DrawTable2(info = info, hasAverage)
        }


        var m1 by remember {
            mutableStateOf("")
        }
        var m2 by remember {
            mutableStateOf("")
        }
        var l by remember {
            mutableStateOf("")
        }
        var a by remember {
            mutableStateOf("")
        }
        var b1 by remember {
            mutableStateOf("")
        }
        var b2 by remember {
            mutableStateOf("")
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Введите m1 (кг)", fontSize = 25.sp)
        TextField(
            value = m1,
            onValueChange = { newValue -> m1 = newValue })
        Text(text = "Введите m2 (кг)", fontSize = 25.sp)
        TextField(
            value = m2,
            onValueChange = { newValue -> m2 = newValue })
        Text(text = "Введите l (length) (м)", fontSize = 25.sp)
        TextField(
            value = l,
            onValueChange = { newValue -> l = newValue })
        Text(text = "Введите a (градусы)", fontSize = 25.sp)
        TextField(
            value = a,
            onValueChange = { newValue -> a = newValue })
        Text(text = "Введите b1 (градусы)", fontSize = 25.sp)
        TextField(
            value = b1,
            onValueChange = { newValue -> b1 = newValue })
        Text(text = "Введите b2 (градусы)", fontSize = 25.sp)
        TextField(
            value = b2,
            onValueChange = { newValue -> b2 = newValue })
        Button(
            onClick = {
                try {
                    if (!hasAverage) {
                        info.add(
                            Case2Calculations(
                                m1.toDouble(),
                                m2.toDouble(),
                                l.toDouble(),
                                a.toDouble(),
                                b1.toDouble(),
                                b2.toDouble(),
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
                    m1 = ""
                    m2 = ""
                    l = ""
                    a = ""
                    b1 = ""
                    b2 = ""
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
                            Case2Calculations(
                                lstWithAverage["m1"] ?: 0.0,
                                lstWithAverage["m2"] ?: 0.0,
                                lstWithAverage["l"] ?: 0.0,
                                lstWithAverage["a"] ?: 0.0,
                                lstWithAverage["b1"] ?: 0.0,
                                lstWithAverage["b2"] ?: 0.0
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
                m1 = ""
                m2 = ""
                l = ""
                a = ""
                b1 = ""
                b2 = ""
            },
            modifier = Modifier.padding(top = 20.dp),
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text(text = "ОЧИСТИТЬ ВСЕ ДАННЫЕ")
        }
    }
}