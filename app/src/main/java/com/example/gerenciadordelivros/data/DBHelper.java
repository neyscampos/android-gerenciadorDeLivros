package com.example.gerenciadordelivros.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.example.gerenciadordelivros.domain.Livro;

public class DBHelper extends SQLiteOpenHelper {
    public static final String BD_NAME = "Livrosbd";
    public static final int BD_VERSION = 1;

    private static DBHelper instance;

    private static final String SQL_CREATE = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL)",
            LivroContract.TABLE_NAME, LivroContract.Columns._ID, LivroContract.Columns.titulo,
            LivroContract.Columns.autor, LivroContract.Columns.editora, LivroContract.Columns.emprestado);

    private static final String SQL_DROP = String.format("DROP TABLE IF EXISTS %s ", LivroContract.TABLE_NAME);

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context);
        }
        return instance;
    }

    private DBHelper(@Nullable Context context) {
        super(context, BD_NAME, null, BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
