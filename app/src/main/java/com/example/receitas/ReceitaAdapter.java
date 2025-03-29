package com.example.receitas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaViewHolder> {
    private final List<Receita> receitas;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Receita receita);
    }

    public ReceitaAdapter(List<Receita> receitas, OnItemClickListener listener) {
        this.receitas = receitas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receita, parent, false);
        return new ReceitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewHolder holder, int position) {
        holder.bind(receitas.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return receitas.size();
    }
}
