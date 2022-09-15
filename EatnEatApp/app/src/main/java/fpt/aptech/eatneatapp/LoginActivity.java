package fpt.aptech.eatneatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fpt.aptech.eatneatapp.entities.Viewemployee;
import fpt.aptech.eatneatapp.service.UtilsApi;
import fpt.aptech.eatneatapp.session.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    EditText edtPhone;
    Button btnSendOTP;
    Viewemployee nEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPhone = findViewById(R.id.edtPhone);
        btnSendOTP = findViewById(R.id.btnSendOTP);

        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtPhone.getText().toString())){
                    Toast.makeText(LoginActivity.this,"Phone Number Required", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login :tim emp co phone nhu tren, if tim thay thi send OTP code
                    findOne();
                }
            }
        });
    }
//    public void findOne(){
//        Call<Viewemployee> findOneEmp = UtilsApi.checkLogin().findOne(edtPhone.getText().toString());
//        findOneEmp.enqueue(new Callback<Viewemployee>() {
//            @Override
//            public void onResponse(Call<Viewemployee> call, Response<Viewemployee> response) {
//                if(response.isSuccessful()){
//                    nEmployee= response.body();
//                    if (nEmployee!=null){
//                        //sendOTP();
//                        Intent activity = new Intent(LoginActivity.this, MainActivity.class);
//                        //luu Session
//                       Session.setSession(nEmployee);
//                        startActivity(activity);
//                    }else{
//                        Toast.makeText(getBaseContext(), "Phone is incorrect!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//            @Override
//            public void onFailure(Call<Viewemployee> call, Throwable t) {
//                Log.d("MainActivity","Phone is incorrect!");
//                t.printStackTrace();
//            }
//        });
//    }
    public void findOne(){
        Call<Viewemployee> findOneEmp = UtilsApi.checkLogin().findOne(edtPhone.getText().toString());
        findOneEmp.enqueue(new Callback<Viewemployee>() {
            @Override
            public void onResponse(Call<Viewemployee> call, Response<Viewemployee> response) {
                if(response.isSuccessful()){
                    nEmployee= response.body();
                    if (nEmployee!=null){
                        sendOTP();
                    }else{
                        Toast.makeText(getBaseContext(), "Phone is incorrect!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<Viewemployee> call, Throwable t) {
                Log.d("MainActivity","Phone is incorrect!");
                t.printStackTrace();
            }
        });
    }
    public void sendOTP(){
        Call<String> sendOTPRequest = UtilsApi.checkLogin().sendOTP(edtPhone.getText().toString());
        sendOTPRequest.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String otp=response.body();
                    if (otp!=null){
                        Toast.makeText(LoginActivity.this,"Send OTP Successful", Toast.LENGTH_LONG).show();
                        Intent activity = new Intent(LoginActivity.this, OTPCodeActivity.class);
                        //gui gia tri sang OTPCodeActivity
                        activity.putExtra("otp",response.body());
                        //luu Session
                        Session.setSession(nEmployee);
                        startActivity(activity);

                    }
                    Toast.makeText(LoginActivity.this,"Phone is incorrect!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Send Failed", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Phone is incorrect!", Toast.LENGTH_LONG).show();
            }
        });

    }

}