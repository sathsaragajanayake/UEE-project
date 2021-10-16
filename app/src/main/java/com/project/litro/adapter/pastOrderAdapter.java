package com.project.litro.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.litro.DBHandler;
import com.project.litro.R;
import com.project.litro.models.pastOrdersModel;

import java.util.List;

public class pastOrderAdapter extends RecyclerView.Adapter<pastOrderAdapter.pastOrderHolder> {
    private List<pastOrdersModel> list;
    private Context context;
    com.project.litro.DBHandler DBHandler;



    public pastOrderAdapter(List<pastOrdersModel> list, Context context,DBHandler DBHandler) {
        this.list = list;
        this.context = context;
        this.DBHandler = DBHandler;
    }

    @NonNull
    @Override
    public pastOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.past_order_card, parent,false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            DBHandler = new DBHandler(parent.getContext());;
        }
        return new pastOrderHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull pastOrderHolder holder, int position) {
        pastOrdersModel pastorderList = list.get(position);

        holder.pdate.setText(pastorderList.getDate());
        holder.porder.setText(pastorderList.getOrder());
        holder.pdealer.setText(pastorderList.getDealer());
        holder.ptotal.setText(pastorderList.getTotal());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class pastOrderHolder extends RecyclerView.ViewHolder {
        private TextView pdate, porder, pdealer, ptotal;
        Button delete;

        public pastOrderHolder(@NonNull View itemView) {
            super(itemView);

            pdate = itemView.findViewById(R.id.pdate);
            porder = itemView.findViewById(R.id.porder);
            pdealer = itemView.findViewById(R.id.pdealer);
            ptotal = itemView.findViewById(R.id.ptotal);
            delete = itemView.findViewById(R.id.pdelete_button);

        }
    }
}
