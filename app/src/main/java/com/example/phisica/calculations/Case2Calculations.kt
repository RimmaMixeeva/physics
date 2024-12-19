package com.example.phisica.calculations

import com.example.phisica.functionalHelp.Acos
import com.example.phisica.functionalHelp.Cos
import com.example.phisica.functionalHelp.Sin
import java.lang.Math.sqrt

fun Case2Calculations(
    m1: Double, m2: Double,
    length: Double, alpha: Double,
    beta1: Double, beta2: Double
): Map<String, Double> {
// Исходные данные
    val g: Double = 9.8
    var beta1 = beta1
    var beta2 = beta2

    // Расчёт скорости v1 по формуле: v1 = sqrt(2 * g * l * (1 - cos(alpha)))
    val v1 = sqrt(2 * g * length * (1 - Cos(alpha)))

    // Скорости после столкновения
    val u1 = v1 * (m1 - m2) / (m1 + m2)  // скорость первого шара после удара
    val u2 = 2 * m1 * v1 / (m1 + m2)    // скорость второго шара после удара

    // Импульс до удара p = m1 * v1
    val pBefore = m1 * v1
    val pAfter = m1 * u1 + m2 * u2

    // Коэффициент
    val k0 = (Sin(beta2 / 2) - Sin(beta1 / 2)) / Sin(alpha / 2)

    val result = mapOf<String, Double>(
        "m1" to m1, "m2" to m2, "l" to length,
        "a" to alpha, "b1" to beta1, "b2" to beta2,
        "p" to pBefore, "p\'" to pAfter, "k" to k0)
    return result
}