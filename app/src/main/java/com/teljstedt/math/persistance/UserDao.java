package com.teljstedt.math.persistance;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);
    @Update
    void update(User... users);
    @Delete
    void delete(User... users);
    @Query("SELECT * FROM user")
    List<User> getAllUsers();
    @Query("SELECT * from user where uid=:uid")
    User findUser(final int uid);
}
