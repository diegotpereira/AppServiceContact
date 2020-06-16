package br.com.appservicecontact.activity;


import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import br.com.appservicecontact.R;
import mehdi.sakout.aboutpage.AboutPage;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //criando a view a partir daqui da activity, usando a bilioteca
        String descricao = "Olá, seja bem vindo ao nosso App! ";

        View sobre = new AboutPage(this)
                .setImage(R.drawable.logo)
                .setDescription( descricao + " " )
                .addGroup("Nossas Redes Sociais!")
                .addEmail("diegoteixeirapereira@gmail.com", "Envie seu email!")
                .addWebsite("https://github.com/diegotpereira", "Desenvolvimento de aplicações")
                .addGroup("Redes sociais")
                .addFacebook("facebook","Acesse aqui")
                .create();

        setContentView(sobre);

    }
}
