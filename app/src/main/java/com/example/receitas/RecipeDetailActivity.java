package com.example.receitas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String nome = getIntent().getStringExtra("nome");
        String ingredientes = getIntent().getStringExtra("ingredientes");
        String preparo = getIntent().getStringExtra("preparo");

        TextView textNome = findViewById(R.id.textNome);
        TextView textIngredientes = findViewById(R.id.textIngredientes);
        TextView textPreparo = findViewById(R.id.textPreparo);

        textNome.setText(nome);
        textIngredientes.setText("Ingredientes: \n" + ingredientes);
        textPreparo.setText("Modo de Preparo: \n" + preparo);
    }
}
