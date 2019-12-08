package ericambiel.com.br.smartimdb.data.mapper

import ericambiel.com.br.smartimdb.data.model.Trailer
import ericambiel.com.br.smartimdb.data.network.responseTMDB.VideoFilmeResponse
import java.util.*

class VideosFilmesMapper {
    /**
     * Converte uma lista de videos Response em uma lista de Videos de Filmes desacoplando
     * camada de domnio e network
     * @param listVideosFilmeResponse Lista com videos de filmes mapeados entre Model e endPoint
     * @return Lista de filmes filmes trazidos pelo endPoint
     */
    fun responseToDomain(listVideosFilmeResponse: List<VideoFilmeResponse>): List<Trailer> {
        val videosFilmeList: MutableList<Trailer> = ArrayList()
        for (videoFilmeResponse in listVideosFilmeResponse) {
            val videoFilme = Trailer(
                    videoFilmeResponse.keyVideo,
                    videoFilmeResponse.tituloVideo,
                    videoFilmeResponse.paisVideo + "-" + videoFilmeResponse.linguaVideo,
                    videoFilmeResponse.fonteVideo,
                    videoFilmeResponse.resolucaoVideo)
            videosFilmeList.add(videoFilme)
        }
        return videosFilmeList
    }
}