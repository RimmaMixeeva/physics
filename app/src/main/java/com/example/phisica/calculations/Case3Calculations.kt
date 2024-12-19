package com.example.phisica.calculations

import com.example.phisica.functionalHelp.Acos
import com.example.phisica.functionalHelp.Cos
import com.example.phisica.functionalHelp.Sin
import java.lang.Math.sqrt


fun Case3Calculations (
    mass: Double, radius: Double,
    alpha: Double, beta: Double,
    t: Double
): Map<String, Double> {
    val beta = beta
    val g = 9.8

    // Вычисление импульса до удара (p)
    val pBefore = mass * sqrt(2 * g * radius * (1 - Cos(alpha)))

    // Вычисление импульса после удара (p')
    val pAfter = mass * sqrt(2 * g * radius * (1 - Cos(beta)))

    // Коэффициент
    val k0 = Sin(beta / 2) / Sin(alpha / 2)

    // Сила удара F
    val F = (pBefore - pAfter) / (t / 1000000)

    // Скорость удара c
    val c = sqrt(2 * g * radius * (1 - Cos(alpha)))

    val result = mapOf<String, Double>(
        "r" to radius, "m" to mass, "a" to alpha, "b" to beta,
        "p" to pBefore, "p\'" to pAfter, "k" to k0,
        "F" to F, "c" to c, "t" to t
        )
    return result
}