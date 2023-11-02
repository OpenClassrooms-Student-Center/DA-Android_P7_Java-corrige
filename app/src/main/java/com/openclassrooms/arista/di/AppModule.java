package com.openclassrooms.arista.di;

import android.content.Context;

import com.openclassrooms.arista.data.AppDatabase;
import com.openclassrooms.arista.data.dao.ExerciseDtoDao;
import com.openclassrooms.arista.data.dao.SleepDtoDao;
import com.openclassrooms.arista.data.dao.UserDtoDao;
import com.openclassrooms.arista.data.repository.ExerciseRepository;
import com.openclassrooms.arista.data.repository.SleepRepository;
import com.openclassrooms.arista.data.repository.UserRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {


    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(@ApplicationContext Context context) {

        return AppDatabase.getDatabase(context);
    }


    @Provides
    public UserDtoDao provideUserDao(AppDatabase appDatabase) {
        return appDatabase.userDtoDao();
    }


    @Provides
    public SleepDtoDao provideSleepDao(AppDatabase appDatabase) {
        return appDatabase.sleepDtoDao();
    }


    @Provides
    public ExerciseDtoDao provideExerciseDao(AppDatabase appDatabase) {
        return appDatabase.exerciseDtoDao();
    }


    @Provides
    @Singleton
    public UserRepository provideUserRepository(UserDtoDao userDao) {
        return new UserRepository(userDao);
    }

    @Provides
    @Singleton
    public ExecutorService provideExecutorService() {
        return Executors.newFixedThreadPool(4);
    }

    @Provides
    @Singleton
    public SleepRepository provideSleepRepository(SleepDtoDao sleepDtoDao) {
        return new SleepRepository(sleepDtoDao);
    }


    @Provides
    @Singleton
    public ExerciseRepository provideExerciseRepository(ExerciseDtoDao exerciseDtoDao) {
        return new ExerciseRepository(exerciseDtoDao, provideExecutorService());
    }
}
