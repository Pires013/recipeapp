package com.example.receitas;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Receita> receitas = new ArrayList<>();
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
        });

        recyclerView.setAdapter(adapter);
    }
}
