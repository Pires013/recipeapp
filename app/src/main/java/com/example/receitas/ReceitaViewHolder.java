package com.example.receitas;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ReceitaViewHolder extends RecyclerView.ViewHolder {

    public ReceitaViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(final Receita receita, final ReceitaAdapter.OnItemClickListener listener) {
        TextView textNome = itemView.findViewById(R.id.textNome);
        ImageView imageReceita = itemView.findViewById(R.id.imageReceita);

        textNome.setText(receita.getNome());
        imageReceita.setImageResource(receita.getImagem());

        itemView.setOnClickListener(view -> listener.onItemClick(receita));
    }
}
