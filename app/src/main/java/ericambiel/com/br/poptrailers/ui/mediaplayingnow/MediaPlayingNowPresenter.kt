package ericambiel.com.br.poptrailers.ui.mediaplayingnow

import ericambiel.com.br.poptrailers.data.mapper.MediaMapper
import ericambiel.com.br.poptrailers.data.network.RetrofitConfig
import ericambiel.com.br.poptrailers.data.network.responseCallTMDB.DiscoveryCall
import ericambiel.com.br.poptrailers.data.network.responseCallTMDB.MediaResult
import ericambiel.com.br.poptrailers.domain.Media
import ericambiel.com.br.poptrailers.ui.MainContrato
import ericambiel.com.br.poptrailers.ui.common.CommonPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MediaPlayingNowPresenter(view: MediaPlayingNowFragment) : MainContrato.Presenter {
    private var view: MediaPlayingNowFragment? = view
    private var mediaList: List<Media> = ArrayList()
    private var discoveryCall: DiscoveryCall = DiscoveryCall()

    override fun getMedia() {
        RetrofitConfig
            .getInstanceTMDB()
            .getMediaPlayingNow(
                    discoveryCall.API_KEY,
                    discoveryCall.page,
                    discoveryCall.language,
                    discoveryCall.region,
                    discoveryCall.releaseDataGte,
                    discoveryCall.releaseDataLte,
                    discoveryCall.withReleaseType,
                    discoveryCall.includeAdult,
                    discoveryCall.sortBy)
            .enqueue(object : Callback<MediaResult?> {
                // status code >= 200 e <300
                override fun onResponse(call: Call<MediaResult?>,
                                        response: Response<MediaResult?>) {
                    if (response.isSuccessful) {
                        mediaList = MediaMapper
                                .responseToDomain(response.body()?.mediaResult)
                        //Desacopla camada de domínio da camada de rede
                        view!!.showMedia(mediaList as MutableList<Media>?)
                    } else {
                        view!!.showErrorToast(response.message())
                    }
                }

                override fun onFailure(call: Call<MediaResult?>, t: Throwable) {
                    t.message?.let { view!!.showErrorToast(it) }
                }
            })
    }

    override fun getVideos(media: Media?) {
        CommonPresenter.getVideos(media, view as MediaPlayingNowFragment)
    }

    override fun destruirView() {
        this.view = null
    }
}