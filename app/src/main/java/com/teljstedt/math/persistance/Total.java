package com.teljstedt.math.persistance;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "uid", childColumns = "uId", onDelete = 1))
public class Total {
    @PrimaryKey
    public final int id;
    public final int uid;
    public String week;
    public Integer cntTotalWeek;
    public Integer points;

    public Total(final int id, final int uid, String week, Integer cntTotalWeek, Integer points) {
        this.id = id;
        this.uid = uid;
        this.week = week;
        this.cntTotalWeek = cntTotalWeek;
        this.points = points;
    }
//    public HashMap<Operator,Integer> mapCntWrong;
    //    public HashMap<Operator,Integer> mapCntCorrect;
//    public HashMap<Operator,Integer> mapCntQuiz;
}
