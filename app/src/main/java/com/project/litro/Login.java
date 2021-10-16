package com.project.litro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText email,password;
    Button login,register;
    TextView forgetPassword;
    DBHandler handler;
    String emaildbget,passworddbget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = findViewById(R.id.loginemail);
        password = findViewById(R.id.loginpassword);
        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.loginregisterButton);
        forgetPassword = findViewById(R.id.forgot);
        handler = new DBHandler(this);
        handler.getReadableDatabase();

        Cursor datacursor = handler.readUserData();

        if(datacursor.getCount() == 0){
            Toast.makeText(this,"Register First",Toast.LENGTH_SHORT).show();
        }
        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        while(datacursor != null && datacursor.moveToNext()) {
            emaildbget =stringBuffer1.append(datacursor.getString(2)).toString();
            passworddbget =stringBuffer2.append(datacursor.getString(4)).toString();
        }



        /*AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setCancelable(true);
        builder.setTitle("Login Data");
        builder.setMessage(emaildbget + "\n" +passworddbget + "\n");
        builder.show();*/






        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString().trim();
                String password1 = password.getText().toString().trim();
                if(email1.equals(emaildbget) && password1.equals(passworddbget)){
                    Intent loginIntent = new Intent(Login.this,PermissionsActivity.class);
                    startActivity(loginIntent);
                }else{
                    Toast.makeText(Login.this,"Username or Password is wrong",Toast.LENGTH_SHORT).show();
                }

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerint = new Intent(Login.this,Register.class);
                startActivity(registerint);
            }
        });

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passwordint = new Intent(Login.this,PasswordResetFirst.class);
                startActivity(passwordint);
            }
        });

    }
}