package com.teljstedt.math.persistance;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import java.util.List;

@Dao
public interface TotalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Total total);
    @Update
    void update(Total... totals);
    @Delete
    void delete(Total... totals);

    @Query("DELETE FROM total")
    void deleteAll();

    @Query("DELETE FROM total WHERE uid=:uid")
    void deleteAllUid(final int uid);

    @Query("SELECT * FROM total ORDER BY uid ASC, week ASC")
    LiveData<List<Total>> getAllTotals();

    @Query("SELECT * from total WHERE uid=:uid  ORDER BY week ASC")
    LiveData<List<Total>> findTotalsForUser(final int uid);
    @Query("SELECT * from total WHERE uid=:uid and week=:week")
    LiveData<Total> findTotalForUserWeek(final int uid, String week);
    @Query("SELECT * FROM total WHERE id=:id")
    LiveData<Total> findTotal(final int id);
}
