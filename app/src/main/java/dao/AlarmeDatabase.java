package dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import model.Alarme;
import utils.Converters;

/**
 * Author : Gabriel F F Lobão
 * Date : 14/04/2024
 */

@Database(entities = {Alarme.class},version = 1,exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AlarmeDatabase extends RoomDatabase {

    public abstract  AlarmeDAO getAlarmeDao();

    private static AlarmeDatabase instance;

    public static AlarmeDatabase getDatabase(final Context context) {
        if(instance==null){
            synchronized (AlarmeDatabase.class){
                if(instance==null){
                    instance = Room.databaseBuilder(context,AlarmeDatabase.class,"alarme.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }

}
