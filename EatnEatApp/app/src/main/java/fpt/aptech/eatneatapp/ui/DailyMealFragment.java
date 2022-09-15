package fpt.aptech.eatneatapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.adapter.DailyMealAdapter;
import fpt.aptech.eatneatapp.adapter.ItemClickListener;
import fpt.aptech.eatneatapp.adapter.MenuAdapter;
import fpt.aptech.eatneatapp.adapter.MenuTypeAdapter;
import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Viewemployee;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.service.FoodService;
import fpt.aptech.eatneatapp.service.UtilsApi;
import fpt.aptech.eatneatapp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyMealFragment extends Fragment {

    RecyclerView recyclerView;
    List<Menutype> dailyMealModels = new ArrayList<>();
    FoodService service;
    DailyMealAdapter dailyMealAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_daily_meal, container, false);
        recyclerView = root.findViewById(R.id.daily_meal_rec);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(gridLayoutManager);

        service= UtilsApi.getFoodService();
        findAllCat();
        Viewemployee emp= (Viewemployee) Session.getSession();
        dailyMealAdapter = new DailyMealAdapter(dailyMealModels, getActivity());
        recyclerView.setAdapter(dailyMealAdapter);
        return root;
    }

    public void findAllCat(){
        service.findAllCat().enqueue(new Callback<List<Menutype>>() {
            @Override
            public void onResponse(Call<List<Menutype>> call, Response<List<Menutype>> response) {
                dailyMealModels.clear();
                dailyMealModels.addAll(response.body());
                dailyMealAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Menutype>> call, Throwable t) {
                Log.d("MainActivity","Load food list to fail");
                t.printStackTrace();
            }
        });
    }

    

}