package edu.uga.cs.state_capitals_quiz;
import java.util.Random;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        text = findViewById(R.id.textView3);
        ArrayList<Integer> questions = getQuestions();
        String q = questions.toString();
        text.setText(q);

    }

    public ArrayList<Integer> getQuestions(){

        ArrayList<Integer> toReturn = new ArrayList<Integer>();
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(50) + 1;
        toReturn.add(randomInt);

        while(toReturn.size() < 6){
            randomInt = randomGenerator.nextInt(50) + 1;
            if(!toReturn.contains(randomInt))
                toReturn.add(randomInt);
        }
        return toReturn;
    }

}
