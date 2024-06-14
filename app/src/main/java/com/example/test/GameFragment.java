package com.example.test;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GameFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView _tv_time;
    private long _start,_end;
    private MyTimer myTimer;

    public GameFragment() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GameFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GameFragment newInstance(String param1, String param2) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // generate random number 1-10 and set it to buttons' text
        int min = 1;
        int max = 9;
        Random random = new Random();

        ArrayList<Integer> memo = new ArrayList<>();
        int idx = 1;

        while(idx < 10){
            int value = random.nextInt(max)+min;
            if(memo.isEmpty()){
                memo.add(value);
                Button bt = getButton(idx,view);
                bt.setText(String.valueOf(value));
                idx++;
                continue;
            }
            if(!memo.contains(value)){
                memo.add(value);
                Button bt = getButton(idx,view);
                bt.setText(String.valueOf(value));
                idx++;
            }
        }


        //set event to buttons
        ((Button)view.findViewById(R.id.bt_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.container,new MainMenuFragment());
                fragmentTransaction.commit();
            }
        });

        ((Button)view.findViewById(R.id.bt_toResult)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = ResultFragment.newInstance(_end-_start);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container,fragment);
                fragmentTransaction.commit();
            }
        });


//        Thread thread = new Thread(myTimer);
        dataClass data = new dataClass();
        for(int i = 1;i < 10;i++){
            Button bt = getButton(i,view);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button clickedButton = (Button)v;
                    if(clickedButton.getText() == String.valueOf(data.now)){
                        clickedButton.setVisibility(View.INVISIBLE);
                        data.countUp();
                        System.out.println(data.now);
                        if(data.now == 2){
//                            タイマースタート
                            _start = System.currentTimeMillis();
                            myTimer = new MyTimer(view.findViewById(R.id.tv_time));
                            myTimer.run();

                        }

                        if(data.now == 10){
//                            タイマーストップ
                            _end = myTimer.finish();
//                            ボタン表示
                            view.findViewById(R.id.bt_toResult).setVisibility(View.VISIBLE);

                        }
                    }
                }
            });
        }
    }

    public void mySetText(String time){
        _tv_time.setText(time);
    }

    private Button getButton(int idx,View view){
        Button bt;
        switch(idx){
            case 1:
                bt = view.findViewById(R.id.button);
                break;
            case 2:
                bt = view.findViewById(R.id.button2);
                break;
            case 3:
                bt = view.findViewById(R.id.button3);
                break;
            case 4:
                bt = view.findViewById(R.id.button4);
                break;
            case 5:
                bt = view.findViewById(R.id.button5);
                break;
            case 6:
                bt = view.findViewById(R.id.button6);
                break;
            case 7:
                bt = view.findViewById(R.id.button7);
                break;
            case 8:
                bt = view.findViewById(R.id.button8);
                break;
            case 9:
                bt = view.findViewById(R.id.button9);
                break;
            default:
                bt = view.findViewById(R.id.button);
                System.out.println("例外発生：idx = "+idx);
                break;
        }

        return bt;
    }




    public class dataClass{
        private int now;
        public dataClass(){
            now = 1;
        }

        private void countUp(){
            now++;
        }

    }
}


