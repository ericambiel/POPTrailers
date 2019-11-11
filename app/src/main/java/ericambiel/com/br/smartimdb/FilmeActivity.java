package ericambiel.com.br.smartimdb;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class FilmeActivity extends Activity {
    //Primeira vez que a Activity for criada passara por aqui
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);
    }
}
