package com.example.phisica

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.phisica.calculations.Case1Calculations
import com.example.phisica.defaultData.DefaultData
import com.example.phisica.functionalHelp.countAverage
import com.example.phisica.screens.firstScreen
import com.example.phisica.screens.secondScreen
import com.example.phisica.screens.thirdScreen
import com.example.phisica.ui.theme.PhisicaTheme
import java.lang.Exception

class MainActivity : ComponentActivity() {
    val activity = this

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //mode 0 - ничего не выбрали ещё
        //mode 1 - ластилиновые шары, абсолютно неупругий удар
        //mode 2 - стальные шары разной массы
        //mode 3 - стальные шары, одинаковой массы
        //undermode 0 - таблица
        //undermode 1 - ручной рассчет
        setContent {
            var mode by remember {
                mutableStateOf(0)
            }
            BackHandler() {
                if (mode != 0) {
                    mode = 0
                }
            }

            PhisicaTheme {
                when (mode) {
                    0 -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Задание 1",
                                modifier = Modifier
                                    .clickable { mode = 1 },
                                fontSize = 50.sp
                            )
                            Text(
                                text = "Задание 2",
                                modifier = Modifier
                                    .clickable { mode = 2 },
                                fontSize = 50.sp
                            )
                            Text(
                                text = "Задание 3",
                                modifier = Modifier
                                    .clickable { mode = 3 },
                                fontSize = 50.sp
                            )
                        }
                    }

                    1 -> {
                        firstScreen(activity = activity)
                    }

                    2 -> {
                        secondScreen(activity = activity)
                    }

                    3 -> {
                        thirdScreen(activity = activity)
                    }
                }
            }
        }
    }
}


//
//fun part2() {
//    // Исходные данные
//    val m1: Double = 0.169
//    val m2: Double = 0.105
//    val alpha: Double = 10.0
//    val length: Double = 0.422
//    val g: Double = 9.8
//    val beta1: Double = 4.75
//    val beta2: Double = 7.00
//
//    // Расчёт скорости v1 по формуле: v1 = sqrt(2 * g * l * (1 - cos(alpha)))
//    val v1 = sqrt(2 * g * length * (1 - cos(alpha)))
//
//    // Скорости после столкновения
//    val u1 = v1 * (m1 - m2) / (m1 + m2)  // скорость первого шара после удара
//    val u2 = 2 * m1 * v1 / (m1 + m2)    // скорость второго шара после удара
//
//    // Импульс до удара p = m1 * v1
//    val pBefore = m1 * v1
//    val pAfter = m1 * u1 + m2 * u2
//
//    // Коэффициент
//    val k0 = (Sin(beta2 / 2) - Sin(beta1 / 2)) / Sin(alpha / 2)
//
//    // Вывод результатов
//    println("Результаты расчёта:")
//    println("──────────────────────────────────────────")
//    println("Импульс до удара p (кг·м/с): ${"%.3f".format(pBefore)}")
//    println("Импульс после удара p' (кг·м/с): ${"%.3f".format(pAfter)}")
//    println("Коэффициент восстановления скорости k_v: ${"%.3f".format(k0)}")
//    println("──────────────────────────────────────────")
//}
//
//fun part3() {
//    val radius = 0.0175
//    val mass = 0.169
//
//    val alpha = 9.5
//    val beta = 9.00
//    val g = 9.8
//    var t: Double = 101.00 / 1000000.00
//
//
//    // Вычисление импульса до удара (p)
//    val p = mass * sqrt(2 * g * radius * (1 - Cos(alpha)))
//
//    // Вычисление импульса после удара (p')
//    val pPrime = mass * sqrt(2 * g * radius * (1 - Cos(beta)))
//
//    // Коэффициент
//    val k0 = Sin(beta / 2) / Sin(alpha / 2)
//
//    // Сила удара F
//    val F = (p - pPrime) / t
//
//    // Скорость удара c
//    val c = sqrt(2 * g * radius * (1 - Cos(alpha)))
//
//    // Вывод результатов
//    println("\nРезультаты расчёта:")
//    println("────────────────────────────────────────────")
//    println("Угол α (град): $alpha")
//    println("Угол β (град): $beta")
//    println("Импульс до удара p (кг·м/с): ${"%.3f".format(p)}")
//    println("Импульс после удара p' (кг·м/с): ${"%.3f".format(pPrime)}")
//    println("Коэффициент восстановления скорости k_v: ${"%.3f".format(k0)}")
//    println("Сила удара F (Н): ${"%.2f".format(F)}")
//    println("Скорость удара c (м/с): ${"%.3f".format(c)}")
//    println("────────────────────────────────────────────")
//}

@Composable
fun DrawTable(info: ArrayList<Map<String, Double>>, hasAverage: Boolean) {
    if (info.isNotEmpty()) {
        var header = info[0].keys
        info.forEachIndexed { index, item ->
            Row() {
                header.forEach { key ->
                    Text(
                        text = "%.4f".format(item[key]),
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                if (index ==
                                    info.size - 1 && hasAverage
                                ) Color.LightGray else Color.Transparent
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun DrawTable2(info: ArrayList<Map<String, Double>>, hasAverage: Boolean) {
    if (info.isNotEmpty()) {
        var header = info[0].keys
        info.forEachIndexed { index, item ->
            Row() {
                header.forEach { key ->
                    Text(
                        text = "%.4f".format(item[key]),
                        modifier = Modifier
                            .width(80.dp)
                            .background(
                                if (index ==
                                    info.size - 1 && hasAverage
                                ) Color.LightGray else Color.Transparent
                            )
                    )
                }
            }
        }
    }
}