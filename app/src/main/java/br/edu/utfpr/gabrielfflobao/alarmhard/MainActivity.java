package br.edu.utfpr.gabrielfflobao.alarmhard;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;


import enums.EnumNiveis;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppBarConfiguration appBarConfiguration;


    Button bLimpar;
    Button bCadastrar;
    TextView tCadastroAlarme;
    TextView tNome;
    EditText inputNome;

    TextView tNivel;
    Spinner sNivel;

    CheckBox cDiasUteis;
    RadioButton rOpcao;

    RadioButton rAtivo;
    RadioButton rInativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        sNivel = findViewById(R.id.sNivel);
        rAtivo = findViewById(R.id.rAtivo);
        rInativo = findViewById(R.id.rInativo);
        this.inputNome = findViewById(R.id.inputNome);
        this.cDiasUteis = findViewById(R.id.cDiasUteis);




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
            StringBuilder messageError = new StringBuilder();
            Toast message = Toast.makeText(this,messageError,Toast.LENGTH_LONG);
            if(!rInativo.isChecked() && !rAtivo.isChecked()) {
                messageError.append("Preencha se o despertador ficará ativo ou inativo");
                message.setText(messageError);
                message.show();
            } else if(inputNome.getText().length() ==0) {
                messageError.append("Campo nome está vazio");
                message.setText(messageError);
                message.show();
            }



        });


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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}