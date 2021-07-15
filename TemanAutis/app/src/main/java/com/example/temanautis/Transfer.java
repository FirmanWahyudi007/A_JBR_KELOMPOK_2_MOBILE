package com.example.temanautis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Transfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        TextView bca = (TextView) findViewById(R.id.bca);
        bca.setMovementMethod(LinkMovementMethod.getInstance());
        TextView bri = (TextView) findViewById(R.id.bri);
        bri.setMovementMethod(LinkMovementMethod.getInstance());
        TextView mandiri = (TextView) findViewById(R.id.mandiri);
        mandiri.setMovementMethod(LinkMovementMethod.getInstance());
        TextView gopay = (TextView) findViewById(R.id.gopay);
        gopay.setMovementMethod(LinkMovementMethod.getInstance());

        Button home2 = (Button) findViewById(R.id.home2);
        home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transfer.this, TabActivity.class);
                startActivity(intent);
            }
        });
    }
}