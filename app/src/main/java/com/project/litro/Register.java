package com.project.litro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText firstName,secondName,email,mobile,password1,password2;
    Button register,login;
    String Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobileNumber);
        password1 = findViewById(R.id.password);
        password2 = findViewById(R.id.password1);
        register = findViewById(R.id.registerButton);
        login = findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginint = new Intent(Register.this,Login.class);
                startActivity(loginint);
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(firstName.getText().toString().isEmpty() || secondName.getText().toString().isEmpty() || email.getText().toString().isEmpty() || mobile.getText().toString().isEmpty() || password1.getText().toString().isEmpty() || password2.getText().toString().isEmpty()){
                    Toast.makeText(Register.this,"Fill all", Toast.LENGTH_SHORT).show();


                }else {
                    if(password1.getText().toString().equals(password2.getText().toString())){


                        String FIRSTNAME = firstName.getText().toString().trim();
                        String SECONDNAME = secondName.getText().toString().trim();
                        String EMAIL = email.getText().toString().trim();
                        String MOBILE = mobile.getText().toString().trim();
                        String PASSWORD = password1.getText().toString().trim();
                        DBHandler UserdbHandler = new DBHandler(Register.this);

                        long val = UserdbHandler.addUser(FIRSTNAME,SECONDNAME,EMAIL,MOBILE,PASSWORD);
                        if(val <=0 ){
                            Toast.makeText(Register.this,"Error!", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Register.this,"Register Successfully", Toast.LENGTH_SHORT).show();
                        }


                    }else{
                        Toast.makeText(Register.this,"password not match", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });



    }
}