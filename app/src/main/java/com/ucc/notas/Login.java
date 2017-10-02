package com.ucc.notas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText user, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.editUser);
        password = (EditText)findViewById(R.id.editPassword);

    }

    public void sign(View v){
        if(!user.getText().toString().equals("") && !password.getText().toString().equals("")){
            Bundle myBundle = new Bundle();
            myBundle.putString("user", user.getText().toString());
            Intent mainActivity = new Intent(this, MainActivity.class);
            mainActivity.putExtras(myBundle);
            startActivity(mainActivity);
            finish();
        }
        else{
            Toast.makeText(this, "Usuario o contraseña vacios", Toast.LENGTH_SHORT).show();
        }

    }
}
