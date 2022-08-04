package com.davideploy.brazilnews.ui.favorites;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.davideploy.brazilnews.data.BrazilNewsRepository;
import com.davideploy.brazilnews.domain.News;

import java.util.List;

public class FavoritesViewModel extends ViewModel {

    public FavoritesViewModel() {

    }

    public LiveData<List<News>> loadFavoriteNews() {
        return BrazilNewsRepository.getInstance().getLocalDb().newsDao().loadFavoriteNews();
    }

    public void saveNews(News news) {
        AsyncTask.execute(() -> BrazilNewsRepository.getInstance().getLocalDb().newsDao().save(news));
    }
}