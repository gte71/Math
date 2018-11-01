package com.teljstedt.math.persistance;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class AppRepository {
    private TotalDao totalDao;
    private UserDao userDao;
    private LiveData<List<User>> liveDataListUserAll;
    private LiveData<List<Total>> liveDataListTotalAll;
    private LiveData<Total> liveDataTotalForWeek;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        totalDao = db.getTotalDao();
        userDao = db.getUserDao();
        liveDataListUserAll = userDao.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() {
        return liveDataListUserAll;
    }

    LiveData<User> getUser(String name) {
        return userDao.getUserByName(name);
    }

    public void insert(User user) {
        new insertUserAsyncTask(userDao).execute(user);
    }

    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao mAsyncTaskDao;

        insertUserAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
