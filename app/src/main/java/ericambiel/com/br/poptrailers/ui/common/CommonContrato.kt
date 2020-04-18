package ericambiel.com.br.poptrailers.ui.common

import ericambiel.com.br.poptrailers.domain.Media
import ericambiel.com.br.poptrailers.ui.MainContrato

interface CommonContrato : MainContrato.Presenter{
    interface Presenter {
        /**
         * Get videos (trailes and teasers) from API
         */
        fun getVideos(media: Media?, view: MainContrato.View)
    }
}