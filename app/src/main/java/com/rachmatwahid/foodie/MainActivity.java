package com.rachmatwahid.foodie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private byte quantity = 0;
    private String foodName = "Fried Rice";
    private String chefNote;
    private boolean isTakeAway;

    private TextView quantityTextView;
    private EditText chefNoteEditText;

    public static final String EXTRA_FOOD_NAME = "NAME";
    public static final String EXTRA_FOOD_QUANTITY = "QUANTITY";
    public static final String EXTRA_CHEF_NOTE = "CHEFNOTE";
    public static final String EXTRA_DELIVERY_OPT = "DELIVERYOPT";

    public static final int ORDER_REQUEST = 1;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private final ArrayList<String> dishes = new ArrayList<>();

    private RecyclerView recyclerView;
    private DishesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dishes.add("Fried Rice");
        dishes.add("Chicken Noodle");
        dishes.add("Satay");

        recyclerView = findViewById(R.id.main_recyclerView);
        adapter = new DishesAdapter(this, dishes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.d(LOG_TAG, "onCreate");

        quantityTextView = findViewById(R.id.quantityTextView);
        chefNoteEditText = findViewById(R.id.chefNote_editText);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState != null) {
            quantity = savedInstanceState.getByte("SAVED_QUANTITY");
            quantityTextView.setText(Byte.toString(quantity));
        }
    }

    public void addOne(View view) {
        quantity++;
        quantityTextView.setText(Byte.toString(quantity));
    }

    public void substractOne(View view) {
        quantity--;
        quantityTextView.setText(Byte.toString(quantity));
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
                quantity += orderRequest;
                quantityTextView.setText(Byte.toString(quantity));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putByte("SAVED_QUANTITY", quantity);
    }

    public void launchAboutActivity(MenuItem item) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void showCart(View view) {
        chefNote = chefNoteEditText.getText().toString();

        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra(EXTRA_FOOD_NAME, foodName);
        intent.putExtra(EXTRA_FOOD_QUANTITY, quantity);
        intent.putExtra(EXTRA_CHEF_NOTE, chefNote);
        intent.putExtra(EXTRA_DELIVERY_OPT, isTakeAway);
        startActivity(intent);
    }

    public void setDiningOption(View view) {
        boolean radioChecked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.dine_radio:
                if (radioChecked) isTakeAway = false;
                break;
            case R.id.take_radio:
                if (radioChecked) isTakeAway = true;
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}