package com.teljstedt.math.persistance;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);
    @Update
    void update(User... users);
    @Delete
    void delete(User... users);

    @Query("DELETE FROM user")
    void deleteAll();
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();
    @Query("SELECT * from user where uid=:uid")
    LiveData<User> getUserByUid(final int uid);

    @Query("SELECT * from user where name=:name")
    LiveData<User> getUserByName(final String name);
}
