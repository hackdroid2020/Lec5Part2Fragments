package edu.tomerbu.lec5part2fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TextFragment extends Fragment {


    private String[] words = {"Lollipop", "Marshmallow", "KitKat", "Bisli", "Bamba", "Pink-Lady"};
    private int i = 0;
    private TextView tvWords;
    //onCreateView -> fragment creates it's View!
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_text, container, false);
        tvWords = v.findViewById(R.id.text_words);

        startTimer();
        return v;
    }

    //recursive call
    private void startTimer(){
        tvWords.postDelayed(() -> {
            //code that runs in one second from now
            tvWords.setText(words[i++]);
            if (i >= words.length) i = 0;

            startTimer();
        }, 1000);
    }

//    //onViewCreated: after the view is already created:
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        //no find view by id! -> view.findViewById
//        TextView tvWords = view.findViewById(R.id.text_words);
//
//    }


}
