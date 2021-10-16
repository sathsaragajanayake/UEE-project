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
import com.project.litro.models.currentOrderModel;

import java.util.List;

public class CurrentOrderAdapter  extends RecyclerView.Adapter<CurrentOrderAdapter.CurrentOrderHolder>{

    private List<currentOrderModel> listc;
    private Context context;
    DBHandler orderDBHandler;

    public CurrentOrderAdapter(List<currentOrderModel> list, Context context, DBHandler orderDBHandler) {
        this.listc = list;
        this.context = context;
        this.orderDBHandler = orderDBHandler;
    }
    @NonNull
    @Override
    public CurrentOrderAdapter.CurrentOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.current_order_card, parent,false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            orderDBHandler = new DBHandler(parent.getContext());;
        }
        return new CurrentOrderAdapter.CurrentOrderHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentOrderAdapter.CurrentOrderHolder holder, int position) {
        currentOrderModel currentorderList = listc.get(position);

        holder.pdate.setText(currentorderList.getDate());
        holder.porder.setText(currentorderList.getOrder());
        holder.pdealer.setText(currentorderList.getDealer());
        holder.ptotal.setText(currentorderList.getTotal());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listc.size();
    }

    public class CurrentOrderHolder extends RecyclerView.ViewHolder {
        private TextView pdate, porder, pdealer, ptotal;
        Button delete;

        public CurrentOrderHolder(@NonNull View itemView) {
            super(itemView);

            pdate = itemView.findViewById(R.id.pdate);
            porder = itemView.findViewById(R.id.porder);
            pdealer = itemView.findViewById(R.id.pdealer);
            ptotal = itemView.findViewById(R.id.ptotal);
            delete = itemView.findViewById(R.id.pdelete_button);

        }
    }
}
