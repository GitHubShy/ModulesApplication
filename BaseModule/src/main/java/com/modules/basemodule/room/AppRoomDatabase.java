package com.modules.basemodule.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.modules.baselibrary.LogProxy;
import com.modules.basemodule.BuildConfig;
import com.modules.basemodule.room.dao.MemoryDao;
import com.modules.basemodule.room.entity.Memory;

/**
 * https://www.jianshu.com/p/bc6cc48ffa67
 */
@Database(entities = {Memory.class}, version = 1,exportSchema = false)
public abstract class AppRoomDatabase extends RoomDatabase {

    public abstract MemoryDao getMemoryDao();

    private static String DATABASE_NAME = BuildConfig.APP_DATABASE_NAME;

    private static AppRoomDatabase mAppRoomDatabase;

    public static AppRoomDatabase getInstance(Application context) {
        if (mAppRoomDatabase == null) {
            synchronized (AppRoomDatabase.class) {
                if (mAppRoomDatabase == null) {
                    mAppRoomDatabase = buildDatabase(context);
                }
            }
        }
        return mAppRoomDatabase;
    }

    private static AppRoomDatabase buildDatabase(Application context) {
        return Room.databaseBuilder(context, AppRoomDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        String databasePath = db.getPath();
                        int version = db.getVersion();
                        LogProxy.d("databasePath="+databasePath+";version="+version);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                        String databasePath = db.getPath();
                        int version = db.getVersion();
                        LogProxy.d("databasePath="+databasePath+";version="+version);
                    }

                    @Override
                    public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                        super.onDestructiveMigration(db);
                        LogProxy.d("Database Destructive");
                    }
                })
                .build();
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
