package com.example.gerenciadordelivros.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.domain.Livro;

import java.util.List;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroHolder> {

    private List<Livro> livros;
    private Context context;

    public LivroAdapter(List<Livro> livros, Context context) {
        this.livros = livros;
        this.context = context;
    }

    @NonNull
    @Override
    public LivroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_livro, parent, false);
        LivroHolder livroHolder = new LivroHolder(view);
        return livroHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LivroHolder holder, int position) {
        Livro livro = livros.get(position);
        holder.txtTitulo.setText(livro.getTitulo());
        holder.txtAutor.setText(livro.getAutor());
        holder.txtEditora.setText(livro.getEditora());

        if (livro.isEmprestado()) {
            holder.ic_livro.setColorFilter(Color.GRAY);
            holder.ic_start.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    public void setItems(List<Livro> livros){
        this.livros = livros;
    }

    public class LivroHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtTitulo;
        public TextView txtEditora;
        public TextView txtAutor;
        public ImageView ic_livro;
        public ImageView ic_start;

        public LivroHolder(View view) {
            super(view);

            txtTitulo = view.findViewById(R.id.txtTitulo);
            txtAutor = view.findViewById(R.id.txtAutor);
            txtEditora = view.findViewById(R.id.txtEditora);
            ic_livro = view.findViewById(R.id.ic_livro);
            ic_start = view.findViewById(R.id.ic_start);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Toast.makeText(context, "OnClick " + pos, Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            int pos = getAdapterPosition();

            Toast.makeText(context, "OnLongClick " + pos, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}
