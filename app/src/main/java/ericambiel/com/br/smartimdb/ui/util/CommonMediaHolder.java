package ericambiel.com.br.smartimdb.ui.util;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.domain.Media;

//Classe contem conteudo do RV
class CommonMediaHolder extends RecyclerView.ViewHolder{

    private AppCompatTextView textMediaTitle, textReleaseDate;
    private AppCompatImageView imagePoster;
    private Media media;

    CommonMediaHolder(@NonNull View itemView,
                      final CommonMediaAdapter.ItemMediaClickListener itemMediaClickListener) {
        super(itemView);

        imagePoster = itemView.findViewById(R.id.image_media_poster);
        textMediaTitle = itemView.findViewById(R.id.text_media_title);
        textReleaseDate = itemView.findViewById(R.id.text_release_date);

        //Dispara esse metodo a cada click do media escolhido
        itemView.setOnClickListener(v -> {
            //Se estiver instânciado no Adapter
            if (itemMediaClickListener != null) {
                //Devolve media clicado
                itemMediaClickListener.onClickItemMedia(media);
            }
        });
    }

    void bind(Media media){
        this.media = media;

        textMediaTitle.setText(media.getOriginalTitle());
        textReleaseDate.setText(media.getRelaseDate());
        //Baixa a imagem e mostra em um componente imageView
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + media.getPosterPath())
                .into(imagePoster);
    }
}
