package com.openclassrooms.arista.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.openclassrooms.arista.data.dao.ExerciseDtoDao;
import com.openclassrooms.arista.data.dao.SleepDtoDao;
import com.openclassrooms.arista.data.dao.UserDtoDao;
import com.openclassrooms.arista.data.entity.ExerciseDto;
import com.openclassrooms.arista.data.entity.SleepDto;
import com.openclassrooms.arista.data.entity.UserDto;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {UserDto.class, SleepDto.class, ExerciseDto.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDtoDao userDtoDao();
    public abstract SleepDtoDao sleepDtoDao();
    public abstract ExerciseDtoDao exerciseDtoDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static class AppDatabaseCallback extends RoomDatabase.Callback {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                SleepDtoDao sleepDao = INSTANCE.sleepDtoDao();
                UserDtoDao userDtoDao = INSTANCE.userDtoDao();

                // Implement your database population logic here
                // Example:
                sleepDao.insertSleep(new SleepDto(System.currentTimeMillis(), 480, 4));
                sleepDao.insertSleep(new SleepDto(System.currentTimeMillis() - 1000 * 60 * 60 * 24, 450, 3));
                userDtoDao.insertUser(new UserDto(1,"virgile","lol@xd.com", "testtest"));
            });
        }
    }

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "AristaDB")
                            .addCallback(new AppDatabaseCallback())
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

