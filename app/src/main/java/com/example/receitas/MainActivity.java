package com.example.receitas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Receita> receitas;
    private ReceitaAdapter adapter;
    private RequestQueue requestQueue;
    private static final String BASE_URL = "http://10.0.2.2:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        receitas = new ArrayList<>();
        receitas.add(new Receita("Bolo de Chocolate", R.drawable.bolo,
                "2 ovos, 1 xícara de leite, 2 colheres de chocolate em pó",
                "Misture tudo e asse por 40 minutos"));
        receitas.add(new Receita("Salada de Frutas", R.drawable.salada,
                "1 banana, 1 maçã, 1 laranja",
                "Corte e misture todas as frutas"));
        receitas.add(new Receita("Omelete", R.drawable.omelete,
                "2 ovos, sal a gosto, queijo",
                "Bata os ovos e frite em fogo médio"));
        receitas.add(new Receita("Panqueca", R.drawable.panqueca,
                "1 xícara de farinha, 1 ovo, 1 xícara de leite",
                "Misture e frite pequenas porções"));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ReceitaAdapter(receitas, receita -> {
            Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
            intent.putExtra("nome", receita.getNome());
            intent.putExtra("ingredientes", receita.getIngredientes());
            intent.putExtra("preparo", receita.getPreparo());
            startActivity(intent);
        }, this);

        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);

        buscarReceitasDoBackend();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_favoritos) {
                startActivity(new Intent(MainActivity.this, FavoritosActivity.class));
                return true;
            }
            return false;
        });
    }

    private void buscarReceitasDoBackend() {
        String url = BASE_URL + "/receitas";

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject receitaJson = response.getJSONObject(i);

                            String nome = receitaJson.getString("nome");
                            String ingredientes = receitaJson.getString("ingredientes");
                            String preparo = receitaJson.getString("modoPreparo");

                            boolean existe = false;
                            for (Receita r : receitas) {
                                if (r.getNome().equalsIgnoreCase(nome)) {
                                    existe = true;
                                    break;
                                }
                            }

                            if (!existe) {
                               
                                Receita receita = new Receita(nome, 0, ingredientes, preparo);
                                receitas.add(receita);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                },
                error -> Toast.makeText(this, "Erro ao carregar receitas do servidor", Toast.LENGTH_SHORT).show()
        );

        requestQueue.add(request);
    }
}
