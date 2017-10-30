package com.ucc.notas;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ucc.data.Database;

public class Login extends AppCompatActivity {

    EditText user, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText) findViewById(R.id.editUser);
        password = (EditText) findViewById(R.id.editPassword);

    }

    public void sign(View v) {
        if (!user.getText().toString().equals("") && !password.getText().toString().equals("")) {
            Database conectDb = new Database(this, "mydb", null, 3);
            SQLiteDatabase getDataDb = conectDb.getReadableDatabase();

            String userName = user.getText().toString();
            String userPassword = password.getText().toString();
            if (getDataDb == null) {
                Toast.makeText(this, "Error al consultar la data", Toast.LENGTH_LONG).show();
            } else {
                Cursor cursor = getDataDb.rawQuery("Select * from user where user_name='" + userName + "' and password='" + userPassword + "'", null);

                if (cursor.moveToFirst()) {
                    int count = cursor.getCount();
                    Log.d("mensaje", "count : " + count);
                    Bundle myBundle = new Bundle();
                    myBundle.putString("user", user.getText().toString());
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    mainActivity.putExtras(myBundle);
                    startActivity(mainActivity);
                    finish();
                } else {
                    Toast.makeText(this, "NO existe el usuario o contraseña incorrecta", Toast.LENGTH_LONG).show();
                }

            }


        } else {
            Toast.makeText(this, "Usuario o contraseña vacios", Toast.LENGTH_SHORT).show();
        }

    }

    public void createUser(View v) {
        if (!user.getText().toString().equals("") && !password.getText().toString().equals("")) {
            Database conectDb = new Database(this, "mydb", null, 3);
            SQLiteDatabase getDataDb = conectDb.getReadableDatabase();

            String userName = user.getText().toString();
            String userPassword = password.getText().toString();
            if (getDataDb == null) {
                Toast.makeText(this, "Error al consultar la data", Toast.LENGTH_LONG).show();
            } else {
                Cursor cursor = getDataDb.rawQuery("Select * from user where user_name='" + userName + "'", null);
                if (cursor.moveToFirst()) {
                    Toast.makeText(this, "Usuario ya existe", Toast.LENGTH_LONG).show();
                } else {
                    ContentValues cv = new ContentValues();
                    cv.put("user_name", userName);
                    cv.put("password", userPassword);
                    Long dataInsert = getDataDb.insert("user",null,cv);
                    if(dataInsert==-1){
                        Toast.makeText(this, "Error a ingresar data", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show();
                    }

                }
            }
        }
        else{
            Toast.makeText(this, "Usuario o contraseña vacios", Toast.LENGTH_LONG).show();
        }
    }
}