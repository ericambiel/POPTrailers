package ericambiel.com.br.smartimdb.ui.common

import ericambiel.com.br.smartimdb.domain.Media
import ericambiel.com.br.smartimdb.ui.MainContrato

interface CommonContrato : MainContrato.Presenter{
    interface Presenter {
        /**
         * Get videos (trailes and teasers) from API
         */
        fun getVideos(media: Media?, view: MainContrato.View)
    }
}