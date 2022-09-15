package fpt.aptech.eatneatapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.DetailedDailyMealActivity;
import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.entities.Item;
import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.service.CartDAO;
import fpt.aptech.eatneatapp.service.UtilsApi;

public class DetailedDailyMealAdapter extends RecyclerView.Adapter<DetailedDailyMealAdapter.ViewHolder> {

    List<Viewfood> list;
    Context context;
    CartDAO dao;

    public DetailedDailyMealAdapter(List<Viewfood> list, Context context,CartDAO dao) {
        this.list = list;
        this.context = context;
        this.dao=dao;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.detailed_daly_meal_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Viewfood food=list.get(position);
        if(food == null) {
            return;
        }
        String uri= UtilsApi.url +"./images/"+list.get(position).getImage();
        Glide.with(context).load(uri).into(holder.imageView);
        holder.tvName.setText(food.getFoodname());
        holder.tvPrice.setText(food.getPriceFormat());
        holder.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (LocalTime.now().getHour() > 9){
//                    Toast.makeText(context, "It's over 10 o'clock! The time for ordering food passed. See you tomorrow!", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                int total=0;
                for (Item item:dao.findAll()){
                    total=total+item.getPrice()*item.getQuantity();
                    if (total>=35000){
                        alert("Sorry, you order the total price should not exceed 35,000 VND .Please reset!");
                        return;
                    }
                }

                Item item =new Item();
                item.setFoodid(food.getFoodid());
                item.setFoodname(food.getFoodname());
                item.setPrice(food.getPrice());
                item.setImage(food.getImage());
                Item olditem=dao.findOne(food.getFoodid());
                if(olditem!=null){
                    alert("This item is already in your cart!");
                    return;
                }else{
                    item.setQuantity(1);
                    dao.addCart(item);
                }
                Toast.makeText(context, "Add To Cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView ;
        TextView tvName,tvPrice;
        Button btnCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.detailed_img);
            tvName = itemView.findViewById(R.id.detailed_name);
            tvPrice = itemView.findViewById(R.id.detailed_price);
            btnCart=itemView.findViewById(R.id.detailed_addCart);

        }
    }
    public void alert(String message){
        androidx.appcompat.app.AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_baseline_warning_24);
        builder.setTitle("Alert");
        builder.setMessage(message);
        builder.create();
        builder.show();
    }
}
