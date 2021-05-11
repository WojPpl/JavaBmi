package com.example.javabmi.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;


import com.example.javabmi.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final Button button = root.findViewById(R.id.calculate);
        final EditText weightEdit  = root.findViewById(R.id.weight);
        final EditText heightEdit = root.findViewById(R.id.height);
        final TextView result  = root.findViewById(R.id.textBmi);
        final RadioButton male = root.findViewById(R.id.radioMale);
        final EditText ageEdit  = root.findViewById(R.id.age);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info;
                Float weight = Float.valueOf(weightEdit.getText().toString());
                Float height = Float.valueOf(heightEdit.getText().toString());
                Float age = Float.valueOf(ageEdit.getText().toString());
                Float bmi = (weight / ((height*height) / 100))*100;
                Double calories;

                if(male.isChecked()) {
                    calories = 66.47 + 13.7 * weight + 5 * height - 6.76 * age;
                }
                else {
                    calories = 655.1 + (9.567 * weight) + (1.85 * height) - (4.68 * age);
                }
                info = "Your BMI: " + bmi.toString() + "\nYou can eat " + calories.toString() + " calories";
                result.setText(info);
            }
        });
        return root;
    }
}