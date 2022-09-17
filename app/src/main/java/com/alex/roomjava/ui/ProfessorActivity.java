package com.alex.roomjava.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alex.roomjava.R;
import com.alex.roomjava.db.database.AppDb;
import com.alex.roomjava.db.entity.Professor;

import java.util.ArrayList;
import java.util.List;

public class ProfessorActivity extends AppCompatActivity {

    private EditText etNombre, etEmail;
    private Button btnGuardar, btnConsultar, btnConsultarNombre, btnConsultarID, btnActualizarPorID, btnBorrarPorID, btnBorrarTodo;

    private Professor professor;
    private List<Professor> listProfessors;
    private EscribirBaseDatosTask escribirBaseDatosTask;
    private LeerBaseDatosTask leerBaseDatosTask;
    private LeerDatosPorNombre leerDatosPorNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);
        configView();
    }

    private void configView() {
        professor = new Professor();
        listProfessors = new ArrayList<>();
        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professor.setName(etNombre.getText().toString());
                professor.setEmail(etEmail.getText().toString());
                escribirBaseDatosTask = new EscribirBaseDatosTask();
                escribirBaseDatosTask.execute(professor);
            }
        });

        btnConsultar = findViewById(R.id.btnBuscar);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leerBaseDatosTask = new LeerBaseDatosTask();
                leerBaseDatosTask.execute();
            }
        });

        btnConsultarNombre = findViewById(R.id.btnBuscarNombre);
        btnConsultarNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leerDatosPorNombre = new LeerDatosPorNombre();
                leerDatosPorNombre.execute();
            }
        });

        btnConsultarID = findViewById(R.id.btnBuscarID);
        btnConsultarID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professor = AppDb.getAppDb(getApplicationContext()).professorDAO().findProfessorById(1);
                showProfessorUnit(professor);
            }
        });

        btnActualizarPorID = findViewById(R.id.btnActualizarID);
        btnActualizarPorID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Professor professor = new Professor();
                professor.setId(1);
                professor.setName("Manuel");
                professor.setEmail("manuel@gmail.com");
                AppDb.getAppDb(getApplicationContext()).professorDAO().updateProfessorById(professor);
            }
        });

        btnBorrarPorID = findViewById(R.id.btnBorrarId);
        btnBorrarPorID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Professor professor = new Professor();
                professor.setId(2);
                AppDb.getAppDb(getApplicationContext()).professorDAO().deleteProfessorById(professor);
            }
        });

        btnBorrarTodo = findViewById(R.id.btnBorrar);
        btnBorrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDb.getAppDb(getApplicationContext()).professorDAO().deleteAllProfessor();
            }
        });
    }

    private class EscribirBaseDatosTask extends AsyncTask<Professor, Void, Void>{

        @Override
        protected Void doInBackground(Professor... professors) {
            AppDb.getAppDb(getApplicationContext()).professorDAO().insertProfessor(
                professors[0]
            );
            return null;
        }
    }

    private class LeerBaseDatosTask extends AsyncTask<Void,Void,List<Professor>>{

        @Override
        protected List<Professor> doInBackground(Void... voids) {
            listProfessors = AppDb.getAppDb(getApplicationContext()).professorDAO().findAllProfessor();
            return listProfessors;
        }

        @Override //Hilo principal
        protected void onPostExecute(List<Professor> professors) {
            showProfessor(professors);
        }
    }

    private class LeerDatosPorNombre extends AsyncTask<Void, Void, Professor>{

        @Override
        protected Professor doInBackground(Void... voids) {
            professor = AppDb.getAppDb(getApplicationContext()).professorDAO().findProfessorByName(etNombre.getText().toString());
            return professor;
        }

        @Override
        protected void onPostExecute(Professor professor) {
            if (professor==null){
                Log.d("TAG", "No se encontro el resultado");
            }else {
                showProfessorUnit(professor);
            }

        }
    }

    private void showProfessorUnit(Professor professor) {
            Log.d("TAG", "Nombre: " + professor.getName() + " Email: " + professor.getEmail() + "\n");
    }

    private void showProfessor(List<Professor> professors){
        for (Professor professor:professors){
            Log.d("TAG", "ID professor: " + professor.getId() + " Nombre: " + professor.getName() + " Email: " + professor.getEmail() + "\n");
        }
    }
}