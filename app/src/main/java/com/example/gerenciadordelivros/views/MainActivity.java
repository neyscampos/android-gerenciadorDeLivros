package com.example.gerenciadordelivros.views;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.adapter.LivroAdapter;
import com.example.gerenciadordelivros.data.LivroDAO;
import com.example.gerenciadordelivros.domain.Livro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LivroDAO livroDAO;
    private LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager((this)));

        livroDAO = LivroDAO.getInstance(this);

/*
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro(1L,"Android para Leigos","Michael Burton","Alta books",0));
        livros.add(new Livro(2L,"Android para Programadores","Paul J, Deitel","Bookman",1));
        livros.add(new Livro(3L,"Desenvolvimento para Android","Griffiths, David","Alta books",0));
        livros.add(new Livro(4L,"Android Base de Dados","Queirós, Ricardo","FCA Editora",1));
        livros.add(new Livro(5L,"Android em Ação","King, Chris","Elsevier - Campus",0));
        livros.add(new Livro(6L,"Jogos em Android","Queirós, Ricardo","FCA - Editora",1));
        livros.add(new Livro(7L,"Android Essencial com Kotlin","Ricardo R.","NOVATEC",0));
*/
        livroAdapter = new LivroAdapter(livroDAO.list(), this);
        recyclerView.setAdapter(livroAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_adicionar:
                Intent intent = new Intent(getApplicationContext(), EditarLivroActivity.class);
                startActivityForResult(intent, 100);
                return true;
            case R.id.action_sair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK){
            atualizaListaLivros();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void atualizaListaLivros(){
        List<Livro> livros = livroDAO.list();
        livroAdapter.setItems(livros);
        livroAdapter.notifyDataSetChanged();
    }
}