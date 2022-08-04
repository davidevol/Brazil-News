package com.davideploy.brazilnews.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.davideploy.brazilnews.domain.News;

@Database(entities = {News.class}, version = 1)
public abstract class BrazilNewsDb extends RoomDatabase {
    public abstract NewsDao newsDao();
}
