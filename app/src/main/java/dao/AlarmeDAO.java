package dao;

import java.util.ArrayList;
import java.util.List;

import model.Alarme;

public class AlarmeDAO {

    private static ArrayList<Alarme> alarmeList = new ArrayList<Alarme>();


    public static ArrayList<Alarme> getList() {
        return alarmeList;
    }

    public static void add(Alarme alarme) {
        alarmeList.add(alarme);
    }

}
