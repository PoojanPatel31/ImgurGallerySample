package com.imgurgallery.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class DateTimeUtil {
    companion object {

        /**
         * Convert UTC time to local time and format it.
         */
        fun format(utcTimeInSec: Long): String {
            val date = Calendar.getInstance().apply {
                timeInMillis = utcTimeInSec * 1000
                timeZone = TimeZone.getDefault()
            }.time

            val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.getDefault()).apply {
                timeZone = TimeZone.getDefault()
            }

            return sdf.format(date)
        }
    }
}