package br.edu.utfpr.gabrielfflobao.alarmhard;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
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

import entity.component.AlarmeComponent;
import model.Alarme;

/**
 * Author : Gabriel F F Lobão
 * Date : 16/03/2024
 */
public class CadastraAlarmeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;
    private Button bLimpar;
    private Button bCadastrar;
    private TextView tCadastroAlarme;
    private TextView tNome;
    private EditText inputNome;
    private TextView tNivel;
    private Spinner sNivel;
    private CheckBox cDiasUteis;
    private RadioButton rOpcao;
    private RadioButton rAtivo;
    private RadioButton rInativo;

    private EditText eHoraAlarme;

    private Button bVoltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StringBuilder message = new StringBuilder();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        inicializaComponentes();

    }

    public void bCadastrarClick(View view) {
        AlarmeComponent component = new AlarmeComponent(inputNome,
                sNivel,
                eHoraAlarme,
                rAtivo,
                rInativo,
                cDiasUteis,
                rOpcao
        );
        bCadastrar.setOnClickListener(v -> {
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
                if (alarme != null) {
                    limparCampos();
                    messageAction.append(getString(R.string.alarme_cadastrado_com_sucesso));
                    message.setText(messageAction);
                    message.show();
                    Intent intent = new Intent();
                    intent.putExtra("Alarme", alarme);
                    setResult(RESULT_OK,intent);
                    finish();
                } else {
                    setResult(RESULT_CANCELED);
                    messageAction.append(getString(R.string.falha_ao_cadastrar_alarme));
                    message.setText(messageAction);
                    message.show();
                }
            }

    });


}


    private void bLimparClick(View view) {
        try {
            bLimpar.setOnClickListener(v -> {
                        Toast message = new Toast(this);
                        limparCampos();
                        message = Toast.makeText(this, "Limpando campos", Toast.LENGTH_SHORT);
                        message.show();
                    }
            );
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
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

    private void goToSecondPage() {
        Intent secondPage = new Intent(CadastraAlarmeActivity.this, ListagemAlarmesActivity.class);
        startActivity(secondPage);

    }


    public static void enviarAlarmeCadastro(AppCompatActivity activity, ActivityResultLauncher<Intent> launcher) {
        Intent intent = new Intent(activity, CadastraAlarmeActivity.class);
        launcher.launch(intent);
    }

    void limparCampos() {
        this.inputNome.setText("");
        this.cDiasUteis.setChecked(false);
        this.rAtivo.setChecked(false);
        this.rInativo.setChecked(false);
        this.rAtivo.setClickable(true);
        this.rInativo.setClickable(true);
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
        this.bLimpar = findViewById(R.id.bLimpar);
        this.bCadastrar = findViewById(R.id.bCadastrar);

    }
}