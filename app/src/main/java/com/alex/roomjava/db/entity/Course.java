package com.alex.roomjava.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.alex.roomjava.constants.Constants;

@Entity(tableName = Constants.NAME_TABLE_COURSE,
        foreignKeys = @ForeignKey(entity = Professor.class,
        parentColumns = "id", //Id de la tabla professor
        childColumns = "professorId", //El id del professor que va estar guardada en esta tabla
        onDelete = CASCADE))
public class Course {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "duration")
    public String duration;
    @ColumnInfo(name = "professorId")
    public int professorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
}


