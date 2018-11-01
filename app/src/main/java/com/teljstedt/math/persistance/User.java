package com.teljstedt.math.persistance;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public final int uid;
    @NonNull
    public String name;
    public int totalPoints;

    public User(final int uid, String name, int totalPoints) {
        this.uid = uid;
        this.name = name;
        this.totalPoints = totalPoints;
    }
}
