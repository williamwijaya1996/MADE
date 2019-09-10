package com.example.movieandtvshowcatalogue.dialogfragment;


import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.movieandtvshowcatalogue.R;
import com.example.movieandtvshowcatalogue.ReminderActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsChoosenDialog extends DialogFragment {

    TextView optionLanguage,optionReminder;

    public OptionsChoosenDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options_choosen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        optionLanguage = view.findViewById(R.id.option_language);
        optionReminder = view.findViewById(R.id.option_reminder);
        optionLanguage.setText(getResources().getString(R.string.set_lang));
        optionReminder.setText(getResources().getString(R.string.set_reminder));
        init();
    }

    private void init(){

        optionReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReminderActivity.class);
                startActivity(intent);
                getDialog().dismiss();
            }
        });

        optionLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(mIntent);
                getDialog().dismiss();
            }
        });
    }
}
