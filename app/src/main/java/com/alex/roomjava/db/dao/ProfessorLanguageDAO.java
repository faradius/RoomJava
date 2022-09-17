package com.alex.roomjava.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alex.roomjava.db.entity.Languages;
import com.alex.roomjava.db.entity.Professor;
import com.alex.roomjava.db.entity.ProfessorLanguage;

import java.util.List;

@Dao
public interface ProfessorLanguageDAO {
    @Insert
    void insert(ProfessorLanguage professorLanguage);

    @Query("SELECT * FROM professor INNER JOIN professorlanguages ON professor.id = professorlanguages.profesorId WHERE professorlanguages.lenguajeId = :leguajeId")
    List<Professor> getProfessorForRepository(int leguajeId);

    @Query("SELECT * FROM languages INNER JOIN professorlanguages ON languages.id = professorlanguages.lenguajeId WHERE professorlanguages.profesorId = :profesorId")
    List<Languages> getLanguagesForRepository(int profesorId);
}
