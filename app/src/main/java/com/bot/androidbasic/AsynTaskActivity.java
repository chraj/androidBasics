package com.bot.androidbasic;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AsynTaskActivity extends AppCompatActivity {


    TextView textView;
    TextView textViewconnectivity;

    static MtAsyncTask execute;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task);

        textView = findViewById(R.id.progressCount);
        textViewconnectivity = findViewById(R.id.connectivity);
        //way of doing something on backgroud thread
        //this is basically wrapper that is given as android API to use thread

        execute = new MtAsyncTask();
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null && intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                textViewconnectivity.setText(intent.getExtras().toString());
            }

        }
    };

    private class MtAsyncTask extends AsyncTask<Void, Integer, Void> {

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

    }

}
