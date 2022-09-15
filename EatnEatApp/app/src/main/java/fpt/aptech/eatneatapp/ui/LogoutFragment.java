package fpt.aptech.eatneatapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fpt.aptech.eatneatapp.LoginActivity;
import fpt.aptech.eatneatapp.MainActivity;
import fpt.aptech.eatneatapp.R;
import fpt.aptech.eatneatapp.session.Session;

public class LogoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_logout, container, false);
        Intent intent=new Intent(getContext(), LoginActivity.class);
        Session.setSession(null);
        startActivity(intent);
        return view;
    }
}