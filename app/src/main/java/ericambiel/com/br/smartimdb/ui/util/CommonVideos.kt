package ericambiel.com.br.smartimdb.ui.util

import ericambiel.com.br.smartimdb.config.Keys
import ericambiel.com.br.smartimdb.data.mapper.VideosFilmeMapper.Companion.responseToDomain
import ericambiel.com.br.smartimdb.data.network.RetrofitConfig
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.VideoResponse
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.VideosResult
import ericambiel.com.br.smartimdb.domain.Media
import ericambiel.com.br.smartimdb.domain.Video
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommonVideos {



    fun getVideos(media: Media) : List<Video>? {
        RetrofitConfig
                .getInstanceTMDB()
                .getMediaVideos(
                        media.id,
                        Keys.KEY_TMDB)
                .enqueue(object : Callback<VideosResult?> {
                    override fun onResponse(call: Call<VideosResult?>,
                                            response: Response<VideosResult?>) { // status code >= 200 e <300
                        if (response.isSuccessful) {
                            var videoList: List<Video>? = responseToDomain(
                                    response.body()?.resultadosVideos as List<VideoResponse>)
                        } else {
                          //  view.mostraErro(response.message())
                        }
                    }

                    override fun onFailure(call: Call<VideosResult?>,
                                           t: Throwable) {
                      //  view.mostraErro(t.message)
                    }
                })
        return null
    }
}