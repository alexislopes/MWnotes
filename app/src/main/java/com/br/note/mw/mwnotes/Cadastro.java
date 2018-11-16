package com.br.note.mw.mwnotes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.note.mw.mwnotes.com.br.mw.Modelo.Usuario;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Cadastro extends AppCompatActivity implements Runnable {

    EditText nomeInput, usuarioInput, senhaInput;
    Button btnCadastro;
    Handler handler = new Handler();
    ProgressDialog janela;
    Thread tarefa;
    Usuario usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        ligaJavaXML();


        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                janela = new ProgressDialog(Cadastro.this);
                janela.setMessage("Cadastrando Usuário");
                janela.show();
                tarefa = new Thread(Cadastro.this);
                tarefa.start();
            }
        });

    }

    public void ligaJavaXML(){
        nomeInput = findViewById(R.id.cadastroNomeInput);
        usuarioInput = findViewById(R.id.cadastroUsuarioInput);
        senhaInput = findViewById(R.id.cadastroSenhaInput);
        btnCadastro = findViewById(R.id.cadastroBtnCadastro);
    }

    @Override
    public void run() {
        WSCliente wsCliente = new WSCliente();
        usuario = new Usuario(nomeInput.getText().toString(), usuarioInput.getText().toString(), senhaInput.getText().toString());
        final String usuarioToJson = usuario.toJson();

        try {
            final boolean resposta = wsCliente.insereUsuario(usuarioToJson);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(resposta){
                        Toast.makeText(Cadastro.this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                        Intent mudaTela = new Intent(Cadastro.this, Aplicacao.class);
                        mudaTela.putExtra("usuario", usuarioToJson);
                        startActivity(mudaTela);
                    } else {
                        Toast.makeText(Cadastro.this, "Erro ao cadastar", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            janela.dismiss();
        }
    }
}
