package com.bot.androidbasic;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements DemoFragment.OnFragmentInteractionListener {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView = findViewById(R.id.tvView);
        final String action = getIntent().getAction();
        //Way of accessing intent
        if (action != null) {
            if (action.equals(Intent.ACTION_SEND)) {
                textView.setText(String.format("%s %s", action, getIntent().getStringExtra(Intent.EXTRA_TEXT)));
            } else {
                textView.setText(action);
            }
        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content, DemoFragment.newInstance("FRAGMENT 1")).addToBackStack(null).commit();
        manager.beginTransaction().replace(R.id.content, DemoFragment.newInstance("FRAGMENT 2")).addToBackStack(null).commit();
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
    public void onFragmentInteraction(String uri) {
        textView.setText(uri);
    }
}
