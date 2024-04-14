package br.edu.utfpr.gabrielfflobao.alarmhard;


import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Logger;

import dao.AlarmeDAO;
import dao.AlarmeDatabase;
import entity.component.AlarmeComponent;
import enums.EnumNiveis;
import model.Alarme;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class CadastraAlarmeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;
    private TextView tNome;
    private EditText inputNome;
    private TextView tNivel;
    private Spinner sNivel;
    private CheckBox cDiasUteis;
    private RadioButton rOpcao;
    private RadioButton rAtivo;
    private RadioButton rInativo;

    public static final String MODO = "MODO";
    private static final int NOVO = 1;
    private static final int EDITAR = 2;
    private Alarme alarmeEditado;
    private int modo;
    private EditText eHoraAlarme;


    private Button bVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StringBuilder message = new StringBuilder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        inicializaComponentes();
        if (bundle != null) {
            modo = bundle.getInt("MODO", NOVO);
            if (modo == NOVO) {
                setTitle(getString(R.string.cadastrar_alarme));
                alarmeEditado =null;
            } else if (modo == EDITAR) {
                setTitle(getString(R.string.editar_alarme));
                Long id = (Long) bundle.get("id");
                Alarme alarmeRecuperado = getDao().queryForId(id);
                alarmeEditado =  alarmeRecuperado;
                carregarDadosAlarme(alarmeRecuperado);
            }
        }
    }

    public void carregarDadosAlarme(Alarme alarmeRecuperado) {
        this.inputNome.setText(alarmeRecuperado.getNome());
        this.cDiasUteis.setChecked(alarmeRecuperado.getDiasUteis());
        if (alarmeRecuperado.getAtivo()) {
            this.rAtivo.setChecked(Boolean.TRUE);
            this.rInativo.setChecked(Boolean.FALSE);
        } else {
            this.rAtivo.setChecked(Boolean.FALSE);
            this.rInativo.setChecked(Boolean.TRUE);
        }

        this.rAtivo.setClickable(true);
        this.rInativo.setClickable(true);
        this.eHoraAlarme.setText(alarmeRecuperado.getHora().toString());
        this.sNivel.setSelection(getPositionFromEnum(alarmeRecuperado.getNivel()));


    }
    public int getPositionFromEnum(String nivel) {
        String[] niveisArray = getResources().getStringArray(R.array.niveis);

        for (int i = 0; i < niveisArray.length; i++) {
            if (nivel.equalsIgnoreCase(niveisArray[i])) {
                return i;
            }
        }

        return -1; // Retorna -1 se o enum não for encontrado no array
    }
    public void bSalvarClick() {
        AlarmeComponent component = new AlarmeComponent(inputNome, sNivel, eHoraAlarme, rAtivo, rInativo, cDiasUteis, rOpcao);
        StringBuilder messageAction = new StringBuilder();
        Toast message = Toast.makeText(this, messageAction, Toast.LENGTH_LONG);
        if (component.isOpAtivoInativoUnChecked()) {
            messageAction.append(getString(R.string.preencha_se_o_despertador_ficar_ativo_ou_inativo));
            message.setText(messageAction);
            message.show();
        } else if (component.isNomeEmpty()) {
            messageAction.append(getString(R.string.campo_nome_vazio));
            message.setText(messageAction);
            message.show();
        } else if (component.isHoraAlarmeEmpty()) {
            messageAction.append(getString(R.string.necess_rio_preencher_campo_hora));
            message.setText(messageAction);
            message.show();
        } else if (!component.validateHoraMinuto()) {
            messageAction.append(getString(R.string.campo_hora_invalido));
            message.setText(messageAction);
            message.show();

        } else {
            Alarme alarme = component.generateEntity(this);
            if (alarmeEditado == null) {
                limparCampos();
                messageAction.append(getString(R.string.alarme_cadastrado_com_sucesso));
                message.setText(messageAction);
                message.show();
                Intent intent = new Intent();
                intent.putExtra("Alarme", alarme);
                setResult(RESULT_OK, intent);
                alarmeEditado = null;
                finish();
            } else if(alarmeEditado !=null) {
                message.setText(messageAction);
                message.show();
                Intent intent = new Intent();
                intent.putExtra("id", alarmeEditado.getId());
                intent.putExtra("Alarme", alarme);
                setResult(RESULT_OK, intent);
                alarmeEditado = null;
                finish();
            } else {
                setResult(RESULT_CANCELED);
                messageAction.append(getString(R.string.falha_ao_cadastrar_alarme));
                message.setText(messageAction);
                message.show();
            }
        }

    }


    private void bLimparClick(View view) {

        Toast message = new Toast(this);
        limparCampos();
        message = Toast.makeText(this, "Limpando campos", Toast.LENGTH_SHORT);
        message.show();

    }


    public void bVoltarClick(View view) {

        try {
            bVoltar.setOnClickListener(v -> {
                goToSecondPage();
            });
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(e.getMessage());
        }


    }
    AlarmeDAO getDao() {
        return AlarmeDatabase.getDatabase(this).getAlarmeDao();
    }
    private void goToSecondPage() {
        Intent secondPage = new Intent(CadastraAlarmeActivity.this, ListagemAlarmesActivity.class);
        startActivity(secondPage);

    }


    public static void enviarAlarmeCadastro(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher) {
        Intent intent = new Intent(activity, CadastraAlarmeActivity.class);
        intent.putExtra(MODO, NOVO);
        launcher.launch(intent);
    }

    public static void editarAlarme(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher, long id) {
        Intent intent = new Intent(activity, CadastraAlarmeActivity.class);
        intent.putExtra(MODO, EDITAR);
        intent.putExtra("id", id);
        launcher.launch(intent);
    }

    public void limparCampos() {
        this.inputNome.setText("");
        this.cDiasUteis.setChecked(false);
        this.rAtivo.setChecked(false);
        this.rInativo.setChecked(false);
        this.rAtivo.setClickable(true);
        this.rInativo.setClickable(true);
        this.eHoraAlarme.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.opcoes_cadastrar_act, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuItemId = item.getItemId();
        return selectOption(menuItemId);
    }

    private boolean selectOption(int opcao) {
        if (opcao == R.id.bSalvar) {
            bSalvarClick();
            return true;
        } else if (opcao == R.id.bLimpar) {
            limparCampos();
            return true;
        }
        return false;

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void inicializaComponentes() {
        this.sNivel = findViewById(R.id.sNivel);
        this.rAtivo = findViewById(R.id.rAtivo);
        this.rInativo = findViewById(R.id.rInativo);
        this.eHoraAlarme = findViewById(R.id.eHoraAlarme);
        this.inputNome = findViewById(R.id.inputNome);
        this.cDiasUteis = findViewById(R.id.cDiasUteis);
        setTitle(getString(R.string.cadastrar_alarme));

    }


}