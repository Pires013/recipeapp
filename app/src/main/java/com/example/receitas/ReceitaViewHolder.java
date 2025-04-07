package com.example.receitas;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ReceitaViewHolder extends RecyclerView.ViewHolder {

    private final TextView textNome;
    private final ImageView imageReceita;
    private final ImageButton btnFavorito;

    public ReceitaViewHolder(View itemView) {
        super(itemView);
        textNome = itemView.findViewById(R.id.textNome);
        imageReceita = itemView.findViewById(R.id.imageReceita);
        btnFavorito = itemView.findViewById(R.id.btnFavorito);
    }

    public void bind(final Receita receita, final ReceitaAdapter.OnItemClickListener listener, final Context context) {
        textNome.setText(receita.getNome());
        imageReceita.setImageResource(receita.getImagem());

        SharedPreferences prefs = context.getSharedPreferences("favoritos", Context.MODE_PRIVATE);
        boolean favoritoSalvo = prefs.getBoolean(receita.getNome(), false);
        receita.setFavorito(favoritoSalvo);

        btnFavorito.setImageResource(favoritoSalvo ? R.drawable.ic_favorito_on : R.drawable.ic_favorito_off);

        btnFavorito.setOnClickListener(view -> {
            boolean novoEstado = !receita.isFavorito();
            receita.setFavorito(novoEstado);
            btnFavorito.setImageResource(novoEstado ? R.drawable.ic_favorito_on : R.drawable.ic_favorito_off);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(receita.getNome(), novoEstado);
            editor.apply();
        });

        itemView.setOnClickListener(view -> listener.onItemClick(receita));
    }
}
