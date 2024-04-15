package br.edu.utfpr.gabrielfflobao.alarmhard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SobreAcivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sobre_acivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        inicializaComponentes();
    }

    private void inicializaComponentes() {
        TextView nomeAlunoTextView = findViewById(R.id.nomeAluno);
        TextView emailAlunoTextView = findViewById(R.id.emailAluno);
        TextView cursoAlunoTextView = findViewById(R.id.cursoAluno);
        TextView especializacaoAlunoTextView = findViewById(R.id.especializacaoAluno);
        TextView descricaoAppTextView = findViewById(R.id.descricaoApp);
        ImageView imageView = findViewById(R.id.imageView5);
        setTitle(R.string.sobre);
    }

    public static void goToSobreActivity(Activity activity) {
        Intent intent = new Intent(activity, SobreAcivity.class);
        activity.startActivity(intent);
    }
}