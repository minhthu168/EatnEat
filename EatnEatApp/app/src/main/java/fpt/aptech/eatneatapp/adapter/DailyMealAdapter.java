package fpt.aptech.eatneatapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.DetailedDailyMealActivity;
import fpt.aptech.eatneatapp.MainActivity;
import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.service.FoodService;
import fpt.aptech.eatneatapp.service.UtilsApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.ViewHolder> {
    private List<Menutype> menutypeList;
    Context context;

    public DailyMealAdapter(List<Menutype> menutypeList, Context context) {
        this.menutypeList = menutypeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_meal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Menutype menutype = menutypeList.get(position);
        if(menutype == null) {
            return;
        }

        String uri= UtilsApi.url +"./images/"+menutypeList.get(position).getTypeimg();
        Glide.with(context).load(uri).into(holder.imageView);
        holder.tvName.setText(menutype.getType());
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetailed(menutype);
            }
        });
    }

    @Override
    public int getItemCount() {

        if(menutypeList!=null)
        {
            return menutypeList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutItem;
        ImageView imageView;
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layoutItem = itemView.findViewById(R.id.layout_Item);
            imageView = itemView.findViewById(R.id.imgDailyView);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }

    private void onClickDetailed(Menutype menutype){
        Intent intent = new Intent(context, DetailedDailyMealActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("objectUser", menutype);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
