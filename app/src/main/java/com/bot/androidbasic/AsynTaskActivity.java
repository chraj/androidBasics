package com.bot.androidbasic;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AsynTaskActivity extends AppCompatActivity {


    TextView textView;

    static AsyncTask<Void, Integer, Void> execute;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task);

        textView = findViewById(R.id.progressCount);
        //way of doing something on backgroud thread
        //this is basically wrapper that is given as android API to use thread


        execute = new AsyncTask<Void, Integer, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                textView.setText("Thread Started");
            }


            //worker thread
            @Override
            protected Void doInBackground(Void... voids) {
                int n = 0;
                while (n < 10) {
                    n++;
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    onProgressUpdate(n);
                }

                return null;
            }


            @SuppressLint("DefaultLocale")
            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                textView.setText(String.format("Counting =%d", values.length > 0 ? values[0] : 0));
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                textView.setText("Thread Ended");
            }


        }.execute();
    }


}
