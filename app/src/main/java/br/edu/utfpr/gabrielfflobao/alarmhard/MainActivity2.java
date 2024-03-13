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

import dao.AlarmeDAO;

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

        listView = findViewById(R.id.ListViewItens);
        populaListView();
    }

    private void populaListView() {

        ArrayList<String> nomesAlarmes = new ArrayList<String>();
        AlarmeDAO.getList().forEach(v -> nomesAlarmes.add(v.getNome()));
        ArrayAdapter<String> list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,nomesAlarmes);
        listView.setAdapter(list);

    }
}