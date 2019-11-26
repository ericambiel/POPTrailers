package ericambiel.com.br.smartimdb.data.mapper;

import java.util.ArrayList;
import java.util.List;

import ericambiel.com.br.smartimdb.data.model.Filme;
import ericambiel.com.br.smartimdb.data.network.response.FilmePopularesResponse;

public class FilmesMapper {
    /**
     * Converte uma lista de filmes Response em uma lista de filmes desacoplando
     * camada de domínio e network
     * @param listFilmeResponse
     * @return
     */
    public static List<Filme> responseToDomain(List<FilmePopularesResponse> listFilmeResponse){
        List<Filme> filmesList = new ArrayList<>();

        for (FilmePopularesResponse filmeResponse : listFilmeResponse){
            final  Filme filme = new Filme(filmeResponse.getTituloOriginal(), filmeResponse.getCaminhoPoster());
            filmesList.add(filme);
        }

        return filmesList;
    }
}
