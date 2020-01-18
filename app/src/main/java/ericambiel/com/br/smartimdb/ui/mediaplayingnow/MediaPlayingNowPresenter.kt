package ericambiel.com.br.smartimdb.ui.mediaplayingnow

import ericambiel.com.br.smartimdb.data.mapper.MediaMapper
import ericambiel.com.br.smartimdb.data.network.RetrofitConfig
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.DiscoveryCall
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.MediaResult
import ericambiel.com.br.smartimdb.domain.Media
import ericambiel.com.br.smartimdb.ui.MediaContrato
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MediaPlayingNowPresenter(view: MediaPlayingNowFragment) : MediaContrato.Presenter {
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destruirView() {
        this.view = null
    }
}