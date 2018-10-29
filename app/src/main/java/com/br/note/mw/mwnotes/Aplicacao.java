package com.br.note.mw.mwnotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.widget.Button;


import com.br.note.mw.mwnotes.com.br.mw.fragments.*;

public class Aplicacao extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static Button salvar;
    /*private Button salvar, deletar, atualizar, achar, muda;
    private EditText codigo, tag, nota, usuario;
    private Nota nova;

    private ListView listaNotas;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    NotaDAOMySQLite db = new NotaDAOMySQLite();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "In developing", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    FragmentManager fm = getSupportFragmentManager();
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment frag = null;
        if (id == R.id.nav_note) {
            /*FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainFragment, new NoteFrag());
            ft.commit();*/
            Intent intent = new Intent(this, NoteActivity.class);
            startActivity(intent);
            //nota();
        } else if (id == R.id.nav_blocks) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainFragment, new BlockFrag());
            ft.commit();
        } else if (id == R.id.nav_tag) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainFragment, new TagFrag());
            ft.commit();
        } else if (id == R.id.nav_manage) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainFragment, new SettingFrag());
            ft.commit();
        } else if (id == R.id.nav_share) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainFragment, new ShareFrag());
            ft.commit();
        } else if (id == R.id.nav_send) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.mainFragment, new SendFrag());
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*protected void nota(){
        salvar = findViewById(R.id.btnDone);
        deletar = findViewById(R.id.btnDel);
        atualizar = findViewById(R.id.btnUpdate);
        achar = findViewById(R.id.btnFind);
        muda = findViewById(R.id.muda);

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

        muda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.home);
            }
        });

    }
    public void listaNotas() {
        List<Nota> notas = db.achaTodos();

        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(Aplicacao.this, android.R.layout.simple_list_item_1, arrayList);

        listaNotas.setAdapter(arrayAdapter);

        for (Nota nota : notas) {
            //Log.d("Lista", "\nID: " + c.getCodigo() + " Nome: " + c.getNome());
            arrayList.add(nota.getCodigo() + " - " + nota.getTag());
            arrayAdapter.notifyDataSetChanged();
        }
    }*/

}
