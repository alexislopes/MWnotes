package com.br.note.mw.mwnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.br.note.mw.mwnotes.com.br.mw.DAO.CORE.NotaDAOMySQLite;
import com.br.note.mw.mwnotes.com.br.mw.Modelo.Nota;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    private Button salvar, deletar, atualizar, achar;
    private EditText codigo, tag, nota, usuario;
    private Nota nova;

    private ListView listaNotas;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    NotaDAOMySQLite db = new NotaDAOMySQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        salvar = findViewById(R.id.btnDone);
        deletar = findViewById(R.id.btnDel);
        atualizar = findViewById(R.id.btnUpdate);
        achar = findViewById(R.id.btnFind);

        codigo = findViewById(R.id.codigoField);
        tag = findViewById(R.id.tagField);
        nota = findViewById(R.id.noteField);
        usuario = findViewById(R.id.usuarioField);

        listaNotas = findViewById(R.id.listNotas);

        listaNotas();

        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String conteudo = (String) listaNotas.getItemAtPosition(i);
                String id = conteudo.substring(0, conteudo.indexOf(" -"));

                Nota achada = db.achaNotaPorCodigo(Integer.parseInt(id));

                codigo.setText(String.valueOf(achada.getCodigo()));
                tag.setText(String.valueOf(achada.getTag()));
                nota.setText(String.valueOf(achada.getNota()));
                usuario.setText(String.valueOf(achada.getUsuario()));

            }
        });


        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!String.valueOf(tag.getText()).equals("") && !String.valueOf(nota.getText()).equals("")) {

                    nova = new Nota(String.valueOf(tag.getText()), String.valueOf(nota.getText()));
                    db.insereNota(nova);

                    listaNotas();

                    codigo.setText("");
                    tag.setText("");
                    nota.setText("");
                    usuario.setText("");
                }
            }
        });

        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deletaNota(String.valueOf(tag.getText()));

                listaNotas();

                codigo.setText("");
                tag.setText("");
                nota.setText("");
                usuario.setText("");
            }
        });

        atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(String.valueOf(codigo.getText()));
                int user = Integer.parseInt(String.valueOf(usuario.getText()));
                db.atualizaNota(id, String.valueOf(tag.getText()), String.valueOf(nota.getText()), user);

                listaNotas();

                codigo.setText("");
                tag.setText("");
                nota.setText("");
                usuario.setText("");
            }
        });
    }

    public void listaNotas() {
        List<Nota> notas = db.achaTodos();

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(NoteActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listaNotas.setAdapter(arrayAdapter);

        for (Nota nota : notas) {
            //Log.d("Lista", "\nID: " + c.getCodigo() + " Nome: " + c.getNome());
            arrayList.add(nota.getCodigo() + " - " + nota.getTag());
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
