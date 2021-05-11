package com.example.javabmi.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.example.javabmi.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    private Integer questionNo = 0;
    private String[] questions = {
            "How did COVID 19 infect the first human? \n\n A) some Chinese ate a bat \n\n B) got out of a virus laboratory located in the city where the infection was detected \n\n C) aliens implanted it with a special probe",
            "Where Covid19 was separated? \n\n A) in China \n\n B) in Pakistan \n\n C) in the USA",
            "Which country is in the top three for coronavirus spread and deaths? \n\n A) Russia \n\n B) Italy \n\n C) Poland",
            "According to the latest Irish research, what fraction of Covid 19 infections has occurred outside? \n\n A) 1/10 \n\n B) 1/1000 \n\n C) 1/10000",
            "According to the WHO, how long does immunity to Covid 19 persist after vaccination? \n\n A) for one year \n\n B) it is unknown \n\n C) about half a year",
            "According to annual CDC research, wearing masks for 100 days reduces the spread of covid 19 virus? \n\n A) does not reduces spread \n\n B) reduces spread by about 20% \n\n C) reduces spread by about 1%"
    };
    private Integer[] rightAnswers = {1, 2, 3, 3, 2, 3};

    public void showToast(Integer answer, TextView textView) {

        Toast toastGood = Toast.makeText(getActivity().getApplicationContext(),"Good !!",Toast.LENGTH_SHORT);
        Toast toastWrong = Toast.makeText(getActivity().getApplicationContext(),"WRONG !!",Toast.LENGTH_SHORT);
        toastGood.setMargin(50,50);
        toastWrong.setMargin(50,50);

        if (questionNo > 4) {
            textView.setText("You WIN !!!");
        } else {
            if (answer == rightAnswers[questionNo]) {
                toastGood.show();
                questionNo = questionNo + 1;
                textView.setText(questions[questionNo]);
            } else {
                toastWrong.show();
            }
        }
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        Button buttonA = root.findViewById(R.id.buttonA);
        Button buttonB = root.findViewById(R.id.buttonB);
        Button buttonC = root.findViewById(R.id.buttonC);
        TextView questionsText = root.findViewById(R.id.quizQuestions);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(1, questionsText);
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(2, questionsText);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(3, questionsText);
            }
        });

        return root;
    }

}