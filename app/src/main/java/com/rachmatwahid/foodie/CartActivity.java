package com.rachmatwahid.foodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    TextView foodNameTextView;
    TextView quantityTextView;
    TextView chefNoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra(MainActivity.EXTRA_FOOD_NAME);
        byte quantity = intent.getByteExtra(MainActivity.EXTRA_FOOD_QUANTITY, (byte) 0);
        String chefNote = intent.getStringExtra(MainActivity.EXTRA_CHEF_NOTE);

        foodNameTextView = findViewById(R.id.foodName_textView_cart);
        quantityTextView = findViewById(R.id.quantity_textView_cart);
        chefNoteTextView = findViewById(R.id.chefNote_TextView_cart);

        foodNameTextView.setText(foodName);
        quantityTextView.setText(String.valueOf(quantity));
        chefNoteTextView.setText(chefNote);
    }

}