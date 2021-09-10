package com.jitendra.countryasia.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jitendra.countryasia.dao.CountryDao;
import com.jitendra.countryasia.entity.EntityData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {EntityData.class}, version = 1, exportSchema = false)
public abstract class CountryRoomDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();

    public static volatile CountryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static CountryRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null) {
            synchronized (CountryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountryRoomDatabase.class, "country_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                CountryDao countryDao = INSTANCE.countryDao();
                countryDao.deleteAllCountries();
            });
        }
    };


}
