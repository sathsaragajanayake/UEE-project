package com.project.litro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView name,mobile,email,address;
    Button feedback,hotline,logout;
    ImageView edit;
    DBHandler userhandler;
    String name1dbget,name2dbget,emaildbget,mobiledbget,passworddbget,address1dbget,address2dbget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        userhandler = new DBHandler(this);
        userhandler.getReadableDatabase();
        Cursor datacursor1 = userhandler.readUserData();
        Cursor datacursor2 = userhandler.readaddressData();



        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobileNumber);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);


        edit = findViewById(R.id.addNumber);
        feedback = findViewById(R.id.feedbackbutton);
        hotline = findViewById(R.id.hotlinebutton);
        logout = findViewById(R.id.logoutbutton);



        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addressint = new Intent(Profile.this,AddAddress.class);
                startActivity(addressint);
            }
        });


        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        StringBuffer stringBuffer3 = new StringBuffer();
        StringBuffer stringBuffer4 = new StringBuffer();
        while(datacursor1 != null && datacursor1.moveToNext()) {
            name1dbget = stringBuffer1.append(datacursor1.getString(0)).toString();
            name2dbget = stringBuffer2.append(datacursor1.getString(1)).toString();
            emaildbget = stringBuffer3.append(datacursor1.getString(2)).toString();
            mobiledbget = stringBuffer4.append(datacursor1.getString(3)).toString();
            passworddbget = stringBuffer4.append(datacursor1.getString(4)).toString();
        }
        StringBuffer stringBuffer5 = new StringBuffer();
        StringBuffer stringBuffer6 = new StringBuffer();
        while(datacursor2 != null && datacursor2.moveToNext()) {
            address1dbget = stringBuffer5.append(datacursor2.getString(0)).toString();
            address2dbget = stringBuffer6.append(datacursor2.getString(1)).toString();
        }


        name.setText(name1dbget+" "+name2dbget);
        mobile.setText(mobiledbget);
        email.setText(emaildbget);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent updateint = new Intent(Profile.this,EditNumber.class);
                updateint.putExtra("name1",name1dbget);
                updateint.putExtra("name2",name2dbget);
                updateint.putExtra("email",emaildbget);
                updateint.putExtra("password",passworddbget);
                startActivity(updateint);
            }
        });


        if(address1dbget == null && address2dbget == null){
         address.setText("Null");
        }else {
            address.setText(address1dbget + "," + address2dbget);
        }

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent feedbackIntent = new Intent(Profile.this,AddAddress.class);
                //startActivity(feedbackIntent);
            }
        });
        hotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+1311));
                startActivity(callIntent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logoutIntent = new Intent(Profile.this,Login.class);
                startActivity(logoutIntent);
                finish();
            }
        });



    }
}