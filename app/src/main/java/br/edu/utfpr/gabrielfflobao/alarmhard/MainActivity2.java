package br.edu.utfpr.gabrielfflobao.alarmhard;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import adapter.AlarmeAdapterList;
import dao.AlarmeDAO;
import model.Alarme;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class MainActivity2 extends AppCompatActivity {

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ListView lista = (ListView) findViewById(R.id.listaAlarme);
        ArrayList<Alarme> alarmes = getAlarmes();
        AlarmeAdapterList adapter = new AlarmeAdapterList(alarmes, this);
        lista.setAdapter(adapter);
    }

    public ArrayList<Alarme>  getAlarmes() {
        return AlarmeDAO.getList();
    }
}