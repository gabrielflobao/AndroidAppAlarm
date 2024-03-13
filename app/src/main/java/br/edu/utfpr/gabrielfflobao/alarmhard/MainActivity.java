package br.edu.utfpr.gabrielfflobao.alarmhard;

import android.content.Intent;
import android.os.Bundle;

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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import dao.AlarmeDAO;
import enums.EnumNiveis;
import model.Alarme;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        this.sNivel = findViewById(R.id.sNivel);
        this.rAtivo = findViewById(R.id.rAtivo);
        this.rInativo = findViewById(R.id.rInativo);
        this.eHoraAlarme = findViewById(R.id.eHoraAlarme);
        this.inputNome = findViewById(R.id.inputNome);
        this.cDiasUteis = findViewById(R.id.cDiasUteis);
        this.bVoltar = findViewById(R.id.bVoltar);

        bVoltar.setOnClickListener(v -> {
            goToSecondPage();
        });



        bLimpar = findViewById(R.id.bLimpar);
        bLimpar.setOnClickListener(v -> {
                    Toast message = new Toast(this);
                limparCampos();
                    message = Toast.makeText(this, "Limpando campos", Toast.LENGTH_SHORT);
                    message.show();
                }
        );
        bCadastrar = findViewById(R.id.bCadastrar);
        bCadastrar.setOnClickListener(v -> {
            rAtivo = findViewById(R.id.rAtivo);
            rInativo = findViewById(R.id.rInativo);
            StringBuilder messageAction = new StringBuilder();
            Toast message = Toast.makeText(this, messageAction,Toast.LENGTH_LONG);
            if(!rInativo.isChecked() && !rAtivo.isChecked()) {
                messageAction.append("Preencha se o despertador ficará ativo ou inativo");
                message.setText(messageAction);
                message.show();
            } else if(inputNome.getText().length() ==0) {
                messageAction.append("Campo nome está vazio");
                message.setText(messageAction);
                message.show();
            } else {
                Alarme alarme = new Alarme();
                alarme.setAtivo(rAtivo.isChecked());
                alarme.setNome(inputNome.getText().toString());
                alarme.setDiasUteis(cDiasUteis.isChecked());
                alarme.setNiveis(EnumNiveis.getNivel(sNivel.getSelectedItem().toString().toUpperCase()));


                alarme.setHora(LocalTime.from(formatter.parse(eHoraAlarme.getText().toString())));


                System.out.println(alarme.getHora());
                cadastrarAlarme(alarme);
                limparCampos();
                messageAction.append("Alarme Cadastrado Com Sucesso!!!");
                message.setText(messageAction);
                message.show();
            }





        });


    }

    private void goToSecondPage() {
        Intent secondPage = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(secondPage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
       /* NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    */
        return true;
    }

    void limparCampos() {
       this.inputNome.setText("");
       this.cDiasUteis.setChecked(false);
       this.rAtivo.setChecked(false);
       this.rInativo.setChecked(false);
    }

    void cadastrarAlarme(Alarme alarme) {
        AlarmeDAO.add(alarme);
        AlarmeDAO.getList().forEach( v -> System.out.println(v.toString()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}