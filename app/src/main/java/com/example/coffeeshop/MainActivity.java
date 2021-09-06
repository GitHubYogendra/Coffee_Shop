package com.example.coffeeshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    int add = 0;
    int add1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean click(View view) {
        CheckBox fee_checkbox = (CheckBox) findViewById(R.id.click);
        boolean checked;
        if (fee_checkbox.isChecked()) {
            checked = true;
            add = 1;
        } else {
            checked = false;
            add = 0;
        }

        return checked;
    }

    public boolean clickbox(View view) {
        boolean checked;
        CheckBox fee_checkbox = (CheckBox) findViewById(R.id.clickbox);
        if (fee_checkbox.isChecked()) {
            checked = true;
            add1 = 2;
        } else {
            checked = false;
            add1 = 0;
        }
        return checked;
    }

    public void submitOrder(android.view.View view) {
        TextView name = (TextView) findViewById(R.id.tex);
        String text = name.getText().toString();
        TextView email = (TextView) findViewById(R.id.editTextTextEmailAddress);
        String address = email.getText().toString();
        String dis = "Name : " + text + "\nAdd whipped cream ? " + click(view) + "\nAdd Chocolate ? " + clickbox(view) + "\nQuantity : " + i + "\nTotal : $ " + ((i * 5) + ((add + add1) * i)) + "\nThank you!";
        displayMessage(dis);
    }


    private void displayMessage(String dis) {
        TextView price = (TextView) findViewById(R.id.cost);
        price.setText("" + dis);
    }

    private void display(int dom) {
        TextView minus = (TextView) findViewById(R.id.text_view);
        minus.setText("" + dom);
    }

    private void displayPrise(int num) {
        TextView prisecost = (TextView) findViewById(R.id.cost);
        prisecost.setText(NumberFormat.getCurrencyInstance().format(num));
    }

    public void submitPlus(View view) {
        int sum = i + 1;
        if (sum <= 100) {
            display(sum);
            displayPrise((sum * 5) + ((add + add1) * sum));
            i = sum;
        } else {
            i = 100;
            Toast.makeText(this, "YOU CANNOT HAVE MORE THEN 100 COFFEES", Toast.LENGTH_LONG).show();
        }
    }

    public void submitMinus(View view) {
        int diff = i - 1;
        if (diff >= 0) {
            display(diff);
            displayPrise((diff * 5) + ((add + add1) * diff));
            i = diff;
        } else {
            i = 0;
            Toast.makeText(this, "NO COFFEE ORDERED", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void conform(View view) {
        TextView name = (TextView) findViewById(R.id.tex);
        String text = name.getText().toString();
        String address = "yogendrakgowda006@gmail.com";
        String dis = "Name : " + text + "\nAdd whipped cream ? " + click(view) + "\nAdd Chocolate ? " + clickbox(view) + "\nQuantity : " + i + "\nTotal : $ " + ((i * 5) + ((add + add1) * i)) + "\nThank you!";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL,address);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Summery for " + text);
        intent.putExtra(Intent.EXTRA_TEXT, dis);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

