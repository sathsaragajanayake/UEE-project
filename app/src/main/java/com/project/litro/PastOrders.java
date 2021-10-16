package com.project.litro;



import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.litro.adapter.pastOrderAdapter;
import com.project.litro.models.pastOrdersModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PastOrders extends Fragment {

    public PastOrders(){}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_past_orders, container, false);
        DBHandler DBHandler1;
        DBHandler1 = new DBHandler(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.pastordersrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<pastOrdersModel> lists = new ArrayList<>();

        Cursor dbgetcursor = DBHandler1.readordersData();

        if(dbgetcursor.getCount() == 0){
            Toast.makeText(getContext(),"Nothing to show",Toast.LENGTH_SHORT).show();

        }else{
            while (dbgetcursor.moveToNext() && dbgetcursor.getString(5).equals("completed")){
                String orderid = dbgetcursor.getString(0);
                String date = dbgetcursor.getString(1);
                String dealer = dbgetcursor.getString(2);
                String total = dbgetcursor.getString(3);
                String status = dbgetcursor.getString(4);
                String qty = dbgetcursor.getString(5);

                lists.add(new pastOrdersModel(date,orderid,dealer,total,status,qty));

            }

        }



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.setAdapter(new pastOrderAdapter(lists,getContext(),DBHandler1));
        }
        return view;
    }
}