package fpt.aptech.eatneatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.adapter.DetailOrderAdapter;
import fpt.aptech.eatneatapp.entities.Item;
import fpt.aptech.eatneatapp.entities.Viewemployee;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.entities.Vieworderdetail;
import fpt.aptech.eatneatapp.entities.Vieworders;
import fpt.aptech.eatneatapp.service.CartDAO;
import fpt.aptech.eatneatapp.service.FoodService;
import fpt.aptech.eatneatapp.service.OrderService;
import fpt.aptech.eatneatapp.service.UtilsApi;
import fpt.aptech.eatneatapp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailOrderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView eName,eID,tvOid,tvDate,total;
    Button btnHome, reOrder;
    List<Vieworderdetail> detailedOrderList;
    DetailOrderAdapter adapter;
    OrderService service;
    FoodService foodService;
    Viewfood food =new Viewfood();
    Vieworders order;
    CartDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Title Bar
        actionBar.setTitle("Detail Order");
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);

        recyclerView = findViewById(R.id.list_order_detail);
        eName=findViewById(R.id.od_empname);
        eID=findViewById(R.id.od_empid);
        tvOid=findViewById(R.id.od_orderid);
        tvDate=findViewById(R.id.od_orderdate);
        total=findViewById(R.id.od_total);
        btnHome =findViewById(R.id.od_home);
        reOrder=findViewById(R.id.od_reorder);


        Viewemployee emp= (Viewemployee) Session.getSession();
        eName.setText("Employee Name : "+emp.getEmpname());
        eID.setText("EmpID : "+emp.getEmpid());

        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }
        dao=new CartDAO(this);
        order = (Vieworders) bundle.get("objectOrder");
        tvOid.setText("OrderID: "+order.getOrderid());
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        tvDate.setText("OrderDate : "+formatter.format(order.getOrderdate()));
        total.setText(order.getTotalFormat());

        service= UtilsApi.checkOut();
        detail(order.getOrderid());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DetailOrderAdapter(detailedOrderList,this);
        recyclerView.setAdapter(adapter);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailOrderActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        reOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (LocalTime.now().getHour() > 10){
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
                    for (Vieworderdetail od:detailedOrderList){
                        Item item =new Item();
                        item.setFoodid(od.getFoodid());
                        item.setFoodname(od.getFoodname());
                        item.setPrice(od.getPrice());
                        item.setImage(od.getImage());
                        item.setQuantity(od.getQuantity());
                        dao.addCart(item);
                    }
                    Toast.makeText(DetailOrderActivity.this, "Add To Cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<Vieworderdetail> detail(int orderid){
        detailedOrderList=new ArrayList<>();
        service.findAllOrderDetail(orderid).enqueue(new Callback<List<Vieworderdetail>>() {
            @Override
            public void onResponse(Call<List<Vieworderdetail>> call, Response<List<Vieworderdetail>> response) {
                detailedOrderList.clear();
                detailedOrderList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Vieworderdetail>> call, Throwable t) {
                Log.d("DetailOrderActivity","Load order detail list to fail");
                t.printStackTrace();
            }
        });
        return detailedOrderList;
    }
    public void findOneFood(int foodid){
        Call<Viewfood> findOneFood = UtilsApi.getFoodService().findOneFood(foodid);
        findOneFood.enqueue(new Callback<Viewfood>() {
            @Override
            public void onResponse(Call<Viewfood> call, Response<Viewfood> response) {
                food=response.body();
            }

            @Override
            public void onFailure(Call<Viewfood> call, Throwable t) {
                Log.d("DetailOrderActivity","Load food to fail");
                t.printStackTrace();
            }
        });
    }
    public void alert(String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(getBaseContext());
        builder.setIcon(R.drawable.ic_baseline_warning_24);
        builder.setTitle("Alert");
        builder.setMessage(message);
        builder.create();
        builder.show();
    }

    //back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return true;
    }
}