package fpt.aptech.eatneatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fpt.aptech.eatneatapp.entities.Viewemployee;
import fpt.aptech.eatneatapp.session.Session;

public class OTPCodeActivity extends AppCompatActivity {

    private EditText otpET1,otpET2,otpET3,otpET4,otpET5,otpET6;
    private TextView lgPhone;
    private Button btnSend;
    private int selectedETOption=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpcode);

        otpET1=findViewById(R.id.otpET1);
        otpET2=findViewById(R.id.otpET2);
        otpET3=findViewById(R.id.otpET3);
        otpET4=findViewById(R.id.otpET4);
        otpET5=findViewById(R.id.otpET5);
        otpET6=findViewById(R.id.otpET6);

        btnSend=findViewById(R.id.btnSend);

        lgPhone=findViewById(R.id.lgPhone);
        //lay phone tu LoginActivity thong qua intent
        Viewemployee emp= (Viewemployee) Session.getSession();
        String phone=getIntent().getStringExtra(emp.getPhone());
        String otp=getIntent().getStringExtra("otp");
        lgPhone.setText(phone);




        otpET1.addTextChangedListener(textWatcher);
        otpET2.addTextChangedListener(textWatcher);
        otpET3.addTextChangedListener(textWatcher);
        otpET4.addTextChangedListener(textWatcher);
        otpET5.addTextChangedListener(textWatcher);
        otpET6.addTextChangedListener(textWatcher);

        //mac dinh keyboard o otpET1
        showKeyboard(otpET1);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String otpcode=otpET1.getText().toString()+otpET2.getText().toString()
                        +otpET3.getText().toString()+otpET4.getText().toString()
                        +otpET5.getText().toString()+otpET6.getText().toString();
                if (otpcode.length()==6){
                    if (otp.equals(otpcode)){
                        Toast.makeText(OTPCodeActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                        Intent activity = new Intent(OTPCodeActivity.this, MainActivity.class);
                        startActivity(activity);
                    }else{
                        Toast.makeText(OTPCodeActivity.this,"OTP Code is incorrect", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    private void showKeyboard(EditText otpET){
        otpET.requestFocus();
        InputMethodManager inputMethodManager= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(otpET,InputMethodManager.SHOW_IMPLICIT);
    }

    private  final TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length()>0){
                if (selectedETOption==0){
                    showKeyboard(otpET1);
                    selectedETOption=1;
                }else if (selectedETOption==1){
                    showKeyboard(otpET2);
                    selectedETOption=2;
                }else if (selectedETOption==2){
                    showKeyboard(otpET3);
                    selectedETOption=3;
                }else if (selectedETOption==3){
                    showKeyboard(otpET4);
                    selectedETOption=4;
                }else if (selectedETOption==4){
                    showKeyboard(otpET5);
                    selectedETOption=5;
                }else if (selectedETOption==5){
                    showKeyboard(otpET6);
                }
            }

        }
    };
}