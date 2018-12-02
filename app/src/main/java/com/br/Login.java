package com.br;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.note.mw.mwnotes.R;
import com.br.Modelo.Usuario;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Login extends AppCompatActivity implements Runnable {

  Button btnLogin, btnCadastrar;
  EditText nameInput, passwordInput;
  Handler handler = new Handler();
  ProgressDialog janela;
  Thread tarefa;
  Usuario usuario;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ligaJavaXML();


    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        janela = new ProgressDialog(Login.this);
        janela.setMessage("Verificando os Dados do Servidor");
        janela.show();
        tarefa = new Thread(Login.this);
        tarefa.start();
      }
    });

    btnCadastrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent mudaTela = new Intent(Login.this, Cadastro.class);
            startActivity(mudaTela);
        }
    });
  }

  public void ligaJavaXML() {
    btnLogin = findViewById(R.id.btnLogin);
    btnCadastrar = findViewById(R.id.btnCadastrar);
    nameInput =  findViewById(R.id.usuarioInput);
    passwordInput = findViewById(R.id.senhaInput);
  }

  @Override
  public void run() {
    WSCliente cliente = new WSCliente();
    usuario = new Usuario(nameInput.getText().toString(), passwordInput.getText().toString());
    final String usuarioToJson = usuario.toJson();
    System.out.println(usuarioToJson);

    try {
      final boolean resposta = cliente.verificaUsuario(usuarioToJson);
      handler.post(new Runnable() {
        @Override
        public void run() {
          if(resposta){
            Intent principal = new Intent(Login.this, Aplicacao.class);
            principal.putExtra("usuario", usuarioToJson);
            startActivity(principal);
          }else {
            Toast.makeText(Login.this, "Login ou Senha inválidos", Toast.LENGTH_SHORT).show();
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
