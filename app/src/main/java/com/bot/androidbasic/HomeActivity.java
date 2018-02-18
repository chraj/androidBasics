package com.bot.androidbasic;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements DemoFragment.OnFragmentInteractionListener, ServiceConnection {

    TextView textView;
    private MyService myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = findViewById(R.id.tvView);
        final String action = getIntent().getAction();
        //Way of accessing intent
        if (action != null) {
            if (action.equals(Intent.ACTION_SEND)) {
                textView.setText(String.format("%myService %myService", action, getIntent().getStringExtra(Intent.EXTRA_TEXT)));
            } else {
                textView.setText(action);
            }
        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, DemoFragment.newInstance("FRAGMENT 1")).addToBackStack(null).commit();
        manager.beginTransaction().replace(R.id.content, DemoFragment.newInstance("FRAGMENT 2")).addToBackStack(null).commit();
    }

    public void startServiceMethod(View view) {
        Toast.makeText(this, myService.getWordList().toString(), Toast.LENGTH_SHORT).show();
    }

    public void openTextIntent(View view) {

        startActivity(new Intent(this, AsynTaskActivity.class));
//        // Create the text message with a string
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "Check");
//        sendIntent.setType("text/plain");
//        sendIntent.setPackage(BuildConfig.APPLICATION_ID);
//        // Verify that the intent will resolve to an activity
//        if (sendIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(sendIntent);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(this);
    }

    @Override
    public void onFragmentInteraction(String uri) {
        textView.setText(uri);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MyService.MyBinder b = (MyService.MyBinder) service;
        myService = b.getService();

        myService.addSome("Random");
        List<String> wordList = myService.getWordList();
        Log.e("Data from service", wordList.toString());
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        myService = null;
    }
}
