package ericambiel.com.br.smartimdb.ui.listafilmes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ericambiel.com.br.smartimdb.R;

public class listaFilmesAdapter extends RecyclerView.Adapter<listaFilmesAdapter.ListaFilmesViewHolder> {

    @NonNull
    @Override
    //Primeiro metodo a ser chamado na criação da RecycleView
    public ListaFilmesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Ira inflar nosso rv dentro de um ViewGroup, no caso a que esta chamando essa classe
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_filme, parent, false);

        return new ListaFilmesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaFilmesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ListaFilmesViewHolder extends RecyclerView.ViewHolder{

        public ListaFilmesViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
