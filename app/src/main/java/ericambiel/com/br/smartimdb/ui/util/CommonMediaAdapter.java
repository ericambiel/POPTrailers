package ericambiel.com.br.smartimdb.ui.util;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.domain.Media;

public class CommonMediaAdapter extends RecyclerView.Adapter<CommonMediaHolder> {

    private List<Media> mediaList;
    private ItemMediaClickListener itemMediaClickListener;

    public CommonMediaAdapter(ItemMediaClickListener itemMediaClickListener) {
        this.itemMediaClickListener = itemMediaClickListener;

        mediaList = new ArrayList<>();
    }

    @NonNull
    @Override
    //Primeiro metodo a ser chamado na criação da RecycleView
    public CommonMediaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Ira inflar nosso rv dentro de um ViewGroup, no caso a que esta chamando essa classe, 2° paramentro é onde
        // vai se encaixar no caso em seu parent
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_media_item, parent, false);

        return new CommonMediaHolder(view, itemMediaClickListener);
    }

    //É invocado toda vez que um rv precisa ser renderizado na tela.
    @Override
    public void onBindViewHolder(@NonNull CommonMediaHolder commonMediaHolder, int position) {
        commonMediaHolder.bind(mediaList.get(position));
    }

    //Retorna o tamanho da lista dentro de um ViewHolder, a quantidade de dados a ser carregado
    @Override
    public int getItemCount() {
        return (mediaList != null && mediaList.size() > 0) ? mediaList.size() : 0;
    }

    public void setFilmes(List<Media> mediaList){
        this.mediaList = mediaList;
        notifyDataSetChanged(); //Faz o binding de cada item pra cada posição e exibi ao usuário
    }

    /**
     * Interface que implementara o click em um filme
     */
    public interface ItemMediaClickListener {
        void onClickItemFilme(Media media);
    }
}
