package ericambiel.com.br.poptrailers.data.network.responseCallTMDB;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * 2º
 * Classe responsavel por mapear todos o objeto result de um Json
 * trazidos de uma lista para seus atributos
 *
 * verificar: https://developers.themoviedb.org/3/movies/get-popular-movies
 */
public class MediaResponse {
    @Json(name = "poster_path")
    private final String caminhoPoster;

    @Json(name = "adult")
    private final Boolean adulto;

    @Json(name = "overview")
    private final String sinopse;

    @Json(name = "release_date")
    private final String dataLancamento;

    @Json(name = "genre_ids")
    private final List<Integer> idsGenero;

    /**
     * Id do filme, pode ser usados em outros
     * endPoints como a chamada de um trailer
     */
    @Json(name = "id")
    private final int id;

    @Json(name = "original_title")
    private final String tituloOriginal;

    @Json(name = "original_language")
    private final String linguaOriginal;

    @Json(name = "title")
    private final String titulo;

    @Json(name = "backdrop_path")
    private final String planoFundo;

    @Json(name = "popularity")
    private final double popularidade;

//    @Json(name = "video")
//    private final boolean video; //    Sempre vem falso

    @Json(name = "vote_count")
    private final int contagemVotos;

    @Json(name = "vote_average")
    private final double estrelas;

    public MediaResponse(String caminhoPoster,
                         Boolean adulto,
                         String sinopse,
                         String dataLancamento,
                         List<Integer> idsGenero,
                         int id,
                         String tituloOriginal,
                         String linguaOriginal,
                         String titulo,
                         String planoFundo,
                         double popularidade,
                         int contagemVotos,
                         double estrelas) {
        this.caminhoPoster = caminhoPoster;
        this.adulto = adulto;
        this.sinopse = sinopse;
        this.dataLancamento = dataLancamento;
        this.idsGenero = idsGenero;
        this.id = id;
        this.tituloOriginal = tituloOriginal;
        this.linguaOriginal = linguaOriginal;
        this.titulo = titulo;
        this.planoFundo = planoFundo;
        this.popularidade = popularidade;
        this.contagemVotos = contagemVotos;
        this.estrelas = estrelas;
    }

    public String getCaminhoPoster() {
        return caminhoPoster;
    }

    public Boolean getAdulto() {
        return adulto;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public List<Integer> getIdsGenero() {
        return idsGenero;
    }

    public int getId() {
        return id;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public String getLinguaOriginal() {
        return linguaOriginal;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getPlanoFundo() {
        return planoFundo;
    }

    public double getPopularidade() {
        return popularidade;
    }

    public int getContagemVotos() {
        return contagemVotos;
    }

    public double getEstrelas() {
        return estrelas;
    }
}