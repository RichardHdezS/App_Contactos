package com.example.agendacontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AgregarContacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
    }

    public void descarta(View view) {
        Intent intentAllcontactos = new Intent(this, MainActivity.class);
        startActivity(intentAllcontactos);
        setTitle("Agregar Contacto");
    }
}