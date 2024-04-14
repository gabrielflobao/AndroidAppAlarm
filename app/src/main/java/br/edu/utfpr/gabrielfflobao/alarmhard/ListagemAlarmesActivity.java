package br.edu.utfpr.gabrielfflobao.alarmhard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.view.ActionMode;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.AlarmeAdapterList;
import dao.AlarmeDAO;
import dao.AlarmeDatabase;
import model.Alarme;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class ListagemAlarmesActivity extends AppCompatActivity {

    private ListView listView;

    private AlarmeAdapterList adapter;
    private ActionMode actionMode;
    private View viewSelecionada;
    private ListAdapter adapterAlarme;
    List<Alarme> alarmes;
    private int posicaoSelecionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startComponentes();
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    return false;
                }
                view.setBackgroundColor(Color.LTGRAY);
                viewSelecionada = view;
                posicaoSelecionada = position;
                listView.setEnabled(false);
                actionMode = startSupportActionMode(mActionModeCallBack);
                return true;
            }
        });

    }

    private ActionMode.Callback mActionModeCallBack = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.opcoes_ao_pressionar_item, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int idMenuImte = item.getItemId();
            if (idMenuImte == R.id.menuItemEditar) {
                editarAlarme();
                mode.finish();
                return true;
            } else if (idMenuImte == R.id.menuItemExcluir) {
                excluirAlarme();
                mode.finish();
                return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (viewSelecionada != null) {
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }
            actionMode = null;
            viewSelecionada = null;
            listView.setEnabled(true);
        }
    };

    private void editarAlarme() {
        Alarme alarme = getDao().queryForId(getAlarmes().get(posicaoSelecionada).getId());
        CadastraAlarmeActivity.editarAlarme(this, editarAlarme, alarme.getId());

    }

    private void excluirAlarme() {
        Alarme alarme = getDao().queryForId(getAlarmes().get(posicaoSelecionada).getId());
        getDao().delete(alarme);
        adapter.notifyDataSetChanged();
    }

    public List<Alarme> getAlarmes() {
        return alarmes;
    }


    private void startComponentes() {
        setContentView(R.layout.lista_alarme_adapter);
        AlarmeDatabase database = AlarmeDatabase.getDatabase(this);
         alarmes = database.getAlarmeDao().queryAllAscending();
        adapter = new AlarmeAdapterList(this, alarmes);
        listView = (ListView) findViewById(R.id.listaAlarme);
        listView.setAdapter(adapter);
    }

    ActivityResultLauncher<Intent> criarNovoAlarme = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {

                    if (o.getResultCode() == Activity.RESULT_OK) {
                        Intent result = o.getData();
                        Bundle bundle = result.getExtras();
                        if (bundle != null) {
                            Alarme alarme = (Alarme) bundle.get("Alarme");
                           if(alarme.getId() !=null){


                            cadastrarAlarme(alarme);
                            List<Alarme> alarmes = getAlarmes();
                            adapter.setList(alarmes);
                            listView.setAdapter(adapter);
                            listView.deferNotifyDataSetChanged();
                            adapter.notifyDataSetChanged();
                           } else {


                               cadastrarAlarme(alarme);
                               List<Alarme> alarmes = getAlarmes();
                               adapter.setList(alarmes);
                               listView.setAdapter(adapter);
                               listView.deferNotifyDataSetChanged();
                               adapter.notifyDataSetChanged();


                           }

                        }
                    }
                }
            }

    );


    ActivityResultLauncher<Intent> editarAlarme = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {

                    if (o.getResultCode() == Activity.RESULT_OK) {
                        Intent result = o.getData();
                        Bundle bundle = result.getExtras();
                        if (bundle != null) {
                            Long id = (Long) bundle.get("id");
                            Alarme alarme = (Alarme) bundle.get("Alarme");
                            Alarme alarmeEditar = getDao().queryForId(id);
                            alarmeEditar.setNome(alarme.getNome());
                            alarmeEditar.setHora(alarme.getHora());
                            alarmeEditar.setNivel(alarme.getNivel());
                            alarmeEditar.setDiasUteis(alarme.getDiasUteis());
                            alarmeEditar.setAtivo(alarme.getAtivo());
                            getDao().update(alarmeEditar);
                            adapter.notifyDataSetChanged();


                        }
                    }
                }
            }

    );

     AlarmeDAO getDao() {
        return AlarmeDatabase.getDatabase(this).getAlarmeDao();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principais_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuItemId = item.getItemId();
        return selectOption(menuItemId);
    }

    private boolean selectOption(int opcao) {
        if (opcao == R.id.menuItemCadastrar) {
            CadastraAlarmeActivity.enviarAlarmeCadastro(this, criarNovoAlarme);
            return true;
        } else if (opcao == R.id.menuItemSobre) {
            SobreAcivity.goToSobreActivity(this);
            return true;
        }
        return false;

    }

    void cadastrarAlarme(Alarme alarme) {
        AlarmeDatabase database = AlarmeDatabase.getDatabase(this);
        database.getAlarmeDao().insert(alarme);
    }
}