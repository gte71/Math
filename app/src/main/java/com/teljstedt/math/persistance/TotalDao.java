package com.teljstedt.math.persistance;

import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface TotalDao {
    @Insert
    void insert(Total total);
    @Update
    void update(Total... totals);
    @Delete
    void delete(Total... totals);
    @Query("SELECT * FROM total")
    List<Total> getAllTotals();
    @Query("SELECT * from total WHERE uid=:uid")
    List<Total> findTotalsForUser(final int uid);
    @Query("SELECT * from total WHERE uid=:uid and week=:week")
    List<Total> findTotalForUserWeek(final int uid,String week);
    @Query("SELECT * FROM total WHERE id=:id")
    Total findTotal(final int id);
}
