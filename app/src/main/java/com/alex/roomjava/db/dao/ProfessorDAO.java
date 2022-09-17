package com.alex.roomjava.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alex.roomjava.db.entity.Professor;

import java.util.List;

@Dao
public interface ProfessorDAO {
   @Insert
    void insertProfessor(Professor professor);

   @Query("SELECT * FROM professor")
    List<Professor> findAllProfessor();

   @Query("SELECT * FROM professor WHERE name LIKE :name")
    Professor findProfessorByName(String name);

    @Query("SELECT * FROM professor WHERE id LIKE :id")
    Professor findProfessorById(int id);

   @Update
    void updateProfessorById(Professor professor);

   @Query("DELETE FROM professor")
    void deleteAllProfessor();

   @Delete
    void deleteProfessorById(Professor professor);
}
