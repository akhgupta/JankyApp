package com.akhil.jankyapp;

import android.os.Bundle;
import android.widget.TextView;

public class LeakyStaticViewActivity extends BaseAppCompatActivity {
    static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaky_static_view);
        textView = (TextView) findViewById(R.id.textView);
    }
}