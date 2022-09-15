package fpt.aptech.eatneatapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.adapter.OrderHistoryAdapter;
import fpt.aptech.eatneatapp.entities.Viewemployee;
import fpt.aptech.eatneatapp.entities.Vieworders;
import fpt.aptech.eatneatapp.service.OrderService;
import fpt.aptech.eatneatapp.service.UtilsApi;
import fpt.aptech.eatneatapp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment {
    RecyclerView recyclerView;
    List<Vieworders> list =new ArrayList<>();
    OrderHistoryAdapter adapter;
    OrderService service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_order, container, false);
        recyclerView=view.findViewById(R.id.orderHistory);
        service= UtilsApi.checkOut();
        Viewemployee emp= (Viewemployee) Session.getSession();
        orderList(emp.getEmpid());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        adapter = new OrderHistoryAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void orderList(String empid){
       service.findAllOrders(empid).enqueue(new Callback<List<Vieworders>>() {
            @Override
            public void onResponse(Call<List<Vieworders>> call, Response<List<Vieworders>> response) {
                if (response.isSuccessful()){
                    list.clear();
                    list.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Vieworders>> call, Throwable t) {

            }
        });

    }
}