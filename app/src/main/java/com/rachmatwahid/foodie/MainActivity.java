package com.rachmatwahid.foodie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private byte stock = 0;
    private String foodName = "Fried Rice";
    private TextView quantityTextView;

    public static final  String EXTRA_FOOD_NAME = "NAME";
    public static final int ORDER_REQUEST = 1;

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

    public void launchDetailActivity(View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(EXTRA_FOOD_NAME, foodName);
        startActivityForResult(intent, ORDER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ORDER_REQUEST) {
            if (resultCode == RESULT_OK) {
                int orderRequest = data.getIntExtra(DetailActivity.EXTRA_ORDER, 0);
                quantityTextView.setText(String.valueOf(orderRequest));
            }
        }
    }
}