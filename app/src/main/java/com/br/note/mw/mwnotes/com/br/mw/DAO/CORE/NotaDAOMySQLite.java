package com.br.note.mw.mwnotes.com.br.mw.DAO.CORE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.br.note.mw.mwnotes.com.br.mw.DAO.API.NotaDAO;
import com.br.note.mw.mwnotes.com.br.mw.Modelo.Nota;

import java.util.ArrayList;
import java.util.List;

public class NotaDAOMySQLite extends SQLiteOpenHelper implements NotaDAO {

    private static final int VERSAO = 1;
    private static final String BANCO = "nicenotin";

    private static final String TABELA = "notas";
    private static final String CODIGO = "codigo";
    private static final String TAG = "tag";
    private static final String NOTA = "nota";
    private static final String USUARIO = "usuario";


    public NotaDAOMySQLite(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABELA + "("
                + CODIGO + " INTEGER PRIMARY KEY,"
                + TAG + " TEXT,"
                + NOTA + " TEXT,"
                + USUARIO + " INTEGER"
                + ")";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public boolean insereNota(Nota nota) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TAG, nota.getTag());
        values.put(NOTA, nota.getNota());
        values.put(USUARIO, nota.getUsuario());

        db.insert(TABELA, null, values);
        db.close();

        return verificaNota(nota);
    }

    @Override
    public Nota achaNotaPorUsuario(String usuario) {
        throw new UnsupportedOperationException("NÃO CODIFICADO");
    }

    @Override
    public Nota achaNotaPorTag(String tag) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABELA, new String[]{CODIGO, TAG, NOTA, USUARIO}, TAG + " = ?", new String[]{tag}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new Nota(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
    }

    @Override
    public List<Nota> achaTodos() {
        List<Nota> todasNotas = new ArrayList<>();

        String query = "SELECT * FROM " + TABELA;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Nota nota = new Nota();
                nota.setCodigo(Integer.parseInt(cursor.getString(0)));
                nota.setTag(cursor.getString(1));
                nota.setNota(cursor.getString(2));
                nota.setUsuario(Integer.parseInt(cursor.getString(3)));

                todasNotas.add(nota);
            } while (cursor.moveToNext());
        }

        return todasNotas;
    }

    @Override
    public boolean atualizaNota(int codigo, String tag, String nota, int usuario) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TAG, tag);
        values.put(NOTA, nota);
        values.put(USUARIO, usuario);

        db.update(TABELA, values, CODIGO + " = ?", new String[]{String.valueOf(codigo)});

        return true;
    }

    @Override
    public boolean verificaNota(Nota nota) {
        String identificador = nota.getTag();
        List todasNotas = achaTodos();

        return todasNotas.contains(identificador);
    }

    @Override
    public boolean deletaNota(String tag) {

        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABELA, TAG + " = ?", new String[]{tag});
        db.close();

        return true;
    }

    @Override
    public Nota achaNotaPorCodigo(int codigo) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABELA, new String[]{CODIGO, TAG, NOTA, USUARIO}, CODIGO + " = ?", new String[]{String.valueOf(codigo)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return new Nota(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
    }


}
