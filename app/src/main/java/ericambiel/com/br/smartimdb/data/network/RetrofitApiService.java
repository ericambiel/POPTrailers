package ericambiel.com.br.smartimdb.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitApiService {

    //Cosntante _INSTANCE do tipo TMDBFilmeService
    private static TMDBFilmeService _INSTANCE;

    //Metodo getInstace que retorna um TMDBFilmeService
    public static TMDBFilmeService getInstance() {
        if (_INSTANCE == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();

            _INSTANCE = retrofit.create(TMDBFilmeService.class);

            return _INSTANCE;
        }
        return _INSTANCE;
    }
}

