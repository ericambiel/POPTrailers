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
class HolderFilmesPopulares extends RecyclerView.ViewHolder{

    private AppCompatTextView textTituloFilme;
    private AppCompatImageView imagePoster;
    private Filme filme;

    HolderFilmesPopulares(@NonNull View itemView, final AdapterFilmesPopulares.ItemFilmeClickListener itemFilmeClickListener) {
        super(itemView);

        textTituloFilme = itemView.findViewById(R.id.text_titulo_filme);
        imagePoster = itemView.findViewById(R.id.image_poster_filme);

        //Dispara esse metodo a cada click do filme escolhido
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se estiver instânciado no Adapter
                if (itemFilmeClickListener != null) {
                    //Devolve filme clicado
                    itemFilmeClickListener.onClickItemFilme(filme);
                }
            }
        });
    }

    public void bind(Filme filme){
        this.filme = filme;

        textTituloFilme.setText(filme.getTituloOriginal());

        //Baixa a imagem e mostra em um componente imageView
        Picasso.get().load("https://image.tmdb.org/t/p/w342/" + filme.getCaminhoPoster()).into(imagePoster);
    }
}
