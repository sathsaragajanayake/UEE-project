package com.project.litro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddAddress extends AppCompatActivity {
    String lat = "0",longt = "0";
    String lati = "0",longti = "0";
    EditText deliverto,address1,address2,city;
    Integer district;
    String districtno;
    Button save,picker;
    DBHandler myDB;
    String address1dbget,address2dbget,citydbget,placedbget,districtdbget;
    int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        deliverto = findViewById(R.id.place);

        address1 = findViewById(R.id.addressline1);
        address2 = findViewById(R.id.addressline2);
        city = findViewById(R.id.city);
        picker = findViewById(R.id.picker);

        save = findViewById(R.id.saveButton);

        myDB = new DBHandler(this);
        myDB.getReadableDatabase();
        Cursor datacursor2 = myDB.readaddressData();

        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent locationint = new Intent(AddAddress.this,map.class);
                startActivity(locationint);
            }
        });

        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.district_array,
                        android.R.layout.simple_spinner_item);

        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        staticSpinner.setAdapter(staticAdapter);

        district = (Integer) staticSpinner.getSelectedItem();
        districtno.equals(district.toString());

        lat = lati;
        longt =longti;

        StringBuffer stringBuffer1 = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        StringBuffer stringBuffer3 = new StringBuffer();
        StringBuffer stringBuffer4 = new StringBuffer();
        StringBuffer stringBuffer5 = new StringBuffer();
        while(datacursor2 != null && datacursor2.moveToNext()) {
            address1dbget = stringBuffer1.append(datacursor2.getString(0)).toString();
            address2dbget = stringBuffer2.append(datacursor2.getString(1)).toString();
            citydbget = stringBuffer3.append(datacursor2.getString(2)).toString();
            placedbget = stringBuffer4.append(datacursor2.getString(3)).toString();
            districtdbget = stringBuffer5.append(datacursor2.getString(4)).toString();
            Toast.makeText(AddAddress.this,districtdbget,Toast.LENGTH_SHORT).show();

            address1.setText(address1dbget);
            address2.setText(address2dbget);
            city.setText(citydbget);
            deliverto.setText(placedbget);
            staticSpinner.setSelection(Integer.parseInt(districtdbget));
        }




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeTXT = deliverto.getText().toString();

                String address1TXT = address1.getText().toString();
                String address2TXT = address2.getText().toString();
                String cityTXT = city.getText().toString();
                DBHandler dbHandler = new DBHandler(AddAddress.this);

                if(placeTXT.equals("")||address1TXT.equals("")||address2TXT.equals("")||cityTXT.equals("")|| district.equals("")){
                    Toast.makeText(AddAddress.this,"Fill all", Toast.LENGTH_SHORT).show();
                }else{
                    long val = dbHandler.addAddress(address1TXT,address2TXT,cityTXT,placeTXT,districtno);
                    Toast.makeText(AddAddress.this,"Subject Added Successfully", Toast.LENGTH_SHORT).show();
                    Intent profint = new Intent(AddAddress.this,Profile.class);
                    startActivity(profint);

                }
            }
        });
    }
}