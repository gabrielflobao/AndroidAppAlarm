package utils;

import android.widget.EditText;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class InputDateParser {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    public static LocalTime getTimeAlarme(EditText date) {
        return LocalTime.from(formatter.parse(date.getText().toString()));
    }
}
