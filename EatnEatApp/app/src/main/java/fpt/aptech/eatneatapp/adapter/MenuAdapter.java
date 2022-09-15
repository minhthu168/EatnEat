package fpt.aptech.eatneatapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.time.LocalTime;
import java.util.List;

import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.entities.Item;
import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.service.CartDAO;
import fpt.aptech.eatneatapp.service.UtilsApi;

public class MenuAdapter extends BaseAdapter {

    private BottomSheetDialog bottomSheetDialog;
    Context context;
    List<Viewfood> list;
    CartDAO dao;
    int quantity;
    public MenuAdapter(Context context, List<Viewfood> list,CartDAO dao){
        this.context=context;
        this.list=list;
        this.dao=dao;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.food_item,null) ;

        TextView tvName=view.findViewById(R.id.name);
        TextView tvPrice=view.findViewById(R.id.price);
        ImageView imageFood=view.findViewById(R.id.ver_img);
        Viewfood food=list.get(i);

        tvName.setText(food.getFoodname());
        tvPrice.setText(food.getPriceFormat()+"  VND");


        String uri= UtilsApi.url +"./images/"+food.getImage();
        Glide.with(context).load(uri).into(imageFood);

        //BottomSheetDialog
        final String fName = food.getFoodname();
        final String fPrice = food.getPriceFormat();

        //New
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetTheme);

                View sheetView = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout, null);
                sheetView.findViewById(R.id.btnAdd_To_Cart).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        if (LocalTime.now().getHour() > 10){
//                            alert("It's over 10 o'clock! The time for ordering food passed. See you tomorrow!");
//                            return;
//                        }
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
                            item.setQuantity(olditem.getQuantity()+quantity);
                            dao.updateQuantity(olditem);
                        }else{
                            item.setQuantity(quantity);
                            dao.addCart(item);
                        }
                        notifyDataSetChanged();
                        Toast.makeText(context, "Add To Cart", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });

                ImageView bottomImg = sheetView.findViewById(Integer.valueOf(R.id.bottom_img));
                TextView bottomName = sheetView.findViewById(R.id.bottom_name);
                TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
                ImageView add_option=sheetView.findViewById(R.id.add_item_option);
                ImageView minis_option=sheetView.findViewById(R.id.minus_item_option);
                TextView quantity_option=sheetView.findViewById(R.id.quantity_option);

                bottomName.setText(fName);
                bottomPrice.setText(fPrice);
                String uri= UtilsApi.url +"./images/"+food.getImage();
                Glide.with(context).load(uri).into(bottomImg);
                quantity=1;
                quantity_option.setText(String.valueOf(quantity));
                add_option.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        quantity++;
                        quantity_option.setText(String.valueOf(quantity));
                        notifyDataSetChanged();
                    }
                });
                minis_option.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        quantity--;
                        quantity_option.setText(String.valueOf(quantity));
                        notifyDataSetChanged();
                    }
                });
                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();
            }
        });

        return view;
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
