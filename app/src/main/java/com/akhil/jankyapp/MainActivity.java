package com.akhil.jankyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;

public class MainActivity extends BaseAppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;

    final String[] items = {
            LeakyAsyncTaskActivity.class.getSimpleName(),
            AsyncTaskActivity.class.getSimpleName(),
            LeakyHandlerActivity.class.getSimpleName(),
            HandlerActivity.class.getSimpleName(),
            LeakyStaticViewActivity.class.getSimpleName(),
    };
    final Class [] activitiesClasses = {
            LeakyAsyncTaskActivity.class,
            AsyncTaskActivity.class,
            LeakyHandlerActivity.class,
            HandlerActivity.class,
            LeakyStaticViewActivity.class,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView.setAdapter(new ArrayAdapter<>(this,R.layout.list_item, items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this,activitiesClasses[position]));
            }
        });
    }
}
