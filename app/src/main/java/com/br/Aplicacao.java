package com.br;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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


import com.br.note.mw.mwnotes.R;
import com.br.fragments.*;

public class Aplicacao extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Button salvar;
    public FloatingActionButton fab;
    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ligaJavaXML();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mudarTela = new Intent(Aplicacao.this, Note.class);
                startActivity(mudarTela);
            }
        });

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void ligaJavaXML(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

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
            Intent intent = new Intent(Aplicacao.this, Note.class);
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
}
