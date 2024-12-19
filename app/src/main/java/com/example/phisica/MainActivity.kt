package com.example.phisica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.phisica.calculations.Case1Calculations
import com.example.phisica.defaultData.DefaultData
import com.example.phisica.functionalHelp.findAverage
import com.example.phisica.ui.theme.PhisicaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var info = arrayListOf<Map<String, Double>>()
        info.add(Case1Calculations(
            DefaultData.firstData1[0],
            DefaultData.firstData1[1],
            DefaultData.firstData1[2],
            DefaultData.firstData1[3]
        ))
        info.add(Case1Calculations(
            DefaultData.firstData2[0],
            DefaultData.firstData2[1],
            DefaultData.firstData2[2],
            DefaultData.firstData2[3]
        ))
        info.add(Case1Calculations(
            DefaultData.firstData3[0],
            DefaultData.firstData3[1],
            DefaultData.firstData3[2],
            DefaultData.firstData3[3]
        ))
        info.add(Case1Calculations(
         findAverage(DefaultData.firstData1[0], DefaultData.firstData2[0], DefaultData.firstData3[0]),
         findAverage(DefaultData.firstData1[1], DefaultData.firstData2[1], DefaultData.firstData3[1]),
         findAverage(DefaultData.firstData1[2], DefaultData.firstData2[2], DefaultData.firstData3[2]),
         findAverage(DefaultData.firstData1[3], DefaultData.firstData2[3], DefaultData.firstData3[3]),
        ))

        //mode 0 - ничего не выбрали ещё
        //mode 1 - ластилиновые шары, абсолютно неупругий удар
        //mode 2 - стальные шары разной массы
        //mode 3 - стальные шары, одинаковой массы
        //undermode 0 - таблица
        //undermode 1 - ручной рассчет
        setContent {
            PhisicaTheme {
               var mode by remember{
                   mutableStateOf(0)
               }

                DrawTable(info = info)


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
fun DrawTable(info: ArrayList<Map<String, Double>>) {
    var header = info[0].keys
    Column() {
        Row() {
            header.forEach { value ->
                Text(text = value, modifier = Modifier.weight(1f).background(Color.Green))
            }
        }
        info.dropLast(1).forEach { item ->
            Row() {
                header.forEach { key ->
                    Text(text = "%.3f".format(item[key]), modifier = Modifier.weight(1f))
                }
            }
        }
        Row() {
            header.forEach { key ->
                Text(text = "%.3f".format(info.get(info.size-1)[key]), modifier = Modifier.weight(1f).background(
                    Color.LightGray))
            }
        }
    }
}