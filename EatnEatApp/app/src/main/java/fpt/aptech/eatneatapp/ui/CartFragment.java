package fpt.aptech.eatneatapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


import cn.pedant.SweetAlert.SweetAlertDialog;
import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.ThankYouActivity;
import fpt.aptech.eatneatapp.adapter.CartAdapter;
import fpt.aptech.eatneatapp.entities.Item;
import fpt.aptech.eatneatapp.entities.Viewemployee;
import fpt.aptech.eatneatapp.service.CartDAO;
import fpt.aptech.eatneatapp.service.OrderService;
import fpt.aptech.eatneatapp.service.UtilsApi;
import fpt.aptech.eatneatapp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {

    RecyclerView cartView;
    TextView totalCart;
    Button checkout;
    CartAdapter cartAdapter;
    List<Item> list=new ArrayList<>();
    OrderService service;
    Integer orderid=0;
    CartDAO dao;
    int totalprice=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cart, container, false);
        cartView=view.findViewById(R.id.cart_rec);
        totalCart=view.findViewById(R.id.totalCart);
        checkout=view.findViewById(R.id.checkout);

        Viewemployee emp= (Viewemployee) Session.getSession();
        TextView txtCart=view.findViewById(R.id.txtMyCart);
        txtCart.setText(emp.getEmpname());
        addAdapter();
        checkout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addAdapter();
                if(totalprice==0){
                    alert("You don't have any items in your cart !");
                    return;
                }
//                if (LocalTime.now().getHour() > 10){
//                    alert("It's over 10 o'clock! The time for ordering food passed. See you tomorrow!");
//                    return;
//                }
                if (totalprice>35000){
                    alert("Sorry, you order the total price should not exceed 35,000 VND .Please reset!");
                    return;
                }
                new SweetAlertDialog(v.getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("You won't be able to order again for today!")
                        .setConfirmText("Yes")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                if(emp.getEmpid()!=null){
                                    saveOrder(emp.getEmpid(),count(),totalAmount());
                                }
                            }
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();

                }

        });
        return view;

    }
    public void addAdapter(){
        dao = new CartDAO(getContext());
        list=dao.findAll();
        cartView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartAdapter = new CartAdapter(list,getContext(),dao);
        cartView.setAdapter(cartAdapter);

        DecimalFormat formatter = new DecimalFormat("###,###,###");
        //totalCart.setText(formatter.format(totalAmount()));
        totalCart.setText(formatter.format(totalAmount()));
        service= UtilsApi.checkOut();
    }
    public int totalAmount(){
        int total=0;
        for (Item item:list){
            total+=item.getPrice()*item.getQuantity();
        }
        totalprice=total;
        return total;
    }
    public int count(){
        int count=0;
        for (Item item:list){
            count+=item.getQuantity();
        }
        return count;
    }
    public void saveOrder(String empid,int quantity,int total){
        service.saveOrder(empid,quantity,total).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                orderid=response.body();
                if (orderid==-2){
                   alert("You placed an order for 35000 VND today. Please rebook tomorrow.");
                    return;
                }
                if (orderid==-1){
                    alert("You ordered today, the item you are ordering plus the items you ordered today exceeds 35000 VND. Please rebook tomorrow.");
                    return;
                }
                for (Item item:list){
                    saveOrderdetail(orderid,item.getFoodid(), item.getQuantity());
                }
                dao.clearAll();
                cartAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Check out Successfully", Toast.LENGTH_SHORT).show();
                Intent activity=new Intent(getActivity(), ThankYouActivity.class);
                startActivity(activity);


            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getContext(), "Order Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveOrderdetail(int orderid,int foodid,int quantity) {
        service.saveOrderDetail(orderid, foodid, quantity).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {

            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

    }
    public void alert(String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setIcon(R.drawable.ic_baseline_warning_24);
        builder.setTitle("Alert");
        builder.setMessage(message);
        builder.create();
        builder.show();
    }

}