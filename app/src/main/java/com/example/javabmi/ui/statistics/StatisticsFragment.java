package com.example.javabmi.ui.statistics;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.javabmi.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.LinkedList;
import java.util.List;

public class StatisticsFragment extends Fragment {

    private StatisticsViewModel statisticsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statisticsViewModel =
                new ViewModelProvider(this).get(StatisticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        final TextView textView = root.findViewById(R.id.text_statistics);
        statisticsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText( "COVID DEATHS % BY BMI\n \nsrc: https://mexicobariatriccenter.com/obesity-increases-risk-covid-19-coronavirus");

                PieChart pieChart = root.findViewById(R.id.pieChart);

                pieChart.setUsePercentValues(true);
                pieChart.setExtraOffsets(5F, 10F, 5F, 5F);
                pieChart.setDrawHoleEnabled(true);
                pieChart.setHoleColor(Color.WHITE);
                pieChart.setTransparentCircleRadius(61f);

                PieEntry underWeight = new PieEntry((float) 0.6, "< 18.5 BMI");
                PieEntry normal = new PieEntry((float) 27.7, "18.5-24.9 BMI");
                PieEntry overWeight  = new  PieEntry((float) 31.6, "25-29.9 BMI");
                PieEntry obesity = new PieEntry((float) 32.8, "30-39.9 BMI");
                PieEntry morbidObese = new PieEntry((float) 7.3, "40+ BMI");

                List<PieEntry> values = new LinkedList<>();

                values.add(underWeight);
                values.add(normal);
                values.add(overWeight);
                values.add(obesity);
                values.add(morbidObese);

                PieDataSet dataSet = new PieDataSet(values, "% deaths");
                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                PieData pieData = new PieData();
                pieData.setDataSet(dataSet);


                pieData.setValueTextSize(15f);
                pieData.setValueTextColor(Color.WHITE);

                pieChart.setData(pieData);
            }
        });
        return root;
    }
}