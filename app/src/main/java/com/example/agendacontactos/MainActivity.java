package com.example.agendacontactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
RecyclerView cRecyclerView;
ContactoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cRecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cRecyclerView.setLayoutManager(linearLayoutManager);

        //pintamos una linea divisora entre cada item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        cRecyclerView.addItemDecoration(dividerItemDecoration);

        cRecyclerView.setHasFixedSize(true);//permitimos que todos los items sean del mismo tamaño
        adapter=new ContactoAdapter(crearContacto(), this);
        cRecyclerView.setAdapter(adapter);
    }

    public List<Contacto> crearContacto(){
        return new ArrayList<Contacto>(){
            {
                add(new Contacto("Ricardo Hdez", "453123123", "Las palmas", "richard@gmial.com"));
                add(new Contacto("Gil", "453123547", "Las lomas", "gil@gmial.com"));
                add(new Contacto("Fermin", "453582123", "lazaro", "fermin@gmial.com"));
                add(new Contacto("Andrea", "453157823", "Las lomas", "pupi@gmial.com"));
                add(new Contacto("Maria Jose", "411823123", "El Ransho", "marijo@gmial.com"));
            }
        };
    }

    //importamos un menu en la barra superior
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnAdd:
                Intent intentNuevoContacto = new Intent(this, AgregarContacto.class);
                startActivity(intentNuevoContacto);

        }
        return super.onOptionsItemSelected(item);
    }
}