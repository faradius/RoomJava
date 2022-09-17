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

import java.util.ArrayList;
import java.util.List;

public class LanguagesActivity extends AppCompatActivity {

    private EditText etNombreCursoLenguaje;
    private Button btnGuardarLenguaje, btnConsultarLenguaje, btnActualizarLenguaje, btnBorrarLenguaje;

    private Languages languages;
    private List<Languages> languagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        configView();
    }

    private void configView() {
        languages = new Languages();
        languagesList = new ArrayList<>();

        etNombreCursoLenguaje = findViewById(R.id.etNombreCursoLenguaje);
        btnGuardarLenguaje = findViewById(R.id.btnGuardarLenguaje);

        btnGuardarLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages.setName(etNombreCursoLenguaje.getText().toString());
                AppDb.getAppDb(getApplicationContext()).languagesDAO().insertLanguages(languages);
            }
        });

        btnConsultarLenguaje = findViewById(R.id.btnConsultarLenguajes);
        btnConsultarLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languagesList = AppDb.getAppDb(getApplicationContext()).languagesDAO().findAllLanguages();
                for (Languages languages: languagesList){
                    Log.d("TAG", "id: " + languages.getId() + " Nombre " + languages.getName());
                }
            }
        });

        btnActualizarLenguaje = findViewById(R.id.btnActualizarLenguaje);
        btnActualizarLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages.setId(1);
                languages.setName("Java 8");
                AppDb.getAppDb(getApplicationContext()).languagesDAO().updateLanguagesById(languages);
            }
        });

        btnBorrarLenguaje = findViewById(R.id.btnBorrarLenguaje);
        btnBorrarLenguaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages.setId(2);
                AppDb.getAppDb(getApplicationContext()).languagesDAO().deleteLanguagesById(languages);
            }
        });
    }
}