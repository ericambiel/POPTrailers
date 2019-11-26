package ericambiel.com.br.smartimdb.ui.listafilmes;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.model.Filme;

//Classe contem conteudo do RV
class ListaFilmesViewHolder extends RecyclerView.ViewHolder{

    public AppCompatTextView textTituloFilme;
    public AppCompatImageView imagePoster;

    ListaFilmesViewHolder(@NonNull View itemView) {
        super(itemView);

        textTituloFilme = itemView.findViewById(R.id.text_titulo_filme);
        imagePoster = itemView.findViewById(R.id.image_poster_filme);

    }

    public void bind(Filme filme){
        textTituloFilme.setText(filme.getTituloOriginal());

        //Baixa a imagem e mostra em um componente imageView
        Picasso.get().load("https://image.tmdb.org/t/p/w342/" + filme.getCaminhoPoster()).into(imagePoster);
    }
}
