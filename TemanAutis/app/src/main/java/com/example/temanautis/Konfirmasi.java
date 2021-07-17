package com.example.temanautis;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.temanautis.API.APIRequestData;
import com.example.temanautis.API.RetroServer;
import com.example.temanautis.Model.DataModel;
import com.example.temanautis.Model.DonateResponse;
import com.example.temanautis.Model.ListDonateModel;
import com.example.temanautis.Model.ListDonateResponse;
import com.example.temanautis.Model.SessionManager;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Konfirmasi extends AppCompatActivity {
    Button selectIMG;
    final int SELECT_PICTURE = 9544;
    ListDonateModel lm;
    private String id, nominal;
    TextView metode, keterangan, Id, Nominal;
    private Uri selectedImageUri;
    String part_image;
    ImageView imageView;
    private String token;
    APIRequestData apiInterface;
    private static final int MY_PERMISSION_REQUEST = 100;
    private int PICK_IMAGE_FROM_GALERY_REQUEST = 1;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        metode = findViewById(R.id.editTextMetode);
        keterangan = findViewById(R.id.editTextKeterangan);
        Id = findViewById(R.id.editTextId);
        Nominal = findViewById(R.id.editTextNominal);
        String data = getIntent().getStringExtra("extra");
        Gson gson = new Gson();
        lm = gson.fromJson(data, ListDonateModel.class);
        id = String.valueOf(lm.getId());
        nominal = String.valueOf(lm.getNominal());
        Id.setText(id);
        Nominal.setText(nominal);
        metode.setText(lm.getMetode_pembayaran());
        keterangan.setText(lm.getKeterangan());
        Button konfirmasi;
        konfirmasi = findViewById(R.id.kirimkonfirmasi);
        selectIMG = findViewById(R.id.buttonChoose);
        imageView = (ImageView) findViewById(R.id.imageView);
        SessionManager sessionManager = new SessionManager(getBaseContext());
        token = (String) sessionManager.getUserDetail().get(SessionManager.TOKEN);
        if (ContextCompat.checkSelfPermission(Konfirmasi.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Konfirmasi.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
        }
        selectIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "open galery"), PICK_IMAGE_FROM_GALERY_REQUEST);
            }
        });
        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File imageFile = new File(part_image);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-file"), imageFile);
                MultipartBody.Part partImage = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);

                apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
                Log.d("TAG", "onMasukClick: " + Id.getText());
                Call<DonateResponse> uploud = apiInterface.Upload("Bearer " + token, Integer.parseInt(Id.getText().toString()), partImage);
                uploud.enqueue(new Callback<DonateResponse>() {
                    @Override
                    public void onResponse(Call<DonateResponse> call, Response<DonateResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<DonateResponse> call, Throwable t) {
                        Toast.makeText(Konfirmasi.this, "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("onFailure", "onFailure: " + t.getMessage());
                    }
                });
            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_FROM_GALERY_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            String[] imageprojection = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImageUri, imageprojection, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int indexImage = cursor.getColumnIndex(imageprojection[0]);
                part_image = cursor.getString(indexImage);
                if (part_image != null) {
                    file = new File(part_image);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                }
            }
        }
    }
}