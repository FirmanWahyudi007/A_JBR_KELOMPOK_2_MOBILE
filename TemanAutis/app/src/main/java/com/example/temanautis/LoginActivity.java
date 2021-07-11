package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Model.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    String Email, Password;
    APIRequestData apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.editTextEmail);
        etPassword = findViewById(R.id.editTextPassword);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);
    }



    public void onLoginClick(View View){
        startActivity(new Intent(this,RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
    public void onMasukClick(View view){
        apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
        Log.d("TAG", "onMasukClick: "+etEmail.getText().toString());
        Call<Login> loginCall = apiInterface.loginresponse(etEmail.getText().toString(),etPassword.getText().toString());
        loginCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, TabActivity.class));
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }

    public void login(String email, String password){

    }



}