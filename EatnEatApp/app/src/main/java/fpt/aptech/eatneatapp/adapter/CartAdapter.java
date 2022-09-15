package fpt.aptech.eatneatapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.entities.Item;
import fpt.aptech.eatneatapp.service.CartDAO;
import fpt.aptech.eatneatapp.service.UtilsApi;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    List<Item> list;
    Context context;
    CartDAO dao;
    public CartAdapter(List<Item> list, Context context,CartDAO dao) {
        this.list = list;
        this.context=context;
        this.dao=dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        //holder.imgCart.setImageResource(Integer.valueOf(list.get(position).getFoodname()));
        String uri= UtilsApi.url +"./images/"+list.get(position).getImage();
        Glide.with(context).load(uri).into(holder.imgCart);
        holder.name.setText(list.get(position).getFoodname());
        holder.price.setText(list.get(position).getPriceFormat());
        int totalQuantity=list.get(position).getQuantity();
        holder.quantity.setText(String.valueOf(totalQuantity));

        //xoa item
        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dao.clear(list.get(position));
               list.remove(position);
               notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCart;
        TextView name, price,quantity;
        ImageButton removeItem;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCart = itemView.findViewById(R.id.detailed_img);
            name = itemView.findViewById(R.id.detailed_name);
            price = itemView.findViewById(R.id.detailed_price);
            quantity=itemView.findViewById(R.id.quantity);
            removeItem=itemView.findViewById(R.id.imb_delete);


        }
    }

}
