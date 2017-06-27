package com.cindyhuang.marqueetextview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class MarqueeTextView extends RecyclerView {

    public static final float TEXT_MAX_SIZE = 300;

    private Context context;
    private LinearLayoutManager linearLayoutManager;
    private int endPosition;
    private AtomicBoolean shouldStop = new AtomicBoolean(true);

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    MarqueeTextView.this.smoothScrollBy(50, 0);
                    break;
            }
        }
    };

    public MarqueeTextView(Context context) {
        super(context);
        this.context = context;
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public void initialize(final String text) {
        setHasFixedSize(true);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        setLayoutManager(linearLayoutManager);

        int textLength = text.length();
        int subTextLength = 5;
        final List<String> dataset = new ArrayList<>();
        dataset.add(" ");   // leading padding
        for (int n = 0; n < textLength; n += subTextLength)
            dataset.add(text.substring(n, Math.min(n + subTextLength, textLength)));
        dataset.add(" ");   // trailing padding
        endPosition = dataset.size() - 1;

        this.post(new Runnable() {
            @Override
            public void run() {
                int viewHeight = getHeight();
                float density = getResources().getDisplayMetrics().density;
                float textSize = Math.min(viewHeight / (density * 1.3f), TEXT_MAX_SIZE);

                MarqueeTextAdapter adapter = new MarqueeTextAdapter(dataset, textSize);
                setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == endPosition) {
            MarqueeTextView.this.scrollToPosition(0);
        }
    }

    public void start() {
        if (shouldStop.get()) {
            shouldStop.set(false);
            new Thread() {
                public void run() {
                    while (!shouldStop.get()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Message msg = handler.obtainMessage();
                        msg.what = 1;
                        msg.sendToTarget();
                    }
                }
            }.start();
        }
    }

    public void stop() {
        shouldStop.set(true);
    }

}
