package com.rachmatwahid.foodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    TextView foodNameTextView;
    TextView quantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra(MainActivity.EXTRA_FOOD_NAME);
        Byte quantity = intent.getByteExtra(MainActivity.EXTRA_FOOD_QUANTITY, (byte) 0);

        foodNameTextView = findViewById(R.id.foodName_textView_cart);
        quantityTextView = findViewById(R.id.quantity_textView_cart);

        foodNameTextView.setText(foodName);
        quantityTextView.setText(quantity.toString());
    }
}