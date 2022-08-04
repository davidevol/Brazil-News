package com.davideploy.brazilnews.data;

import androidx.room.Room;

import com.davideploy.brazilnews.App;
import com.davideploy.brazilnews.data.local.BrazilNewsDb;
import com.davideploy.brazilnews.data.remote.BrazilNewsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BrazilNewsRepository {

    //region Constantes
    private static final String REMOTE_API_URL = "https://davideploy.github.io/matches-simulator-api/";
    private static final String LOCAL_DB_NAME = "brazil-news";
    //endregion

    //region Atributos: encapsulam o acesso a nossa API (Retrofit) e banco de dados local (Room).
    private BrazilNewsApi remoteApi;
    private BrazilNewsDb localDb;

    public BrazilNewsApi getRemoteApi() {
        return remoteApi;
    }

    public BrazilNewsDb getLocalDb() {
        return localDb;
    }
    //endregion

    //region Singleton: garante uma instância única dos atributos relacionados ao Retrofit e Room.
    private BrazilNewsRepository() {
        remoteApi = new Retrofit.Builder()
                .baseUrl(REMOTE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BrazilNewsApi.class);

        localDb = Room.databaseBuilder(App.getInstance(), BrazilNewsDb.class, LOCAL_DB_NAME).build();
    }

    public static BrazilNewsRepository getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final BrazilNewsRepository INSTANCE = new BrazilNewsRepository();
    }
    //endregion
}
