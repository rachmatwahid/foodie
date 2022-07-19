package com.rachmatwahid.foodie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private byte stock = 0;
    private TextView quantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quantityTextView = findViewById(R.id.quantityTextView);
    }

    public void addOne(View view) {
        stock ++;
        quantityTextView.setText(Byte.toString(stock));
    }

    public void substractOne(View view) {
        stock --;
        quantityTextView.setText(Byte.toString(stock));
    }
}