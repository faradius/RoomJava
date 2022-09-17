package com.alex.roomjava.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.alex.roomjava.constants.Constants;

@Entity(tableName = Constants.NAME_TABLE_PROFESSOR_LANGUAGES,
        primaryKeys = {"profesorId", "lenguajeId"},
        foreignKeys = {
                        @ForeignKey(entity = Professor.class,
                        parentColumns = "id",
                        childColumns = "profesorId"),
                        @ForeignKey(entity = Languages.class,
                        parentColumns = "id",
                        childColumns = "lenguajeId")
        })
public class ProfessorLanguage {
    @ColumnInfo(name = "profesorId")
    public int profesorId;

    @ColumnInfo(name = "lenguajeId")
    public int lenguajeId;

    public int getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(int profesorId) {
        this.profesorId = profesorId;
    }

    public int getLanguajeId() {
        return lenguajeId;
    }

    public void setLanguajeId(int languajeId) {
        this.lenguajeId = languajeId;
    }
}
