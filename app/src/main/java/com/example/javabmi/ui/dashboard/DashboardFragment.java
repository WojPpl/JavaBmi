package com.example.javabmi.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.javabmi.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        TextView dietText = root.findViewById(R.id.diet);
        String thinDiet = "Breakfest\nGranola x1\n\nLunch\nCelery & Hummus x1\n\nDiner\nEasy Grilled Lemon Chicken x1\nCrispy Garlic Edamame x1";
        String normalDiet = "Breakfest\nSausage and Egg Breakfast Sandwich x1\nOranges x2\n\nLunch\nTuna Stuffed Tomato\nCottage Cheese & Strawberries x2\n\nDinner\nChicken and Ranch Wrap x2\nSkinny Garlic Parmesan Zoodles x2";
        String fatDiet = "Breakfest\nOatmeal Cottage Cheese Pancakes x3\nButtered Toast x3\n\nLunch\nTuna Stuffed Pepper x3\nAlmonds x2\nLemon Avocado Salad x1\n\nDinner\nFried Egg and Ham Sandwich x3\nToast with Tomato and Hummus x1";
        ImageView photo = root.findViewById(R.id.diet_photo);

        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                requireActivity().getSupportFragmentManager().setFragmentResultListener("request_Key", getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        String bmiData = result.getString("bmi");

                        if(bmiData != null) {
                            Double bmi = Double.valueOf(bmiData);
                            if(bmi > 18.5 && bmi < 25) {
                                dietText.setText(normalDiet);
                                photo.setImageResource(R.drawable.normal);
                            }
                            else if (bmi > 25) {
                                dietText.setText(thinDiet);
                                photo.setImageResource(R.drawable.thin);
                            }
                            else {
                                dietText.setText(fatDiet);
                                photo.setImageResource(R.drawable.fat);
                            }
                        }
                    }
                });

            }
        });
        return root;
    }
}