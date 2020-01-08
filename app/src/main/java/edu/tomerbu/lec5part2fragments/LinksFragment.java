package edu.tomerbu.lec5part2fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LinksFragment extends Fragment {
    private TextView tvScore;
    private TextView tvTimeLeft;
    private Button btnTapMe;

    private int score = 0;
    private int timeLeft = 60; //seconds left
    private CountDownTimer countDownTimer;

    //optional:
    private boolean gameStarted = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_links, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvScore = view.findViewById(R.id.text_score);
        tvTimeLeft = view.findViewById(R.id.text_time);
        btnTapMe = view.findViewById(R.id.button_tap);

        btnTapMe.setOnClickListener(v -> {
            incrementScore();
        });
        resetGame();
    }

    private void incrementScore() {
        if (!gameStarted) {
            startGame();
        }
        score++;
        tvScore.setText(getString(R.string.score_text, score));
    }

    private void startGame() {
        countDownTimer.start();
        gameStarted = true;
    }

    private void resetGame() {
        score = 0;

        //get a string from resources (with placeholder)
        String scoreText = getString(R.string.score_text, score);
        tvScore.setText(scoreText);


        timeLeft = 60;
        tvTimeLeft.setText(getString(R.string.time_left, timeLeft));

        countDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //7_000 millis -> millis / 1000 = 7 seconds
                timeLeft = (int) millisUntilFinished / 1000;
                //update the ui:
                tvTimeLeft.setText(getString(R.string.time_left, timeLeft));
            }

            @Override
            public void onFinish() {
                //in fragments: `this` is not a context (use getContext() or getActivity())
                Toast.makeText(getContext(), "Times up! Your score was: " + score, Toast.LENGTH_SHORT).show();
                resetGame();
                // btnTapMe.setEnabled(false);
            }
        };
        gameStarted = false;
    }
}
