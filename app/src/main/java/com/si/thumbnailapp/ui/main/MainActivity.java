package com.si.thumbnailapp.ui.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.si.thumbnailapp.R;
import com.si.thumbnailapp.ui.challenge.view.ChallengeFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_activity_container,
                    ChallengeFragment.newInstance()).commit();
        }
    }
}
