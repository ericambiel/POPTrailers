package ericambiel.com.br.smartimdb.data.mapper;

import java.util.ArrayList;
import java.util.List;

import ericambiel.com.br.smartimdb.data.network.responseTMDB.MediaResponse;
import ericambiel.com.br.smartimdb.domain.Media;

public class FilmesMapper {
    /**
     * Converte uma lista de filmes Response em uma lista de filmes desacoplando
     * camada de dom&iacute;nio e network
     * @param listFilmeResponse Lista com filmes mapeados entre Model e endPoint
     * @return Lista de filmes trazidos pelo endPoint
     */
    public static List<Media> responseToDomain(List<MediaResponse> listFilmeResponse){
        List<Media> filmesList = new ArrayList<>();
        //Liga Objeto Media a FilmePopularResponse
        for (MediaResponse filmeResponse : listFilmeResponse){
            final Media media = new Media(
                    filmeResponse.getId(),
                    filmeResponse.getTituloOriginal(),
                    filmeResponse.getCaminhoPoster());
            filmesList.add(media);
        }
        return filmesList;
    }
}
