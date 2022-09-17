package com.alex.roomjava.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alex.roomjava.R;

public class MainActivity extends AppCompatActivity {

    private Button btnProfessor, btnCursos, btnLenguajes, btnProfesorLenguajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configView();
    }

    private void configView(){
        btnProfessor = findViewById(R.id.btnProfessor);
        btnProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfessorActivity.class));
            }
        });

        btnCursos = findViewById(R.id.btnCursos);
        btnCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CursoActivity.class));
            }
        });

        btnLenguajes = findViewById(R.id.btnLenguajes);
        btnLenguajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LanguagesActivity.class));
            }
        });

        btnProfesorLenguajes = findViewById(R.id.btnProfessorLenguajes);
        btnProfesorLenguajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfessorLanguagesActivity.class));
            }
        });
    }
}