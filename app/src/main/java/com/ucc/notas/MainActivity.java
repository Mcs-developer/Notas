package com.ucc.notas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;
    public TextView nota_corte_1,nota_corte_2,nota_corte_3;
    public TextView semestre1,semestre2,semestre3;
    public EditText parcial_1, trabajo_1, taller_1;
    public EditText parcial_2, trabajo_2, taller_2;
    public EditText parcial_3, trabajo_3, taller_3;
    public double semestre, count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("C1");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("C2");
        host.addTab(spec);

        //Tab 3
        spec = host.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("C3");
        host.addTab(spec);

        nota_corte_1 = (TextView)findViewById(R.id.nota_corte_1);
        nota_corte_2 = (TextView)findViewById(R.id.nota_corte_2);
        nota_corte_3 = (TextView)findViewById(R.id.nota_corte_3);
        semestre1 = (TextView)findViewById(R.id.semestre1);
        semestre2 = (TextView)findViewById(R.id.semestre2);
        semestre3 = (TextView)findViewById(R.id.semestre3);

        parcial_1 = (EditText)findViewById(R.id.parcial_1);
        parcial_2 = (EditText)findViewById(R.id.parcial_2);
        parcial_3 = (EditText)findViewById(R.id.parcial_3);

        trabajo_1 = (EditText)findViewById(R.id.trabajo_1);
        trabajo_2 = (EditText)findViewById(R.id.trabajo_2);
        trabajo_3 = (EditText)findViewById(R.id.trabajo_3);

        taller_1 = (EditText)findViewById(R.id.taller_1);
        taller_2 = (EditText)findViewById(R.id.taller_2);
        taller_3 = (EditText)findViewById(R.id.taller_3);
    }

    public void corte1(View v){

        double parcial  = Double.parseDouble(parcial_1.getText().toString());
        double taller   = Double.parseDouble(taller_1.getText().toString());
        double trabajo  = Double.parseDouble(trabajo_1.getText().toString());
        double nota_taller_trabajo, nota_corte;

        parcial = parcial * 0.5;
        nota_taller_trabajo  = ((taller + trabajo)/2)*0.5;

        nota_corte = parcial + nota_taller_trabajo;

        nota_corte_1.setText("Nota corte 1: " + nota_corte);

        semestre(nota_corte*0.3);
    }

    public void corte2(View v){

        double parcial  = Double.parseDouble(parcial_2.getText().toString());
        double taller   = Double.parseDouble(taller_2.getText().toString());
        double trabajo  = Double.parseDouble(trabajo_2.getText().toString());
        double nota_taller_trabajo, nota_corte;

        parcial = parcial * 0.5;
        nota_taller_trabajo  = ((taller + trabajo)/2)*0.5;

        nota_corte = parcial + nota_taller_trabajo;

        nota_corte_2.setText("Nota corte 1: " + nota_corte);

        semestre(nota_corte*0.3);
    }

    public void corte3(View v){

        double parcial  = Double.parseDouble(parcial_3.getText().toString());
        double taller   = Double.parseDouble(taller_3.getText().toString());
        double trabajo  = Double.parseDouble(trabajo_3.getText().toString());
        double nota_taller_trabajo, nota_corte;

        parcial = parcial * 0.5;
        nota_taller_trabajo  = ((taller + trabajo)/2)*0.5;

        nota_corte = parcial + nota_taller_trabajo;

        nota_corte_3.setText("Nota corte 1: " + nota_corte);

        semestre(nota_corte*0.4);
    }

    public void semestre(double a){

        count++;

        if(count<=3) {
            semestre = semestre + a;

            semestre1.setText("Nota semestre: " + semestre);
            semestre2.setText("Nota semestre: " + semestre);
            semestre3.setText("Nota semestre: " + semestre);
        }

    }


}
