package fpt.aptech.eatneatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.adapter.DetailedDailyMealAdapter;
import fpt.aptech.eatneatapp.adapter.MenuAdapter;
import fpt.aptech.eatneatapp.adapter.MenuTypeAdapter;
import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.service.CartDAO;
import fpt.aptech.eatneatapp.service.FoodService;
import fpt.aptech.eatneatapp.service.UtilsApi;
import fpt.aptech.eatneatapp.ui.CartFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailedDailyMealActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Viewfood> detailedDailyModelList;
    DetailedDailyMealAdapter detailedDailyAdapter;
    ImageView imageView1;
    FoodService service;
    CartDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_daily_meal);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        // Title Bar
        actionBar.setTitle("Daily Meal");
        Spannable text = new SpannableString(actionBar.getTitle());
        text.setSpan(new ForegroundColorSpan(Color.WHITE), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        actionBar.setTitle(text);
        recyclerView = findViewById(R.id.detailed_rec);
        imageView1 = findViewById(R.id.detailed_ImgType);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        Bundle bundle = getIntent().getExtras();
        if(bundle == null) {
            return;
        }
        dao=new CartDAO(this);
        Menutype menutype = (Menutype) bundle.get("objectUser");
        service= UtilsApi.getFoodService();
        searchByCat_SetList(menutype.getMenutypeid());

        String uri= UtilsApi.url +"./images/"+menutype.getTypeimg();
        Glide.with(this).load(uri).into(imageView1);
        detailedDailyAdapter = new DetailedDailyMealAdapter(detailedDailyModelList,this,dao);
        recyclerView.setAdapter(detailedDailyAdapter);
        ImageButton Cartfg=findViewById(R.id.detailed_cartfm);
        Cartfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DetailedDailyMealActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public List<Viewfood> searchByCat_SetList(Integer type){
        detailedDailyModelList = new ArrayList<>();
        service.searchByCat_SetList(type).enqueue(new Callback<List<Viewfood>>() {
            @Override
            public void onResponse(Call<List<Viewfood>> call, Response<List<Viewfood>> response) {
                detailedDailyModelList.clear();
                detailedDailyModelList.addAll(response.body());
                detailedDailyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Viewfood>> call, Throwable t) {
                Log.d("MainActivity","Load food list to fail");
                t.printStackTrace();
            }
        });
        return detailedDailyModelList;
    }
    //back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onBackPressed();
        return true;
    }


}