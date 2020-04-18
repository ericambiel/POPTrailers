package ericambiel.com.br.poptrailers.data.mapper

import ericambiel.com.br.poptrailers.data.network.responseCallTMDB.VideoResponse
import ericambiel.com.br.poptrailers.domain.Video
import java.util.*


class VideosFilmeMapper {
    //Auto instancia o objeto com nome de companion
    companion object {
        /**
         * Converte uma lista de videos Response em uma lista de Videos de Filmes desacoplando
         * camada de domnio e network
         * @param listVideosResponse Lista com videos de filmes mapeados entre Model e endPoint
         * @return Lista de filmes filmes trazidos pelo endPoint
         */
        @JvmStatic //Não é necessário chamar NomeClasse.Companion.nomeMetodo na chamada ao metodo em uma classe Java
        fun responseToDomain(listVideosResponse: List<VideoResponse>): List<Video> {
            val videosFilmeList: MutableList<Video> = ArrayList()
            for (videoFilmeResponse in listVideosResponse) {
                val videoFilme = Video(
                        videoFilmeResponse.keyVideo,
                        videoFilmeResponse.tituloVideo,
                        videoFilmeResponse.linguaVideo + "-" + videoFilmeResponse.paisVideo,
                        videoFilmeResponse.fonteVideo,
                        videoFilmeResponse.resolucaoVideo)
                videosFilmeList.add(videoFilme)
            }
            return videosFilmeList
        }
    }


}