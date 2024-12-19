package com.example.phisica.calculations

import com.example.phisica.functionalHelp.Acos
import com.example.phisica.functionalHelp.Cos
import java.lang.Math.sqrt

//calculation of impulse in plasticine balls case
fun Case1Calculations (m: Double, length: Double, alpha: Double, betta: Double?): Map<String, Double> {
    var g: Double = 9.8
    var betta = betta
    // Расчёт скорости v1 по формуле: v1 = sqrt(2 * g * l * (1 - cos(alpha)))
    val v1 = sqrt(2 * g * length * (1 - Cos(alpha)))

    // Импульс до удара p = m * v1 или 4*m* sqrt(g*length)* Sin(beta/2)
    val pBefore = m * v1

    // Скорость после абсолютно неупругого удара: v' = v1 / 2
    val u = v1 / 2

    if (betta == null){//на случай, если угол надо рассчитать
    betta = Acos(1 - (u*u)/(2*g*length))
    }

    // Импульс после удара p' = 2m * v'
    val pAfter= 2 * m * u

    // Вывод результатов
    var result = mutableMapOf<String, Double>()
    result["m"] = m
    result["l"] = length
    result["a"] = alpha
    result["b"] = betta
    result["p"] = pBefore
    result["p\'"] = pAfter

    return result
  }