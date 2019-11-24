package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ericambiel.com.br.smartimdb.R;
import ericambiel.com.br.smartimdb.data.network.response.FilmesPopularesResponse;

public class ListaFilmesAdapter extends RecyclerView.Adapter<ListaFilmesViewHolder> {

    private List<FilmesPopularesResponse> filmes;

    ListaFilmesAdapter(List<FilmesPopularesResponse> filmes) {
        this.filmes = filmes;
    }

    @NonNull
    @Override
    //Primeiro metodo a ser chamado na criação da RecycleView
    public ListaFilmesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Ira inflar nosso rv dentro de um ViewGroup, no caso a que esta chamando essa classe, 2° paramentro é onde
        // vai se encaixar no caso no seu parent
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_filme, parent, false);

        return new ListaFilmesViewHolder(view);
    }

    //É invocado toda vez que um rv precisa ser renderizado na tela.
    @Override
    public void onBindViewHolder(@NonNull ListaFilmesViewHolder holder, int position) {
        holder.textTituloFilme.setText(filmes.get(position).getTituloOriginal());
    }

    //Retorna o tamanho da lista dentro de um ViewHolder, a quantidade de dados a ser carregado
    @Override
    public int getItemCount() {
        return (filmes != null && filmes.size() > 0) ? filmes.size() : 0;
    }
}
