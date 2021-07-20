package com.example.temanautis;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
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
import com.example.temanautis.Model.DonateResponse;
import com.example.temanautis.Model.ListDonateModel;
import com.example.temanautis.Model.SessionManager;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Konfirmasi extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    Button selectIMG;
    ListDonateModel lm;
    private String id, nominal;
    TextView metode, keterangan, Id, Nominal;
    Uri selectedImageUri;
    ImageView imageView;
    private String token;
    APIRequestData apiInterface;
    public static final int MY_PERMISSION_REQUEST = 100;
    private static final int PICK_IMAGE_FROM_GALERY_REQUEST = 0;
    private Bitmap bitmap;

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
            ActivityCompat.requestPermissions(Konfirmasi.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE_FROM_GALERY_REQUEST);
        }
        selectIMG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                execute(PICK_IMAGE_FROM_GALERY_REQUEST);
            }
        });
        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploudImage(selectedImageUri);
            }
        });
    }
    private void execute(int requestCode){
        switch (requestCode){
            case PICK_IMAGE_FROM_GALERY_REQUEST:
                if (EasyPermissions.hasPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                    Intent openGalleryintent = new Intent(Intent.ACTION_PICK);
                    openGalleryintent.setType("image/*");
                    startActivityForResult(openGalleryintent, PICK_IMAGE_FROM_GALERY_REQUEST);
                    break;
                }else {
                    EasyPermissions.requestPermissions(this, "Izinkan aplikasi mengaskses storage?", PICK_IMAGE_FROM_GALERY_REQUEST, Manifest.permission.READ_EXTERNAL_STORAGE);
                }

        }

    }
    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    void uploudImage(Uri contentURI){
        String filePath = getRealPathFromURIPath(contentURI, Konfirmasi.this);
        File file = new File(filePath);
        Log.d("File","Masuk"+file.getName());
        RequestBody mFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody idd = RequestBody.create(MediaType.parse("text/plain"),Id.getText().toString());
        MultipartBody.Part body = MultipartBody.Part.createFormData("bukti_transfer", file.getName(), mFile);
        apiInterface = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<DonateResponse> donateResponseCall = apiInterface.Upload("PUT","Bearer" + token,idd, body);
        donateResponseCall.enqueue(new Callback<DonateResponse>() {
            @Override
            public void onResponse(Call<DonateResponse> call, Response<DonateResponse> response) {
                DonateResponse donateResponse = response.body();
                Toast.makeText(Konfirmasi.this, "Server Respon : " + response.message(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<DonateResponse> call, Throwable t) {
                Toast.makeText(Konfirmasi.this, "Gagal"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_FROM_GALERY_REQUEST && resultCode == RESULT_OK){
            selectedImageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmap);
            uploudImage(selectedImageUri);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if(requestCode == PICK_IMAGE_FROM_GALERY_REQUEST){
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if(requestCode == PICK_IMAGE_FROM_GALERY_REQUEST){
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }

}