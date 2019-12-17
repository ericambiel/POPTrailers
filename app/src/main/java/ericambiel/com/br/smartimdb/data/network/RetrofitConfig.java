package ericambiel.com.br.smartimdb.data.network;

import ericambiel.com.br.smartimdb.config.Keys;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitConfig {

    /**
     * Cosntante _INSTANCETMDB do tipo TMDBFilmeService
     */
    private static TMDBFilmeService _INSTANCETMDB;
//    private static final int REQUEST_TIMEOUT = 1;
//    private static OkHttpClient okHttpClient;

    /**
     * Cria uma instância de TMDBFilmeService.
     * @return Retorna nova instância de um objeto do tipo TMDBFilmeService.
     */
    public static TMDBFilmeService getInstanceTMDB() {
//        if (okHttpClient == null)
//            initOkHttp();
        if (_INSTANCETMDB == null) {
            Retrofit retrofit = new Retrofit.Builder()
//                    .client(okHttpClient)
                    .baseUrl(Keys.BASE_URL_TMDB)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();

            _INSTANCETMDB = retrofit.create(TMDBFilmeService.class);

            return _INSTANCETMDB;
        }
        return _INSTANCETMDB;
    }

//    private static void initOkHttp() {
//        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
//                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
//                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
//                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
//        okHttpClient = httpClient.build();
//    }
}

