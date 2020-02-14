package ericambiel.com.br.smartimdb.ui.mediaplayingnow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import ericambiel.com.br.smartimdb.data.mapper.MediaMapper
import ericambiel.com.br.smartimdb.data.network.RetrofitConfig
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.DiscoveryCall
import ericambiel.com.br.smartimdb.data.network.responseCallTMDB.MediaResult
import ericambiel.com.br.smartimdb.domain.Media
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MediaPlayingNowViewModel (savedStateHandle: SavedStateHandle): ViewModel() {

    val mediaList: MutableLiveData<List<Media>> by lazy { MutableLiveData<List<Media>>() }
    val message: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    //val media : Media? = savedStateHandle["media"] //savedStateHandle é usado para trazer dados do Fragmento para ViewModel

    private val discoveryCall: DiscoveryCall = DiscoveryCall()

    fun loadMedia() {
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
                    if (response.isSuccessful)
                        mediaList.value = MediaMapper.responseToDomain(response.body()?.mediaResult)
                    else
                        message.value = response.message()
                }

                override fun onFailure(call: Call<MediaResult?>, t: Throwable) {
                    message.value = t.message
                }
            })
    }
}