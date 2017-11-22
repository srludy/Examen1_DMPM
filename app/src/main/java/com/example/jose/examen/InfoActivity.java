package com.example.jose.examen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {

    TextView nom;
    TextView edad;
    TextView sexe;
    TextView rating;
    TextView preferencies;
    Button btnTancar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Iniciacions
        nom = (TextView) findViewById(R.id.viewNom);
        edad = (TextView) findViewById(R.id.viewEdad);
        sexe = (TextView) findViewById(R.id.viewSexe);
        rating = (TextView) findViewById(R.id.viewPuntuacio);
        preferencies = (TextView) findViewById(R.id.viewLectures);
        btnTancar = (Button) findViewById(R.id.btnTancar);

        getInformacio();

        btnTancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    private void getInformacio() {
        Bundle bundle = getIntent().getExtras();
        nom.setText(bundle.getString("nom"));
        edad.setText(bundle.getString("edad"));
        sexe.setText(bundle.getString("sexe"));
        rating.setText(bundle.getString("rating"));
        preferencies.setText(bundle.getString("aficio"));

    }
}
