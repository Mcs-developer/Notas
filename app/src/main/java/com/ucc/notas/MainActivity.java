package com.ucc.notas;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
//test cesar from dev branch
    TabHost tabHost;
    public TextView nota_corte_1, nota_corte_2, nota_corte_3, nota_definitiva, textUserName;
    public EditText parcial_1, trabajo_1, taller_1;
    public EditText parcial_2, trabajo_2, taller_2;
    public EditText parcial_3, trabajo_3, taller_3;
    public double n1, n2, n3;
// test to commits
//test Telegram Notify
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recibir nombre de usuario que se ingresa en el Acitivity Login
        Bundle bundle = getIntent().getExtras();
        String userName = bundle.getString("user");
        textUserName = (TextView)findViewById(R.id.userName);
        textUserName.setText("Bienvenido " + userName);

        // Declaracion del Tab host para almacenar los 3 tabs de las notas
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

        nota_corte_1 = (TextView) findViewById(R.id.nota_corte_1);
        nota_corte_2 = (TextView) findViewById(R.id.nota_corte_2);
        nota_corte_3 = (TextView) findViewById(R.id.nota_corte_3);


        parcial_1 = (EditText) findViewById(R.id.parcial_1);
        parcial_2 = (EditText) findViewById(R.id.parcial_2);
        parcial_3 = (EditText) findViewById(R.id.parcial_3);

        trabajo_1 = (EditText) findViewById(R.id.trabajo_1);
        trabajo_2 = (EditText) findViewById(R.id.trabajo_2);
        trabajo_3 = (EditText) findViewById(R.id.trabajo_3);

        taller_1 = (EditText) findViewById(R.id.taller_1);
        taller_2 = (EditText) findViewById(R.id.taller_2);
        taller_3 = (EditText) findViewById(R.id.taller_3);

        nota_definitiva = (TextView) findViewById(R.id.nota_definitiva);
    }

    public void calcularNotaCorte(View v) {
        int idView = v.getId();
        String msj = "";
        double parcial;
        double taller;
        double trabajo;
        double nota_taller_trabajo;
        try {
            switch (idView) {
                case R.id.button:

                    parcial = Double.parseDouble(parcial_1.getText().toString());
                    taller = Double.parseDouble(taller_1.getText().toString());
                    trabajo = Double.parseDouble(trabajo_1.getText().toString());

                    if(!validarNota(parcial)) {
                        Toast.makeText(this, "Verifique la nota del Parcial",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else if(!validarNota(taller)){
                        Toast.makeText(this,"Verifique la nota del taller", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else if(!validarNota(trabajo)){
                        Toast.makeText(this,"Verifique la nota del trabajo", Toast.LENGTH_SHORT).show();
                        break;
                    }


                    parcial = parcial * 0.5;
                    nota_taller_trabajo = ((taller + trabajo) / 2) * 0.5;

                    n1 = parcial + nota_taller_trabajo;

                    nota_corte_1.setText("Nota corte 1: " + n1);


                    break;
                case R.id.button_corte2:
                    parcial = Double.parseDouble(parcial_2.getText().toString());
                    taller = Double.parseDouble(taller_2.getText().toString());
                    trabajo = Double.parseDouble(trabajo_2.getText().toString());

                    if(!validarNota(parcial)) {
                        Toast.makeText(this, "Verifique la nota del Parcial",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else if(!validarNota(taller)){
                        Toast.makeText(this,"Verifique la nota del taller", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else if(!validarNota(trabajo)){
                        Toast.makeText(this,"Verifique la nota del trabajo", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    parcial = parcial * 0.5;
                    nota_taller_trabajo = ((taller + trabajo) / 2) * 0.5;

                    n2 = parcial + nota_taller_trabajo;

                    nota_corte_2.setText("Nota corte 2: " + n2);

                    break;
                case R.id.button_corte3:
                    parcial = Double.parseDouble(parcial_3.getText().toString());
                    taller = Double.parseDouble(taller_3.getText().toString());
                    trabajo = Double.parseDouble(trabajo_3.getText().toString());


                    if(!validarNota(parcial)) {
                        Toast.makeText(this, "Verifique la nota del Parcial",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else if(!validarNota(taller)){
                        Toast.makeText(this,"Verifique la nota del taller", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else if(!validarNota(trabajo)){
                        Toast.makeText(this,"Verifique la nota del trabajo", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    parcial = parcial * 0.5;
                    nota_taller_trabajo = ((taller + trabajo) / 2) * 0.5;

                    n3 = parcial + nota_taller_trabajo;

                    nota_corte_3.setText("Nota corte 3: " + n3);

                    break;
            }

            calcularDefinitiva();

        } catch (NumberFormatException ex) {
            Toast.makeText(this, "Verifique que un campo no es un número", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validarNota(double n){
        return  n >= 0 && n <= 5;
    }


    public void calcularDefinitiva() {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        double total = (n1 * 0.3) + (n2 * 0.3) + (n3 * 0.4);

        nota_definitiva.setText("Nota Definitiva: " + df.format(total));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Determinar la opcion seleccionada
        Intent signOut;


        switch (item.getItemId()){
            case R.id.signOut:
               signOut = new Intent(this, Login.class);
                startActivity(signOut);
                finish();
                break;
        }
        return true;
    }


}
