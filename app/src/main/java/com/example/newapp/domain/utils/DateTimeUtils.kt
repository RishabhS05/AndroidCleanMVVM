package com.example.newapp.domain.utils

import android.util.Log
import com.example.newapp.domain.utils.DateUtilsConsts.yyyy_MM_dd
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtilsConsts {
    const val yyyy_MM_dd = "yyyy-MM-dd"
}
// Return format: like 2023-10-05
fun getCurrentSimpleDate(): String {
    val date = Date()
    Log.d("Steps Date", " $date")
    return SimpleDateFormat(yyyy_MM_dd, Locale.getDefault()).format(date)
}
fun compareServerDates(first : String? ,second : String? ) : Boolean {
    if(first == null || second == null ) return  false
    try {
        val sdf = SimpleDateFormat(yyyy_MM_dd, Locale.ENGLISH)
        val startDateValue = sdf.parse(first) //"2018-08-30"
        val currentDateValue = sdf.parse(second)
        if (startDateValue != null && currentDateValue != null) {
            return startDateValue.compareTo(currentDateValue) == 0
        }
    }catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}