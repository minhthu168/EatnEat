package fpt.aptech.eatneatapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.service.FoodService;
import fpt.aptech.eatneatapp.service.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuTypeAdapter extends RecyclerView.Adapter<MenuTypeAdapter.ViewHolder>{


    List<Menutype> list;
    Context context;
   ItemClickListener clickListener;


    public MenuTypeAdapter(List<Menutype> list, Context context, ItemClickListener clickListener) {
        this.list = list;
        this.context = context;
        this.clickListener=clickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int position) {
        Menutype type=list.get(position);
        String uri= UtilsApi.url +"./images/"+list.get(position).getTypeimg();
        Glide.with(context).load(uri).into(holder.imageView);
        holder.txtName.setText(type.getType());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(type.getMenutypeid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView txtName;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.hor_img);
            txtName=itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }





    }
