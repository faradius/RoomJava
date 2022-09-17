package com.alex.roomjava.db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.alex.roomjava.constants.Constants;
import com.alex.roomjava.db.dao.CourseDAO;
import com.alex.roomjava.db.dao.LanguagesDAO;
import com.alex.roomjava.db.dao.ProfessorDAO;
import com.alex.roomjava.db.dao.ProfessorLanguageDAO;
import com.alex.roomjava.db.entity.Course;
import com.alex.roomjava.db.entity.Languages;
import com.alex.roomjava.db.entity.Professor;
import com.alex.roomjava.db.entity.ProfessorLanguage;

@Database(entities = {Professor.class, Course.class, Languages.class, ProfessorLanguage.class}, version = 4)
public abstract class AppDb extends RoomDatabase {
    private static AppDb INSTANCE;
    public abstract ProfessorDAO professorDAO();
    public abstract CourseDAO courseDAO();
    public abstract LanguagesDAO languagesDAO();
    public abstract ProfessorLanguageDAO professorLanguageDAO();

    public static AppDb getAppDb(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDb.class, Constants.NAME_DATABASE)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .addMigrations(MIGRATION_2_3)
                    .addMigrations(MIGRATION_3_4)
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE curso (id INTEGER PRIMARY KEY NOT NULL, name TEXT, duration TEXT, professorId INTEGER NOT NULL, FOREIGN KEY (professorId) REFERENCES professor(id) ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE languages (id INTEGER PRIMARY KEY NOT NULL, name TEXT)");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE professorlanguages (profesorId INTEGER NOT NULL, lenguajeId INTEGER NOT NULL, PRIMARY KEY (profesorId, lenguajeId), FOREIGN KEY (profesorId) REFERENCES professor(id), FOREIGN KEY (lenguajeId) REFERENCES languages(id) )");
        }
    };
}
