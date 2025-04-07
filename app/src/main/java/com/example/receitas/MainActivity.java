package com.example.receitas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Receita> receitas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        receitas = new ArrayList<>();
        receitas.add(new Receita("Bolo de Chocolate", R.drawable.bolo, "2 ovos, 1 xícara de leite, 2 colheres de chocolate em pó", "Misture tudo e asse por 40 minutos"));
        receitas.add(new Receita("Salada de Frutas", R.drawable.salada, "1 banana, 1 maçã, 1 laranja", "Corte e misture todas as frutas"));
        receitas.add(new Receita("Omelete", R.drawable.omelete, "2 ovos, sal a gosto, queijo", "Bata os ovos e frite em fogo médio"));
        receitas.add(new Receita("Panqueca", R.drawable.panqueca, "1 xícara de farinha, 1 ovo, 1 xícara de leite", "Misture e frite pequenas porções"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ReceitaAdapter adapter = new ReceitaAdapter(receitas, receita -> {
            Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
            intent.putExtra("nome", receita.getNome());
            intent.putExtra("ingredientes", receita.getIngredientes());
            intent.putExtra("preparo", receita.getPreparo());
            startActivity(intent);
        }, this); 

        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_favoritos) {
            Intent intent = new Intent(MainActivity.this, FavoritosActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
