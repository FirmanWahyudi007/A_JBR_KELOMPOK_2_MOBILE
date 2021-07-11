package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Model.Login;
import com.example.temanautis.Model.Register;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    TextInputLayout Name, Email, Password, Password_Confirmation;
    APIRequestData apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        Name = (TextInputLayout) findViewById(R.id.textInputName);
        Email = (TextInputLayout) findViewById(R.id.textInputEmail);
        Password = (TextInputLayout) findViewById(R.id.textInputPassword);
        Password_Confirmation = (TextInputLayout) findViewById(R.id.textInputPassword_Confirmed);



    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);

    }

    public void onRegisterClick(View view) {
        apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
        Log.d("TAG", "onMasukClick: "+Name.getEditText().getText().toString());
        Log.d("TAG", "onMasukClick: "+Email.getEditText().getText().toString());
        Log.d("TAG", "onMasukClick: "+Password.getEditText().getText().toString());
        Log.d("TAG", "onMasukClick: "+Password_Confirmation.getEditText().getText().toString());
        Call<Register> registerCall = apiInterface.registerresponse(Name.getEditText().getText().toString(),Email.getEditText().getText().toString(),Password.getEditText().getText().toString(), Password_Confirmation.getEditText().getText().toString());
        registerCall.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }else {
                    Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }
}
