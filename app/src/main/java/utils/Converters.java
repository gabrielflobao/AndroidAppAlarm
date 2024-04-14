package utils;

import androidx.room.TypeConverter;

import java.time.LocalTime;

/**
 * Author : Gabriel F F Lobão
 * Date : 14/04/2024
 */
public class Converters {
    @TypeConverter
    public static LocalTime fromString(String value) {
        return value == null ? null : LocalTime.parse(value);
    }

    @TypeConverter
    public static String localTimeToString(LocalTime time) {
        return time == null ? null : time.toString();
    }
}
