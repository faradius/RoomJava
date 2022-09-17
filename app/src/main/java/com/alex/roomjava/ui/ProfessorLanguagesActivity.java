package com.alex.roomjava.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alex.roomjava.R;
import com.alex.roomjava.db.database.AppDb;
import com.alex.roomjava.db.entity.Languages;
import com.alex.roomjava.db.entity.Professor;
import com.alex.roomjava.db.entity.ProfessorLanguage;

import java.util.ArrayList;
import java.util.List;

public class ProfessorLanguagesActivity extends AppCompatActivity {

    private EditText etIdProfesor, etIdLenguaje;
    private Button btnGuardar, btnGetProfessor, btnGetLenguaje;

    private ProfessorLanguage professorLanguage;
    private List<Professor> professors;
    private List<Languages> languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_languages);
        configView();
    }

    private void configView() {
        professors = new ArrayList<>();
        languages = new ArrayList<>();
        professorLanguage = new ProfessorLanguage();


        etIdProfesor = findViewById(R.id.etProfessorIDLenguajes);
        etIdLenguaje = findViewById(R.id.etLenguajesIDLenguajes);

        btnGuardar = findViewById(R.id.btnProfessorLenguajesGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorLanguage.setLanguajeId(Integer.parseInt(etIdLenguaje.getText().toString()));
                professorLanguage.setProfesorId(Integer.parseInt(etIdProfesor.getText().toString()));

                AppDb.getAppDb(getApplicationContext()).professorLanguageDAO().insert(professorLanguage);
            }
        });

        btnGetProfessor = findViewById(R.id.btnProfessorLenguajesConsultar);
        btnGetProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professors = AppDb.getAppDb(getApplicationContext()).professorLanguageDAO().getProfessorForRepository(1);
                for (Professor professor: professors){
                    Log.d("TAG", "Nombre del profesor: " + professor.getName() + "\n");
                }
            }
        });

        btnGetLenguaje = findViewById(R.id.btnLenguajesLenguajesConsultar);
        btnGetLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages = AppDb.getAppDb(getApplicationContext()).professorLanguageDAO().getLanguagesForRepository(3);
                for (Languages languages: languages){
                    Log.d("TAG", "Nombre de lenguaje de Programación: " + languages.getName() + "\n");
                }
            }
        });
    }
}