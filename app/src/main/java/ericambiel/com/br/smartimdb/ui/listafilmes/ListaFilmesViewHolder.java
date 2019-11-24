package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ericambiel.com.br.smartimdb.R;

//Classe com contem conteudo do RV
class ListaFilmesViewHolder extends RecyclerView.ViewHolder{

    TextView textTituloFilme;
    public ListaFilmesViewHolder(@NonNull View itemView) {
        super(itemView);

        textTituloFilme = itemView.findViewById(R.id.text_titulo_filme);

        textTituloFilme.setText("Filme de Exemplo");
    }
}
