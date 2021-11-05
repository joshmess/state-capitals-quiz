package edu.uga.cs.state_capitals_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String DEBUG_TAG = "MainActivity";
    private AppData appData;
    final String TAG = "CSVReading";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appData = new AppData(this);
        new QuestionDBWriter().execute();
    }

    public class QuestionDBWriter extends AsyncTask<Question, Question> {

        protected ArrayList<Question> doInBackground( ) {
            ArrayList<Question> allQuestions = new ArrayList<Question>();
            try {
                InputStream in_s = getAssets().open("state_capitals.csv");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in_s));
                String csv_line;
                while((csv_line = reader.readLine()) != null) {
                    String[] values = csv_line.split(",");
                    Question newQuestion = new Question();
                    newQuestion.setState(values[0]);
                    newQuestion.setCapital(values[1]);
                    newQuestion.setCity1(values[2]);
                    newQuestion.setCity2(values[3]);

                    allQuestions.add(newQuestion);
                    System.out.println("NEW QUESTION: " + newQuestion.toString());
                    appData.storeQuestion(newQuestion);
                }

            }catch (Exception e){
                Log.e(TAG, e.toString());
            }
            return allQuestions;
        }

        @Override
        protected void onPostExecute( ArrayList<Question> questions ) {
            // Show a quick confirmation message
            Toast.makeText( getApplicationContext(), questions.size() + " questions stored.",
                    Toast.LENGTH_SHORT).show();


            Log.d( DEBUG_TAG, questions.size() + " questions stored." );
        }

    }



}