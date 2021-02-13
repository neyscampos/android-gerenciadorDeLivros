package com.example.gerenciadordelivros.views;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gerenciadordelivros.R;
import com.example.gerenciadordelivros.data.LivroDAO;
import com.example.gerenciadordelivros.domain.Livro;

public class EditarLivroActivity extends AppCompatActivity {

    private EditText edt_titulo;
    private EditText edt_autor;
    private EditText edt_editora;
    private CheckBox chk_emprestado;
    private LivroDAO livroDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livro);

        edt_titulo = findViewById(R.id.edt_titulo);
        edt_autor = findViewById(R.id.edt_autor);
        edt_editora = findViewById(R.id.edt_editora);
        chk_emprestado = findViewById(R.id.check_emprestado);

        livroDAO = LivroDAO.getInstance(this);
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void processar(View view) {
        Livro livro = new Livro(edt_titulo.getText().toString(),
                edt_autor.getText().toString(),
                edt_editora.getText().toString(),
                (chk_emprestado.isChecked() ? 1 : 0));

        livroDAO.save(livro);
        //Toast.
        setResult(RESULT_OK);
        finish();
    }
}