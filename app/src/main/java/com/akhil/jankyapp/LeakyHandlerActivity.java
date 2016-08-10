package com.akhil.jankyapp;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;

public class LeakyHandlerActivity extends BaseAppCompatActivity {
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.counter)
    TextView counter;
    private Integer progressCount=0;
    private Handler mHander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_counter);
        if(savedInstanceState!=null) {
            progressCount =savedInstanceState.getInt("progressCount",0);
        }
        mHander=new Handler();
        updateProgressUsingThread();
    }

    private void updateProgressUsingThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < AppConstants.LOOP_COUNT; i++) {
                        try {
                            Thread.sleep(AppConstants.THREAD_SLEEP_TIME);
                            progressCount++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        publishProgress(progressCount);
                    }
            }
        }).start();
    }

    private void publishProgress(final Integer progressCount) {
        mHander.post(new Runnable() {
            @Override
            public void run() {
                if(!isFinishing()) {
                    counter.setText("" + progressCount);
                    LeakyHandlerActivity.this.progress.setProgress(progressCount);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("progressCount",progressCount);
        super.onSaveInstanceState(outState);
    }
}