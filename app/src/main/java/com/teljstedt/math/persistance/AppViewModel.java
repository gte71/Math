package com.teljstedt.math.persistance;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository appRepository;
    private LiveData<List<User>> liveDataListUser;
    private LiveData<User> liveDataUser;

    public AppViewModel(@NonNull Application application) {
        super(application);
        appRepository = new AppRepository(application);
        liveDataListUser = appRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return liveDataListUser;
    }

    public LiveData<User> getUser(String name) {
        liveDataUser = appRepository.getUser(name);
        return liveDataUser;
    }

    public void insert(User user) {
        appRepository.insert(user);
    }
}
