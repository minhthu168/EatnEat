package fpt.aptech.eatneatapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.List;

import fpt.aptech.eatneatapp.DetailOrderActivity;
import fpt.aptech.eatneatapp.DetailedDailyMealActivity;
import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Vieworders;
import fpt.aptech.eatneatapp.service.UtilsApi;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.ViewHolder>{
    List<Vieworders> oList;
    Context context;

    public OrderHistoryAdapter(List<Vieworders> oList, Context context) {
        this.oList = oList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        Vieworders order = oList.get(position);

        if(order == null) {
            return;
        }

        holder.tvfood.setText(order.getFood());
        holder.tvprice.setText(order.getTotalFormat());
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        holder.tvtime.setText(formatter.format(order.getOrderdate()));
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetailed(order);
            }
        });
    }

    @Override
    public int getItemCount() {

        if(oList!=null)
        {
            return oList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layoutItem;
        TextView tvfood ,tvprice, tvtime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.layout_Item_order);
            tvfood = itemView.findViewById(R.id.o_name);
            tvprice = itemView.findViewById(R.id.o_price);
            tvtime=itemView.findViewById(R.id.o_time);
        }
    }

    private void onClickDetailed(Vieworders order) {
        Intent intent = new Intent(context, DetailOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objectOrder", order);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
