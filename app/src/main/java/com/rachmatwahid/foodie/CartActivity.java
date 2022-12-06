package com.rachmatwahid.foodie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CartActivity extends AppCompatActivity {

    String day, month, year, date;

    TextView foodNameTextView;
    TextView quantityTextView;
    TextView chefNoteTextView;
    TextView deliveryOptTextView;
    Button dateButton;
    Toolbar toolbar;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar = findViewById(R.id.cart_toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String foodName = intent.getStringExtra(MainActivity.EXTRA_FOOD_NAME);
        byte quantity = intent.getByteExtra(MainActivity.EXTRA_FOOD_QUANTITY, (byte) 0);
        String chefNote = intent.getStringExtra(MainActivity.EXTRA_CHEF_NOTE);
        boolean deliveryOpt = intent.getBooleanExtra(MainActivity.EXTRA_DELIVERY_OPT, false);

        foodNameTextView = findViewById(R.id.foodName_textView_cart);
        quantityTextView = findViewById(R.id.quantity_textView_cart);
        chefNoteTextView = findViewById(R.id.chefNote_TextView_cart);
        deliveryOptTextView = findViewById(R.id.deliveryOpt_textView_cart);
        dateButton = findViewById(R.id.date_button);


        dateButton.setText(setInitialDate());
        foodNameTextView.setText(foodName);
        quantityTextView.setText(String.valueOf(quantity));
        chefNoteTextView.setText(chefNote);
        if (deliveryOpt) {
            deliveryOptTextView.setText("Take Away");
        } else {
            deliveryOptTextView.setText("Dine In");
        }
    }

    private String setInitialDate() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day + " " + month + " " + year;
    }


    public void showDatePicker(View view) {
        DialogFragment dialogFragment = new DatePickerFragment();
        dialogFragment.show(getSupportFragmentManager(),"datePicker");
    }


    public void processDatePickerResult(Calendar calendar) {
        int date = calendar.get(Calendar.DATE);
        String month = new SimpleDateFormat("MMM").format(calendar.getTime());
        int year = calendar.get(Calendar.YEAR);
        dateButton.setText(date + " " + month + " " + year);
    }

    public void cookNow(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);
        alertDialog.setTitle("Cook Now");
        alertDialog.setMessage("The kitchen will start cooking the order.");
        alertDialog.show();
    }
}