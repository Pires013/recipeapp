package com.example.receitas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    private List<Receita> getTodasReceitas() {
        List<Receita> receitas = new ArrayList<>();
        receitas.add(new Receita("Bolo de Chocolate", R.drawable.bolo, "...", "..."));
        receitas.add(new Receita("Salada de Frutas", R.drawable.salada, "...", "..."));
        receitas.add(new Receita("Omelete", R.drawable.omelete, "...", "..."));
        receitas.add(new Receita("Panqueca", R.drawable.panqueca, "...", "..."));
        return receitas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        RecyclerView recyclerView = findViewById(R.id.recyclerFavoritos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences prefs = getSharedPreferences("favoritos", MODE_PRIVATE);
        List<Receita> favoritas = new ArrayList<>();

        for (Receita receita : getTodasReceitas()) {
            if (prefs.getBoolean(receita.getNome(), false)) {
                receita.setFavorito(true);
                favoritas.add(receita);
            }
        }

        if (favoritas.isEmpty()) {
            Toast.makeText(this, "Nenhuma receita favorita encontrada.", Toast.LENGTH_SHORT).show();
        }

        ReceitaAdapter adapter = new ReceitaAdapter(favoritas, receita -> {
            Intent intent = new Intent(FavoritosActivity.this, RecipeDetailActivity.class);
            intent.putExtra("nome", receita.getNome());
            intent.putExtra("ingredientes", receita.getIngredientes());
            intent.putExtra("preparo", receita.getPreparo());
            startActivity(intent);
        }, this);

        recyclerView.setAdapter(adapter);
    }
}
