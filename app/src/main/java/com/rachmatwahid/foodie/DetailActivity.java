package com.rachmatwahid.foodie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    TextView foodNameTextView, descriptionTextView;

    Toolbar toolbar;
    ActionBar actionBar;

    public static final String EXTRA_ORDER = "ORDER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra(MainActivity.EXTRA_FOOD_NAME);

        Dish dish = intent.getParcelableExtra("DISH_EXTRA");

        foodNameTextView = findViewById(R.id.foodName_textView);
        descriptionTextView = findViewById(R.id.foodDescription_textView);

        foodNameTextView.setText(dish.getName());
        descriptionTextView.setText(dish.getDescription());
    }

    public void orderNow(View view) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ORDER, 1);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void share(View view) {
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this with ")
                .setText("Fried Rice")
                .startChooser();
    }
}