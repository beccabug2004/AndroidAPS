package info.nightscout.androidaps.plugins.iob.iobCobCalculator

import info.nightscout.androidaps.utils.DecimalFormatter
import info.nightscout.androidaps.utils.Round

data class GlucoseStatus(
    val glucose: Double,
    val noise: Double = 0.0,
    val delta: Double = 0.0,
    val shortAvgDelta: Double = 0.0,
    val longAvgDelta: Double = 0.0,
    val date: Long = 0L,

    // mod 7: append 2 variables for 5% range
    val autoISF_duration: Double = 0.0,
    val autoISF_average: Double = 0.0,

    // mod 8: append 3 valiables for deltas based on regression analysis
    //val slope05: Double = 0.0,
    //val slope15: Double = 0.0,
    //val slope40: Double = 0.0,

    // mod 14f: append results from best fitting parabola
    //val dura_p: Double = 0.0,
    //val delta_pl: Double = 0.0,
    //val delta_pn: Double = 0.0,
    //val r_squ: Double = 0.0,
    //val pp_debug: String = "; debug:"

) {

    fun log(): String = "Glucose: " + DecimalFormatter.to0Decimal(glucose) + " mg/dl " +
        "Noise: " + DecimalFormatter.to0Decimal(noise) + " " +
        "Delta: " + DecimalFormatter.to0Decimal(delta) + " mg/dl" +
        "Short avg. delta: " + " " + DecimalFormatter.to2Decimal(shortAvgDelta) + " mg/dl " +
        "Long avg. delta: " + DecimalFormatter.to2Decimal(longAvgDelta) + " mg/dl" +
        "Range length: " + DecimalFormatter.to0Decimal(autoISF_duration) + " min " +
        "Range average: " + DecimalFormatter.to2Decimal(autoISF_average) + " mg/dl; "
        /* +
        "5 min fit delta: " + DecimalFormatter.to2Decimal(slope05) + " mg/dl; " +
        "15 min fit delta: " + DecimalFormatter.to2Decimal(slope15) + " mg/dl; " +
        "40 min fit delta: " + DecimalFormatter.to2Decimal(slope40) + " mg/dl; " +
        "parabola length: " + DecimalFormatter.to2Decimal(dura_p) + " min; " +
        "parabola last delta: " + DecimalFormatter.to2Decimal(delta_pl) + " mg/dl; " +
        "parabola next delta: " + DecimalFormatter.to2Decimal(delta_pn) + " mg/dl; " +
        "fit correlation: " + r_squ + pp_debug
        */
}

fun GlucoseStatus.asRounded() = copy(
    glucose = Round.roundTo(glucose, 0.1),
    noise = Round.roundTo(noise, 0.01),
    delta = Round.roundTo(delta, 0.01),
    shortAvgDelta = Round.roundTo(shortAvgDelta, 0.01),
    longAvgDelta = Round.roundTo(longAvgDelta, 0.01),
    // mod 7: append 2 variables for 5% range
    autoISF_duration = Round.roundTo(autoISF_duration, 0.1),
    autoISF_average = Round.roundTo(autoISF_average, 0.1),
    // mod 8: append 3 variables for deltas extracted from regression analysis
    /*
    slope05 = Round.roundTo(slope05, 0.01),
    slope15 = Round.roundTo(slope15, 0.01),
    slope40 = Round.roundTo(slope40, 0.01),
    // mod 14f: append results from fitting parabola
    dura_p = Round.roundTo(dura_p, 0.1),
    delta_pl = Round.roundTo(delta_pl, 0.01),
    delta_pn = Round.roundTo(delta_pn, 0.01),
    r_squ = Round.roundTo(r_squ, 0.0001)
    */
)