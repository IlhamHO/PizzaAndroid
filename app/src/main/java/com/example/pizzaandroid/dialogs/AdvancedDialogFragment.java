package com.example.pizzaandroid.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

public class AdvancedDialogFragment extends DialogFragment {
    private DialogConfirmInterface<String> confirmInterface;
    private ArrayList<String> mUserItems = new ArrayList<>();
    private String toppings[] = {"Pepperoni", "Sausage", "Anchovies", "Mushrooms", "Onions", "Peppers", "Olives","Tomatoes","Pineapple","Extra Cheese"};

    //listeners magico
    public interface DialogConfirmInterface<T> {
        void cancel();

        void confirm(T[] values);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            confirmInterface = (DialogConfirmInterface<String>) context;
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            Log.e("ConformInterface", "Correct interface not implemented");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final boolean gekozen[] = new boolean[toppings.length];
        Arrays.fill(gekozen, false);

        builder.setTitle("Kies de toppings");
        builder.setMultiChoiceItems(toppings, gekozen, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    mUserItems.add(toppings[which]);
                } else {
                    mUserItems.remove(toppings[which]);
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String values[] = new String[mUserItems.size()];
                for (int i = 0; i < mUserItems.size(); i++) {
                    values[i] = mUserItems.get(i);
                }
                confirmInterface.confirm(values);
            }
        });
        return builder.create();
    }
}


