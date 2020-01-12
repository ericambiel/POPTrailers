package ericambiel.com.br.smartimdb.ui;

import java.util.List;

import ericambiel.com.br.smartimdb.domain.Media;

/**
 * Interface responsavel por designar Presenters e Views as classes (MVP).
 */
public interface MediaContrato {
    /**
     * Interface a ser extendia para todas as Views.
     * Comum a todas as Views.
     */
    interface View {
        /**
         * Exibe player do YouTube em novo Fragmento
         * @param keyVideoList lista de videos a serem executados
         */
        void iniciaYoutubePlayer(List<String> keyVideoList);

        /**
         * Exibe erro caso haja problemas de comunicação entre app e endpoints TMDB.
         */
        void mostraErro(String erro);

        /**
         * Comtem lista de filmes populares a serem exibidos.
         * @param mediaList lista com filmes a serem exibidos.
         */
        void showMedia(List<Media> mediaList);
    }

    /**
     * Interface a ser extendia para todas os Presenters.
     * Comum a todos os Presenters.
     */
    interface Presenter{
        /**
         * Evita que um presenter fique orfão de uma View.
         */
        void destruirView();

        /**
         * Get videos (trailes and teasers) from API
         */
        void getVideos(Media media);

        /**
         * Get medias (movies, series, shows, etc) from API
         */
        void getMedia();
    }
}
