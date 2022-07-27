package com.davideploy.brazilnews.ui.news;

import android.icu.text.UnicodeSetIterator;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.davideploy.brazilnews.R;
import com.davideploy.brazilnews.data.remote.SoccerNewsApi;
import com.davideploy.brazilnews.databinding.ActivityMainBinding;
import com.davideploy.brazilnews.domain.News;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsApi api;
    private ActivityMainBinding binding;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://83rafa.github.io/soccer-news-api/")
                .baseUrl("https://davideploy.github.io/matches-simulator-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(SoccerNewsApi.class);
        this.findNews();
    }

    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                }else {
                    //TODO estratégia de erros
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                //TODO estratégia de erros

            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
}