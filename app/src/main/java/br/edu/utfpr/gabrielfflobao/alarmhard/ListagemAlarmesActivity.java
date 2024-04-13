package br.edu.utfpr.gabrielfflobao.alarmhard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import adapter.AlarmeAdapterList;
import dao.AlarmeDAO;
import model.Alarme;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class ListagemAlarmesActivity extends AppCompatActivity {

    private ListView listView;

    private AlarmeAdapterList adapter;

    private ListAdapter adapterAlarme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startComponentes();

    }

    private void startComponentes() {
        setContentView(R.layout.lista_alarme_adapter);
        ArrayList<Alarme> alarmes = getAlarmes();
        adapter = new AlarmeAdapterList( this,alarmes);
        listView = (ListView) findViewById(R.id.listaAlarme);
        listView.setAdapter(adapter);
    }

    ActivityResultLauncher<Intent> resultAlarme= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {

                    if(o.getResultCode() == Activity.RESULT_OK) {
                        Intent result = o.getData();
                        Bundle bundle = result.getExtras();
                        if(bundle!=null) {
                            Alarme alarme = (Alarme) bundle.get("Alarme");
                            cadastrarAlarme(alarme);
                            ArrayList<Alarme> alarmes = getAlarmes();
                            adapter.setList(alarmes);
                            listView.setAdapter(adapter);
                            listView.deferNotifyDataSetChanged();
                            adapter.notifyDataSetChanged();


                        }
                    }
                }
            }

    );

    public ArrayList<Alarme>  getAlarmes() {
        return AlarmeDAO.getList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principais_opcoes,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuItemId = item.getItemId();
       return selectOption(menuItemId);
    }

   private boolean selectOption(int opcao) {
       if(opcao == R.id.menuItemCadastrar) {
           CadastraAlarmeActivity.enviarAlarmeCadastro(this,resultAlarme);
           return true;
       }else if (opcao == R.id.menuItemSobre) {
           SobreAcivity.goToSobreActivity(this);
           return true;
       }
       return false;

   }
    void cadastrarAlarme(Alarme alarme) {
        AlarmeDAO.add(alarme);
        AlarmeDAO.getList().forEach(v -> System.out.println(v.toString()));
    }
}