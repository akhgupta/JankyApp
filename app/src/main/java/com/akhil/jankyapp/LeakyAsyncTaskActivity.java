package com.akhil.jankyapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;

public class LeakyAsyncTaskActivity extends BaseAppCompatActivity {

    private static final String TAG = "LeakyAsyncTaskActivity";
    @BindView(R.id.progress)
    ProgressBar progress;
    @BindView(R.id.counter)
    TextView counter;
    private Integer progressCount=0;
    private AsyncTask<Void, Integer, Void> mProgressAsyncTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_counter);
        if(savedInstanceState!=null) {
            progressCount =savedInstanceState.getInt("progressCount",0);
        }
        updateProgressAsyncTask();
    }

    private void updateProgressAsyncTask() {
        mProgressAsyncTask=new AsyncTask<Void, Integer, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < AppConstants.LOOP_COUNT; i++) {
                        try {
                            Thread.sleep(AppConstants.THREAD_SLEEP_TIME);
                            progressCount++;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        publishProgress(progressCount);
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                updateCounter(values[0]);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void updateCounter(Integer progress) {
        if(!isFinishing()) {
            counter.setText("" + progress);
            this.progress.setProgress(progress);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("progressCount",progressCount);
        super.onSaveInstanceState(outState);
    }
}