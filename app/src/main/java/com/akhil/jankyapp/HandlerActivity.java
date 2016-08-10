package com.akhil.jankyapp;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;

public class HandlerActivity extends BaseAppCompatActivity {
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.counter)
    TextView counter;
    private Integer progressCount=0;
    private Handler mHander;
    private Thread mThread;
    private boolean isCanceled=false;

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
        mThread=new Thread(new Runnable() {
            @Override
            public void run() {
                    for (int i = 0; i < AppConstants.LOOP_COUNT; i++) {
                        if(!isCanceled) {
                            try {
                            Thread.sleep(AppConstants.THREAD_SLEEP_TIME);
                            progressCount++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        publishProgress(progressCount);
                    }
                }
            }
        });
        mThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isCanceled=true;
        mHander.removeCallbacksAndMessages(null);
    }

    private void publishProgress(final Integer progressCount) {
        mHander.post(new Runnable() {
            @Override
            public void run() {
                if(!isFinishing()) {
                    counter.setText("" + progressCount);
                    HandlerActivity.this.progress.setProgress(progressCount);
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