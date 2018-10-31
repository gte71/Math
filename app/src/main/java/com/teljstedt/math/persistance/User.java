package com.teljstedt.math.persistance;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public final int uid;
    public String name;

    public User(final int uid, String name) {
        this.uid = uid;
        this.name = name;
    }
}
