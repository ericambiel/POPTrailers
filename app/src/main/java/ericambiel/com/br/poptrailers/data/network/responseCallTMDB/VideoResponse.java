package ericambiel.com.br.poptrailers.data.network.responseCallTMDB;

import com.squareup.moshi.Json;

/**
 * 2º
 * Classe responsavel por mapear todos o objeto dentro de "results" de um Json
 * trazidos de uma lista para seus atributos
 *
 * verificar: https://developers.themoviedb.org/3/movies/get-movie-videos
 */
public class VideoResponse {
    @Json(name = "id")
    private final String idVideo;

    /**
     * Lingua do video "pt"
     */
    @Json(name = "iso_639_1")
    private final String linguaVideo;

    /**
     * Pa&iacute;s do video "BR"
     */
    @Json(name = "iso_3166_1")
    private final String paisVideo;

    /**
     * Chave de acesso, colocado no site ex: SUXWAEX2jlg
     * ...tube.com/watch?v=SUXWAEX2jlg
     */
    @Json(name = "key")
    private final String keyVideo;

    /**
     * Nome do video cadastrado no TMDB (pode ser diferente da fonte)
     * ex: "Fight Club | 20th Century FOX"
     */
    @Json(name = "name")
    private final String tituloVideo;

    /**
     * Site onde Video esta ospedados
     * ex: YouTube, Vimeo
     */
    @Json(name = "site")
    private final String fonteVideo;

    /**
     * Resolução do video
     * Valores: 360, 480, 720, 1080
     */
    @Json(name = "size")
    private final int resolucaoVideo;

    /**
     * Tipo do video
     * Valores: Trailer, Teaser, Clip, Featurette, Behind the Scenes, Bloopers
     */
    @Json(name = "type")
    private final String tipoVideo;

    public VideoResponse(String idVideo,
                         String linguaVideo,
                         String paisVideo,
                         String keyVideo,
                         String tituloVideo,
                         String fonteVideo,
                         int resolucaoVideo,
                         String tipoVideo) {
        this.idVideo = idVideo;
        this.linguaVideo = linguaVideo;
        this.paisVideo = paisVideo;
        this.keyVideo = keyVideo;
        this.tituloVideo = tituloVideo;
        this.fonteVideo = fonteVideo;
        this.resolucaoVideo = resolucaoVideo;
        this.tipoVideo = tipoVideo;
    }

    public String getIdVideo() {
        return idVideo;
    }

    public String getLinguaVideo() {
        return linguaVideo;
    }

    public String getPaisVideo() {
        return paisVideo;
    }

    public String getKeyVideo() {
        return keyVideo;
    }

    public String getTituloVideo() {
        return tituloVideo;
    }

    public String getFonteVideo() {
        return fonteVideo;
    }

    public int getResolucaoVideo() {
        return resolucaoVideo;
    }

    public String getTipoVideo() {
        return tipoVideo;
    }
}