package ericambiel.com.br.smartimdb.ui.menuprincipal.filmespopulares;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.model.Filme;

public class FilmesPopularesAdapter extends RecyclerView.Adapter<FilmesPopularesHolder> {

    private List<Filme> listFilmes;
    private ItemFilmeClickListener itemFilmeClickListener;

    FilmesPopularesAdapter(ItemFilmeClickListener itemFilmeClickListener) {
        this.itemFilmeClickListener = itemFilmeClickListener;

        listFilmes = new ArrayList<>();
    }

    @NonNull
    @Override
    //Primeiro metodo a ser chamado na criação da RecycleView
    public FilmesPopularesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Ira inflar nosso rv dentro de um ViewGroup, no caso a que esta chamando essa classe, 2° paramentro é onde
        // vai se encaixar no caso em seu parent
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_filme, parent, false);

        return new FilmesPopularesHolder(view, itemFilmeClickListener);
    }

    //É invocado toda vez que um rv precisa ser renderizado na tela.
    @Override
    public void onBindViewHolder(@NonNull FilmesPopularesHolder filmesPopularesHolder, int position) {
        filmesPopularesHolder.bind(listFilmes.get(position));
    }

    //Retorna o tamanho da lista dentro de um ViewHolder, a quantidade de dados a ser carregado
    @Override
    public int getItemCount() {
        return (listFilmes != null && listFilmes.size() > 0) ? listFilmes.size() : 0;
    }

    void setFilmes(List<Filme> filmeList){
        this.listFilmes = filmeList;
        notifyDataSetChanged(); //Faz o binding de cada item pra cada posição e exibi ao usuário
    }

    /**
     * Interface que implementara o click em um filme
     */
    public interface ItemFilmeClickListener {
        void onClickItemFilme(Filme filme);
    }
}
