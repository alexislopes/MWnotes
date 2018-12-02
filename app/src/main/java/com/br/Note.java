package com.br;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

//import com.br.DAO.CORE.NotaDAOMySQLite;
import com.br.note.mw.mwnotes.R;
import com.br.Modelo.Nota;
import com.br.Modelo.Usuario;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Note extends AppCompatActivity implements Runnable{

    private Button btnSalvar, btnDeletar, btnAtualizar, achar;
    private EditText codigoInput, tituloInput, conteudoInput, donoInput;
    private Nota nota;
    private Usuario usuario;
    private String usuarioJson;


    private ListView listaNotas;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    private Handler handler = new Handler();
    private ProgressDialog janela;
    private Thread tarefa;

    Intent pegaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        ligaJavaXML();

        pegaInfo = getIntent();
        usuarioJson = pegaInfo.getStringExtra("usuario");
        usuario = usuario.fromJson(usuarioJson);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                janela = new ProgressDialog(Note.this);
                janela.setMessage("Salvando Nota");
                janela.show();
                tarefa = new Thread(Note.this);
                tarefa.start();

            }
        });
    }

    public void ligaJavaXML(){
        btnSalvar = findViewById(R.id.btnDone);
        btnDeletar = findViewById(R.id.btnDel);
        btnAtualizar = findViewById(R.id.btnUpdate);
        achar = findViewById(R.id.btnFind);

        codigoInput = findViewById(R.id.codigoField);
        tituloInput = findViewById(R.id.tagField);
        conteudoInput = findViewById(R.id.noteField);
        donoInput = findViewById(R.id.usuarioField);

        listaNotas = findViewById(R.id.listNotas);
    }



    @Override
    public void run() {
        WSCliente wsCliente = new WSCliente();

        if (!String.valueOf(tituloInput.getText()).equals("") && !String.valueOf(conteudoInput.getText()).equals("")) {
            nota = new Nota(tituloInput.getText().toString(), conteudoInput.getText().toString(), usuario.getId());
            try {
                final boolean resposta = wsCliente.insereNota(nota.toJson());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(resposta){
                            Toast.makeText(Note.this, "Nota Cadastrada com Sucesso!", Toast.LENGTH_SHORT).show();
                            tituloInput.setText("");
                            conteudoInput.setText("");
                        } else {
                            Toast.makeText(Note.this, "Erro ao salvar nota!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } finally {
                janela.dismiss();
            }

        } else {
            Toast.makeText(this, "Valores Inválidos", Toast.LENGTH_SHORT).show();
        }

    }
}
