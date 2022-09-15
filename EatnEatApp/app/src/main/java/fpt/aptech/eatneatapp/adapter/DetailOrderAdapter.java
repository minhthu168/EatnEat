package fpt.aptech.eatneatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.entities.Vieworderdetail;
import fpt.aptech.eatneatapp.service.CartDAO;

public class DetailOrderAdapter  extends RecyclerView.Adapter<DetailOrderAdapter.ViewHolder>{

    List<Vieworderdetail> list;
    Context context;


    public DetailOrderAdapter(List<Vieworderdetail> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vieworderdetail od=list.get(position);
        if(od == null) {
            return;
        }
        holder.tvName.setText(od.getFoodname());
        holder.tvQty.setText(String.valueOf(od.getQuantity()));
        holder.tvPrice.setText(od.getTotalFormat());
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName,tvPrice, tvQty;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.od_item_name);
            tvPrice = itemView.findViewById(R.id.od_item_price);
            tvQty = itemView.findViewById(R.id.od_item_qty);


        }
    }
}
