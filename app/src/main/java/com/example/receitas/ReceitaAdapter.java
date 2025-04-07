package com.example.receitas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReceitaAdapter extends RecyclerView.Adapter<ReceitaViewHolder> {
    private final List<Receita> receitas;
    private final OnItemClickListener listener;
    private final Context context;

    public interface OnItemClickListener {
        void onItemClick(Receita receita);
    }

    public ReceitaAdapter(List<Receita> receitas, OnItemClickListener listener, Context context) {
        this.receitas = receitas;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receita, parent, false);
        return new ReceitaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewHolder holder, int position) {
        holder.bind(receitas.get(position), listener, context);
    }

    @Override
    public int getItemCount() {
        return receitas.size();
    }
}
