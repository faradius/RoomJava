package com.alex.roomjava.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alex.roomjava.db.entity.Languages;

import java.util.List;

@Dao
public interface LanguagesDAO {

    @Insert
    void insertLanguages(Languages languages);

    @Query("SELECT * FROM languages")
    List<Languages> findAllLanguages();

    @Update
    void updateLanguagesById(Languages languages);

    @Delete
    void deleteLanguagesById(Languages languages);
}
