package br.com.appservicecontact.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import br.com.appservicecontact.R;
import br.com.appservicecontact.fragment.ClientesFragment;
import br.com.appservicecontact.fragment.ContatoFragment;
import br.com.appservicecontact.fragment.PrincipalFragment;
import br.com.appservicecontact.fragment.ServicosFragment;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //para carregar o fragmento de tela principal
        chamarFragmento(new PrincipalFragment());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                */
                enviarEmail();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_principal) {

            chamarFragmento(new PrincipalFragment());

        } else if (id == R.id.nav_servicos) {

            chamarFragmento(new ServicosFragment());

        } else if (id == R.id.nav_clientes) {

           chamarFragmento(new ClientesFragment());

        } else if (id == R.id.nav_contato) {

            chamarFragmento(new ContatoFragment());

        } else if (id == R.id.nav_sobre) {

            startActivity(new Intent(this, SobreActivity.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void enviarEmail(){
        Intent email = new Intent( Intent.ACTION_SEND );
        email.putExtra(Intent.EXTRA_EMAIL, new String[] {"rayner.kayo.tec@gmail.com"});

        //assunto
        email.putExtra(Intent.EXTRA_SUBJECT, new String[] {"Contato pelo APP"});

        //titulo
        email.putExtra(Intent.EXTRA_TITLE, new String[] {"Titulo"});

        //Texto do email.
        email.putExtra(Intent.EXTRA_TEXT, new String[]{"Olá, vimos que você entrou em contato, em breve retornaremos"});

        //configurar para abrir app de email que estão instalados no celular
        email.setType("message/rfc822");

        //quando chama paar escolher, eu passo o email a ser enviado e mando o
        //usuário escolher qual app de email.
        startActivity( Intent.createChooser(email, "Escolha o app de email") );

    }

    private void chamarFragmento(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.commit();
    }

}
