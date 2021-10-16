package com.project.litro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditNumber extends AppCompatActivity {
    EditText email,newnumber;
    Button update;
    DBHandler userhandler;
    String emailget,newnumberget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_number);

        email = findViewById(R.id.numberresetemail);
        newnumber = findViewById(R.id.newNumberreset);

        update = findViewById(R.id.newNumberconfirmbutton);


        userhandler = new DBHandler(this);
        userhandler.getReadableDatabase();
        String name1 = getIntent().getExtras().get("name1").toString();
        String name2 = getIntent().getExtras().get("name2").toString();
        String email1 = getIntent().getExtras().get("email").toString();
        String password = getIntent().getExtras().get("password").toString();



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                newnumberget = newnumber.getText().toString().trim();
                emailget = email.getText().toString().trim();
                if(emailget.equals(email1)){

                    long i = userhandler.updateNumber(name1,name2,email1,newnumberget,password);
                    if(i<=0) {
                        Toast.makeText(EditNumber.this, "Error", Toast.LENGTH_SHORT).show();


                    }
                    else{
                        Toast.makeText(EditNumber.this, "Updated", Toast.LENGTH_SHORT).show();
                        Intent backint = new Intent(EditNumber.this,Profile.class);
                        startActivity(backint);
                    }
                }else{
                    Toast.makeText(EditNumber.this, "No User Exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}