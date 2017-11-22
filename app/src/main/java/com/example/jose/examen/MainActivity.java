package com.example.jose.examen;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Botonera.OnFragmentInteractionListener, DadesPersonalsFragment.OnFragmentInteractionListener, DesaDades, AficionsFragment.OnFragmentInteractionListener{

    private static final String FRAGMENT_AFICIONS = "fragmentAficions";
    private static final int SUB_ACTIVITY_INFO = 2;
    String nom;
    String edad;
    String sexe;
    String aficio;
    String rating;

    private static final String FRAGMENT_DADES = "fragmentDades";
    Button btnDadesPersonals;
    Button btnAficions;
    ImageButton btnInformacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Iniciacions
        btnDadesPersonals = (Button) findViewById(R.id.btn_dades_personals);
        btnAficions = (Button) findViewById(R.id.btn_aficions);
        btnInformacio = (ImageButton) findViewById(R.id.btn_info);



        //Listeners
        btnDadesPersonals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_DADES);
                if(fragment == null){
                    deleteFragmentAficions();
                    fragment = DadesPersonalsFragment.newInstance("","");
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment, FRAGMENT_DADES).addToBackStack(FRAGMENT_DADES).commit();
                }
            }
        });

        btnAficions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_AFICIONS);
                if(fragment == null){
                    deleteFragmentDades();
                    fragment = AficionsFragment.newInstance("","");
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment, FRAGMENT_AFICIONS).addToBackStack(FRAGMENT_AFICIONS).commit();
                }
            }
        });

        btnInformacio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),InfoActivity.class);
                i.putExtra("nom",nom);
                i.putExtra("edad",edad);
                i.putExtra("sexe",sexe);
                i.putExtra("aficio",aficio);
                i.putExtra("rating", rating);
                startActivityForResult(i,SUB_ACTIVITY_INFO);
            }
        });

    }

    private void deleteFragmentDades() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_DADES);
        if(fragment != null){
            fragmentManager.beginTransaction().remove(fragment).commit();
            fragmentManager.popBackStack();
        }
    }


    private void deleteFragmentAficions() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(FRAGMENT_AFICIONS);
        if(fragment != null){
            fragmentManager.beginTransaction().remove(fragment).commit();
            fragmentManager.popBackStack();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void desaDadesPersonals(String nom, String edad, String sexe) {
        this.nom = nom;
        this.sexe = sexe;
        this.edad = edad;
        Toast.makeText(getApplicationContext(),"Nom: "+nom+" Sexe: "+sexe+" Edad: "+edad, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void desaDadesAficions(String aficio, String rating) {
        this.aficio = aficio;
        this.rating = rating;
        Toast.makeText(getApplicationContext(),"Aficio: "+aficio+" Rating: "+rating, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2: switch(resultCode){
                case RESULT_OK: deleteFragmentAficions();
                                deleteFragmentDades();
                    break;
            }
            break;
        }
    }
}

