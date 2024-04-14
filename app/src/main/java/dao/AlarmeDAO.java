package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

import model.Alarme;
@Dao
public interface AlarmeDAO {


    @Insert
    long insert(Alarme alarme);

    @Delete
    int delete(Alarme alarme);

    @Update
    int update(Alarme alarme);
    @Query("select * from alarme order by nome asc")
    List<Alarme> queryAllAscending();

    @Query("select * from alarme order by nome desc")
    List<Alarme> queryAllDesc();


    @Query("select * from alarme where id = :id")
    Alarme queryForId(long id);


}
