package com.br;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import butterknife.BindView;

import com.seatgeek.placesautocomplete.DetailsCallback;
import com.seatgeek.placesautocomplete.OnPlaceSelectedListener;
import com.seatgeek.placesautocomplete.PlacesAutocompleteTextView;
import com.seatgeek.placesautocomplete.model.AddressComponent;
import com.seatgeek.placesautocomplete.model.AddressComponentType;
import com.seatgeek.placesautocomplete.model.Place;
import com.seatgeek.placesautocomplete.model.PlaceDetails;


public class Note extends AppCompatActivity implements Runnable {

    private Button btnSalvar;
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

    private Intent pegaInfo;

    WSCliente wsCliente;

    PlacesAutocompleteTextView mAutocomplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        wsCliente = new WSCliente();

        ligaJavaXML();

        /*mAutocomplete.setOnPlaceSelectedListener(new OnPlaceSelectedListener() {
            @Override
            public void onPlaceSelected(final Place place) {
                mAutocomplete.getDetailsFor(place, new DetailsCallback() {
                    @Override
                    public void onSuccess(final PlaceDetails details) {
                        Log.d("test", "details " + details);
                        //mStreet.setText(details.name);
                        for (AddressComponent component : details.address_components) {
                            for (AddressComponentType type : component.types) {
                                switch (type) {
                                    case STREET_NUMBER:
                                        break;
                                    case ROUTE:
                                        break;
                                    case NEIGHBORHOOD:
                                        break;
                                    case SUBLOCALITY_LEVEL_1:
                                        break;
                                    case SUBLOCALITY:
                                        break;
                                    case LOCALITY:
                                        //mCity.setText(component.long_name);
                                        break;
                                    case ADMINISTRATIVE_AREA_LEVEL_1:
                                        //mState.setText(component.short_name);
                                        break;
                                    case ADMINISTRATIVE_AREA_LEVEL_2:
                                        break;
                                    case COUNTRY:
                                        break;
                                    case POSTAL_CODE:
                                        //mZip.setText(component.long_name);
                                        break;
                                    case POLITICAL:
                                        break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(final Throwable failure) {
                        Log.d("test", "failure " + failure);
                    }
                });
            }
        });*/

        pegaInfo = getIntent();
        usuarioJson = pegaInfo.getStringExtra("usuario");
        System.out.println("Usuário passado: " + usuarioJson);
        String usuariopego = null;


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

    public void ligaJavaXML() {
        btnSalvar = findViewById(R.id.btnDone);
        //btnDeletar = findViewById(R.id.btnDel);
        //btnAtualizar = findViewById(R.id.btnUpdate);
        //achar = findViewById(R.id.btnFind);

        //codigoInput = findViewById(R.id.codigoField);
        tituloInput = findViewById(R.id.tagField);
        conteudoInput = findViewById(R.id.noteField);
        //donoInput = findViewById(R.id.usuarioField);

        listaNotas = findViewById(R.id.listNotas);

        mAutocomplete = findViewById(R.id.autocomplete);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_hide_x) {
            mAutocomplete.showClearButton(false);
        }
        if (id == R.id.action_show_x) {
            mAutocomplete.showClearButton(true);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void run() {


        try {
            String usuarioachado = wsCliente.achaUsuarioPorUsuario(usuarioJson);
            System.out.println("é nulo? -> " + usuarioachado.isEmpty());
            System.out.println("Usuário achado: " + usuarioachado);
            //usuario = usuario.fromJson(usuarioachado);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }




        if (!String.valueOf(tituloInput.getText()).equals("") && !String.valueOf(conteudoInput.getText()).equals("")) {
            nota = new Nota(tituloInput.getText().toString(), conteudoInput.getText().toString(), mAutocomplete.getText().toString());
            try {
                final boolean resposta = wsCliente.insereNota(nota.toJson());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (resposta) {
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
