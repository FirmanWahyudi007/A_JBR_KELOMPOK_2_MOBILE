package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.temanautis.Model.DataModel;
import com.example.temanautis.Model.ListDonateModel;
import com.google.gson.Gson;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Konfirmasi extends AppCompatActivity {
    Button selectIMG;
    int SELECT_PICTURE = 200;
    ListDonateModel lm;
    int id;
    private Uri selectedImageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        String data = getIntent().getStringExtra("extra");
        Gson gson = new Gson();
        lm = gson.fromJson(data, ListDonateModel.class);
        id = lm.getId();
        selectIMG = findViewById(R.id.buttonChoose);
        selectIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
            }
        }
    }
}