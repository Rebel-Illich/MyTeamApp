package com.hunter.myteams.utils

import androidx.room.TypeConverter
import java.util.stream.Collectors

class Converters {

    @TypeConverter
    fun fromFilters(filters: List<String?>): String? {
        return filters.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toFilters(data: String): String {
        return mutableListOf(data.split(",")).toString()
    }
}

