package com.example.phisica.functionalHelp

import android.util.Log

fun countAverage (info:  ArrayList<Map<String, Double>>): Map<String, Double> {
    var header = info[0].keys.toList()
    var resultMap = mutableMapOf<String, Double>()

    header.forEachIndexed { index, key ->
        var sum = 0.0
        info.forEach { item ->
            sum += item[key] ?:0.0
        }
        var average = sum/info.size
        resultMap[header[index]] = average
    }
    Log.d("TEST5", resultMap.toString())
    return resultMap
}