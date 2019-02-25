package com.example.pizzaandroid;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.pizzaandroid.R;




import com.example.pizzaandroid.dialogs.AdvancedDialogFragment;

public class MainActivity extends AppCompatActivity implements AdvancedDialogFragment.DialogConfirmInterface<String> {


    private final String DIALOG_KEY = "dialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdvancedDialog = findViewById(R.id.btn_order);
        btnAdvancedDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdvancedDialogFragment adf = new AdvancedDialogFragment();
                adf.show(getSupportFragmentManager(), DIALOG_KEY);
            }
        });
    }

    @Override
    public void cancel() {
        Toast.makeText(this, R.string.str_toast_cancel, Toast.LENGTH_LONG).show();
    }



   @Override
    public void confirm(String[] values) {

        for(String element : values)
            Log.d("GEKOZEN", element);
        Snackbar sb;
       sb = Snackbar.make(findViewById(R.id.container), "Bestelling binnen", Snackbar.LENGTH_LONG);
       sb.setActionTextColor(Color.RED);
        sb.setAction("cancel", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("GEKOZEN", "alles geannuleerd");
            }
        });
        sb.show();
    }

    }

