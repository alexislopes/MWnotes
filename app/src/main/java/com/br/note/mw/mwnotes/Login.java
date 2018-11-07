package com.br.note.mw.mwnotes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.br.note.mw.mwnotes.Aplicacao;
import com.br.note.mw.mwnotes.WSCliente;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Login extends AppCompatActivity implements Runnable {

  Button btnLogin;
  EditText nameInput, passwordInput;
  Handler handler = new Handler();
  ProgressDialog janela;
  //ProgressBar barra;
  Thread tarefa;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ligaJavaXML();


    btnLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        janela = new ProgressDialog(Login.this);
        //barra = new ProgressBar(Login.this);
        janela.setMessage("Verificando os Dados do Servidor");
        janela.show();
        tarefa = new Thread(Login.this);
        tarefa.start();
      }
    });
  }

  public void ligaJavaXML() {
    btnLogin = (Button) findViewById(R.id.btnLogin);
    nameInput = (EditText) findViewById(R.id.nameInput);
    passwordInput = (EditText) findViewById(R.id.passwordInput);
  }

  @Override
  public void run() {
    WSCliente cliente = new WSCliente();
    try {
      final boolean resposta = cliente.verificaUsuario(nameInput.getText().toString(), passwordInput.getText().toString());
      handler.post(new Runnable() {
        @Override
        public void run() {
          if(resposta){
            Intent principal = new Intent(Login.this, Aplicacao.class);
            principal.putExtra("usuario", nameInput.getText().toString());
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
