package ericambiel.com.br.smartimdb.data.network.responseTMDB;

import com.squareup.moshi.Json;

/**
 * 2º
 * Classe responsavel por mapear todos o objeto dentro de "results" de um Json
 * trazidos de uma lista para seus atributos
 *
 * verificar: https://developers.themoviedb.org/3/movies/get-movie-videos
 */
public class VideosFilmesResponse {
    @Json(name = "id")
    private final String idVideo;

    /**
     * Lingua do video "pt"
     */
    @Json(name = "iso_639_1")
    private final String linguaVideo;

    /**
     * Pais do video "BR"
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
     * ex: "Fight Club | #TBT Trailer | 20th Century FOX"
     */
    @Json(name = "name")
    private final String nomeVideo;

    /**
     * Site onde Video esta ospedados
     * ex: YouTube, Vimeo
     */
    @Json(name = "site")
    private final String siteVideo;

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
    private final int tipoVideo;

    public VideosFilmesResponse(String idVideo,
                                String linguaVideo,
                                String paisVideo,
                                String keyVideo,
                                String nomeVideo,
                                String siteVideo,
                                int resolucaoVideo,
                                int tipoVideo) {
        this.idVideo = idVideo;
        this.linguaVideo = linguaVideo;
        this.paisVideo = paisVideo;
        this.keyVideo = keyVideo;
        this.nomeVideo = nomeVideo;
        this.siteVideo = siteVideo;
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

    public String getNomeVideo() {
        return nomeVideo;
    }

    public String getSiteVideo() {
        return siteVideo;
    }

    public int getResolucaoVideo() {
        return resolucaoVideo;
    }

    public int getTipoVideo() {
        return tipoVideo;
    }
}