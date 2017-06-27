package com.cindyhuang.marqreetextview.demo;

import android.app.Activity;
import android.os.Bundle;

import com.cindyhuang.marqueetextview.MarqueeTextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MarqueeTextView marquee = (MarqueeTextView)findViewById(R.id.marqueeTextView);
        marquee.initialize("Hello World");
    }
}
