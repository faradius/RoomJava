package com.alex.roomjava.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alex.roomjava.R;
import com.alex.roomjava.db.database.AppDb;
import com.alex.roomjava.db.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CursoActivity extends AppCompatActivity {

    private EditText etIdProfessor, etNombreCurso, etDuracionCurso;
    private Button btnGuardar, btnConsultar, btnActualizar, btnBorrar;
    private Course course;
    private List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);
        configView();
    }

    private void configView() {
        course = new Course();
        courseList = new ArrayList<>();
        etIdProfessor = findViewById(R.id.etIdProfesor);
        etNombreCurso = findViewById(R.id.etNombreCurso);
        etDuracionCurso = findViewById(R.id.etDuracionCurso);

        btnGuardar = findViewById(R.id.btnGuardarCurso);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setDuration(etDuracionCurso.getText().toString());
                course.setName(etNombreCurso.getText().toString());
                course.setProfessorId(Integer.parseInt(etIdProfessor.getText().toString()));
                AppDb.getAppDb(getApplicationContext()).courseDAO().insert(course);
            }
        });
        btnConsultar = findViewById(R.id.btnConsultarCurso);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseList = AppDb.getAppDb(getApplicationContext()).courseDAO().findCoursesForProfessor(Integer.parseInt(etIdProfessor.getText().toString()));
                for (Course course: courseList){
                    Log.d("TAG", "id: "+ course.getId() + " Nombre: " + course.getName() + " Duracion " + course.getDuration() + "idProfessor " +course.getProfessorId());
                }
            }
        });

        btnActualizar = findViewById(R.id.btnActualizarCurso);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setId(2);
                course.setDuration("10 horas");
                course.setName("Java");
                course.setProfessorId(3);
                AppDb.getAppDb(getApplicationContext()).courseDAO().updateCourseByID(course);
            }
        });

        btnBorrar = findViewById(R.id.btnBorrarCurso);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course.setId(1);
                AppDb.getAppDb(getApplicationContext()).courseDAO().deleteCourseByID(course);
            }
        });
    }
}