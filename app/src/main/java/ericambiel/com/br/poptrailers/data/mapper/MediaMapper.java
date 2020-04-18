package ericambiel.com.br.poptrailers.data.mapper;

import java.util.ArrayList;
import java.util.List;

import ericambiel.com.br.poptrailers.data.network.responseCallTMDB.MediaResponse;
import ericambiel.com.br.poptrailers.domain.Media;

public class MediaMapper {
    /**
     * Converte uma lista de filmes/series Response em uma lista de filmes/series desacoplando
     * camada de dom&iacute;nio e network
     * @param listMediaResponse Lista com filmes/series/ mapeados entre Model e endPoint
     * @return Lista de filmes/series trazidos pelo endPoint
     */
    public static List<Media> responseToDomain(List<MediaResponse> listMediaResponse){
        List<Media> filmesList = new ArrayList<>();
        for (MediaResponse mediaResponse : listMediaResponse){
            final Media media = new Media(
                    mediaResponse.getId(),
                    mediaResponse.getTituloOriginal(),
                    mediaResponse.getCaminhoPoster(),
                    mediaResponse.getDataLancamento());
            filmesList.add(media);
        }
        return filmesList;
    }
}
