package ericambiel.com.br.smartimdb.data.mapper;

import java.util.ArrayList;
import java.util.List;

import ericambiel.com.br.smartimdb.data.network.responseTMDB.FilmePopularesResponse;
import ericambiel.com.br.smartimdb.domain.Filme;

public class FilmesMapper {
    /**
     * Converte uma lista de filmes Response em uma lista de filmes desacoplando
     * camada de dom&iacute;nio e network
     * @param listFilmeResponse Lista com filmes mapeados entre Model e endPoint
     * @return Lista de filmes trazidos pelo endPoint
     */
    public static List<Filme> responseToDomain(List<FilmePopularesResponse> listFilmeResponse){
        List<Filme> filmesList = new ArrayList<>();
        //Liga Objeto Filme a FilmePopularResponse
        for (FilmePopularesResponse filmeResponse : listFilmeResponse){
            final  Filme filme = new Filme(
                    filmeResponse.getId(),
                    filmeResponse.getTituloOriginal(),
                    filmeResponse.getCaminhoPoster());
            filmesList.add(filme);
        }
        return filmesList;
    }
}
