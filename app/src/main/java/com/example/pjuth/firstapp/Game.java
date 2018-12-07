package com.example.pjuth.firstapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener {

    public static Map<Integer, Integer> table1 = new HashMap<>();
    List<Button> buttons = new ArrayList<>();
    List<Integer> result = new ArrayList<>();

    private static final String TAG = "MainActivity";

    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initButtons();
        startGame();
    }

    @Override
    public void onClick(View v) {
        int lastMove;

        if (table1.get(v.getId()) == (Integer) 0 && !isThereWinner()) {

            Button button = findViewById(v.getId());
            button.setText("X");
            table1.put(v.getId(), 1);
            lastMove = 1;

            Integer comp = computerMove();
            if (comp != null && !isThereWinner()) {
                Button button2 = findViewById(comp);
                button2.setText("O");
                table1.put(comp, 2);
                lastMove = 2;
            }
            TextView textView = findViewById(R.id.textView);

            if (isThereWinner()) {
                if (lastMove == 1) {
                    textView.setTextColor(Color.parseColor("#008000"));
                    textView.setText("Laimėjai !!!");
                    result.set(0, result.get(0) + 1);
                } else {
                    textView.setTextColor(Color.parseColor("#FF0000"));
                    textView.setText("Pralošei...");
                    result.set(2, result.get(2) + 1);
                }
            } else if (!table1.containsValue(0)) {
                textView.setTextColor(Color.parseColor("#008080"));
                textView.setText("Lygiosios");
                result.set(1, result.get(1) + 1);
            }
            TextView resultTextView = findViewById(R.id.resultText);
            resultTextView.setText(result.get(0) + "            " + result.get(1) + "            " + result.get(2));
        }
    }

    public void newRoundButton(View v) {
        newRound();
    }

    public void back(View v) {
        if (result.get(0) != 0 || result.get(1) != 0 || result.get(2) != 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Ar išsaugoti rezultatą?").setPositiveButton("Taip", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.out.println("Išsaugoti");
                    startActivity(new Intent(Game.this, MainActivity.class));
                }
            }).setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Game.this, MainActivity.class));
                }
            }).create().show();
        } else {
            startActivity(new Intent(Game.this, MainActivity.class));
        }
    }


    public void initButtons() {

//        Typeface font = Typeface.createFromAsset(getAssets(), "1543Humane_jenson_bold.TTF");

        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        buttons.add((Button) findViewById(R.id.button5));
        buttons.add((Button) findViewById(R.id.button6));
        buttons.add((Button) findViewById(R.id.button7));
        buttons.add((Button) findViewById(R.id.button8));
        buttons.add((Button) findViewById(R.id.button9));

        for (Button b : buttons
                ) {
            b.setOnClickListener(this);
//            b.setTypeface(font);
        }
        TextView resultTextView = findViewById(R.id.resultText);

        result.add(0);
        result.add(0);
        result.add(0);
        resultTextView.setText(result.get(0) + "            " + result.get(1) + "            " + result.get(2));

    }

    public void newRound() {

        for (Button v : buttons
                ) {
            v.setText("");
        }
        TextView textView = findViewById(R.id.textView);
        textView.setText("");


        startGame();
    }

    public void startGame() {
        table1.put(R.id.button1, 0);
        table1.put(R.id.button2, 0);
        table1.put(R.id.button3, 0);
        table1.put(R.id.button4, 0);
        table1.put(R.id.button5, 0);
        table1.put(R.id.button6, 0);
        table1.put(R.id.button7, 0);
        table1.put(R.id.button8, 0);
        table1.put(R.id.button9, 0);
    }

    public boolean isThereWinner() {

        return (table1.get(R.id.button1) != (Integer) 0 && table1.get(R.id.button1) == table1.get(R.id.button2) && table1.get(R.id.button2) == table1.get(R.id.button3))
                || (table1.get(R.id.button1) != (Integer) 0 && table1.get(R.id.button1) == table1.get(R.id.button5) && table1.get(R.id.button5) == table1.get(R.id.button9))
                || (table1.get(R.id.button1) != (Integer) 0 && table1.get(R.id.button1) == table1.get(R.id.button4) && table1.get(R.id.button4) == table1.get(R.id.button7))
                || (table1.get(R.id.button2) != (Integer) 0 && table1.get(R.id.button2) == table1.get(R.id.button5) && table1.get(R.id.button5) == table1.get(R.id.button8))
                || (table1.get(R.id.button3) != (Integer) 0 && table1.get(R.id.button3) == table1.get(R.id.button6) && table1.get(R.id.button6) == table1.get(R.id.button9))
                || (table1.get(R.id.button4) != (Integer) 0 && table1.get(R.id.button4) == table1.get(R.id.button5) && table1.get(R.id.button5) == table1.get(R.id.button6))
                || (table1.get(R.id.button7) != (Integer) 0 && table1.get(R.id.button7) == table1.get(R.id.button5) && table1.get(R.id.button5) == table1.get(R.id.button3))
                || (table1.get(R.id.button7) != (Integer) 0 && table1.get(R.id.button7) == table1.get(R.id.button8) && table1.get(R.id.button8) == table1.get(R.id.button9));
    }

    public Integer computerMove() {

        List<Integer> list = new ArrayList<>();

        for (Object o : table1.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            if (pair.getValue() == (Integer) 0) {
                list.add((Integer) pair.getKey());
            }
        }
        if (list.isEmpty()) {
            return null;
        }

        Integer counterMove = isNeedOfCounter();
        if (counterMove != null) {
            return counterMove;
        } else {
            Random rand = new Random();
            return list.get(rand.nextInt(list.size()));
        }

    }

    public Integer counterMove(Integer b1, Integer b2, Integer b3) {

        if (table1.get(b1) != (Integer) 0 && table1.get(b1) == table1.get(b2) && table1.get(b3) == (Integer) 0) {
            return b3;
        } else if (table1.get(b1) != (Integer) 0 && table1.get(b1) == table1.get(b3) && table1.get(b2) == (Integer) 0) {
            return b2;
        } else if (table1.get(b2) != (Integer) 0 && table1.get(b3) == table1.get(b2) && table1.get(b1) == (Integer) 0) {
            return b1;
        } else {
            return null;
        }

    }

    public Integer isNeedOfCounter() {

        List<Integer> list = new ArrayList<>();
        list.add(counterMove(R.id.button1, R.id.button2, R.id.button3));
        list.add(counterMove(R.id.button1, R.id.button4, R.id.button7));
        list.add(counterMove(R.id.button1, R.id.button5, R.id.button9));
        list.add(counterMove(R.id.button4, R.id.button5, R.id.button6));
        list.add(counterMove(R.id.button7, R.id.button5, R.id.button3));
        list.add(counterMove(R.id.button7, R.id.button8, R.id.button9));
        list.add(counterMove(R.id.button2, R.id.button5, R.id.button8));
        list.add(counterMove(R.id.button3, R.id.button6, R.id.button9));

        // tikrina ar gali laimeti
//        for (Integer i : list
//                ) {
//            if (table1.get(i) == (Integer) 2) {
//                return i;
//            }
//        }
        // tikrina ar reikia kontra
        for (Integer i : list
                ) {
            if (i != null) {
                return i;
            }
        }
        // tikrina ar viduriukas neuzimtas
        if (table1.get(R.id.button5) == (Integer) 0) {
            return R.id.button5;
        } else {
            return null;
        }
    }

    public void AddData(String newEntry) {
        boolean insertData = dataBaseHelper.addData(newEntry);

        if (insertData) {
            System.out.println("Data Successfully Inserted!");
        } else {
            System.out.println("Data not inserted");
        }
    }
}
