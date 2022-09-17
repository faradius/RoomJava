package com.alex.roomjava.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.alex.roomjava.db.entity.Course;

import java.util.List;

@Dao
public interface CourseDAO {
    @Insert
    void insert(Course course);

    @Query("SELECT * FROM curso WHERE professorId=:professorId")
    List<Course> findCoursesForProfessor(int professorId);

    @Update
    void updateCourseByID(Course course);

    @Delete
    void deleteCourseByID(Course course);
}
