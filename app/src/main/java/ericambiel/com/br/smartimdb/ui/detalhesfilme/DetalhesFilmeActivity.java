package ericambiel.com.br.smartimdb.ui.detalhesfilme;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.model.Filme;

public class DetalhesFilmeActivity extends AppCompatActivity {

    public static final String TAG = "EXTRA_FILME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        TextView textTituloFilme = findViewById(R.id.text_titulo_original_filme);

        //Recebe o objeto Filme de outra Activity via serializable
        final Filme filme = (Filme)getIntent().getSerializableExtra(TAG);

        //Seta titulo da Actvity com o nome do Filme escolhido
        textTituloFilme.setText(filme.getTituloOriginal());
    }
}
