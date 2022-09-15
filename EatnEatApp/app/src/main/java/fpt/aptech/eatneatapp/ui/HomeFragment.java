package fpt.aptech.eatneatapp.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.adapter.ItemClickListener;
import fpt.aptech.eatneatapp.adapter.MenuAdapter;
import fpt.aptech.eatneatapp.adapter.MenuTypeAdapter;
import fpt.aptech.eatneatapp.entities.Menutype;
import fpt.aptech.eatneatapp.entities.Viewemployee;
import fpt.aptech.eatneatapp.entities.Viewfood;
import fpt.aptech.eatneatapp.service.CartDAO;
import fpt.aptech.eatneatapp.service.FoodService;
import fpt.aptech.eatneatapp.service.UtilsApi;
import fpt.aptech.eatneatapp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements ItemClickListener {

    RecyclerView typeView;
    MenuTypeAdapter typeAdapter;

    List<Menutype> typeList =new ArrayList<>();
    ListView listView;
    FoodService service;
    List<Viewfood> foodList=new ArrayList<>();
    MenuAdapter foodAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);;
        typeView =view.findViewById(R.id.home_hor_rec);
        service= UtilsApi.getFoodService();
        findAllCat();

        typeAdapter=new MenuTypeAdapter(typeList,getContext(),this);
        typeView.setAdapter(typeAdapter);
        typeView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        listView=view.findViewById(R.id.setList);
        foodAdapter=new MenuAdapter(getContext(),foodList,new CartDAO(getContext()));
        listView.setAdapter(foodAdapter);
        findAll();
        TextView empname=view.findViewById(R.id.employeeName);
        Viewemployee emp= (Viewemployee) Session.getSession();
        empname.setText("Hello, "+emp.getEmpname());
        return view;

    }
    public void findAllCat(){
        service.findAllCat().enqueue(new Callback<List<Menutype>>() {
            @Override
            public void onResponse(Call<List<Menutype>> call, Response<List<Menutype>> response) {
                typeList.clear();
                typeList.addAll(response.body());
                typeAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<Menutype>> call, Throwable t) {
                Log.d("MainActivity","Load food list to fail");
                t.printStackTrace();
            }
        });
    }
    public void findAll(){
        service.OptionList().enqueue(new Callback<List<Viewfood>>() {
            @Override
            public void onResponse(Call<List<Viewfood>> call, Response<List<Viewfood>> response) {
                foodList.clear();
                foodList.addAll(response.body());
                foodAdapter.notifyDataSetChanged();
                //gắn cái photo vào chỗ kia là OK
                //Bitmap bitmap = new ImageRequestAsk().execute(list.set(2)).get();
            }

            @Override
            public void onFailure(Call<List<Viewfood>> call, Throwable t) {
                Log.d("MainActivity","Load food list to fail");
                t.printStackTrace();
            }
        });
    }
    public List<Viewfood> searchByCat_OptionList(Integer type){
        List<Viewfood> fList=new ArrayList<>();
        service.searchByCat_OptionList(type).enqueue(new Callback<List<Viewfood>>() {
            @Override
            public void onResponse(Call<List<Viewfood>> call, Response<List<Viewfood>> response) {
                fList.clear();
                fList.addAll(response.body());
                foodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Viewfood>> call, Throwable t) {
                Log.d("MainActivity","Load food list to fail");
                t.printStackTrace();
            }
        });
        return fList;
    }
    @Override
    public void onClick(int menuid) {
        List<Viewfood> fList=searchByCat_OptionList(menuid);
        if (fList!=null){
            foodAdapter=new MenuAdapter(getContext(),fList,new CartDAO(getContext()));
            listView.setAdapter(foodAdapter);
        }else{
            return;
        }
    }
}