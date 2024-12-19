package com.example.phisica.defaultData

import com.example.phisica.calculations.Case1Calculations
import com.example.phisica.functionalHelp.findAverage

class DefaultData {
    companion object {
        //for first task
        var firstData1 = arrayListOf<Double>(0.035, 0.422, 9.50, 4.75)
        var firstData2 = arrayListOf<Double>(0.035, 0.421, 9.50, 4.50)
        var firstData3 = arrayListOf<Double>(0.035, 0.419, 9.50, 4.75)
        var firstHeader = arrayListOf<String>("m", "l", "a", "b", "p", "p\'")
        var secondHeader = arrayListOf<String>("m1", "m2", "l", "a", "b1", "b2", "p", "p\'", "k")
        var thirdHeader = arrayListOf<String>("R", "m", "a", "b", "p", "p\'", "k", "F", "c", "t")
    }
}