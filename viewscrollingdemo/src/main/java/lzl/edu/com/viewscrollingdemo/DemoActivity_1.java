package lzl.edu.com.viewscrollingdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import lzl.edu.com.viewscrollingdemo.view.HorizontalScrollViewEX;

public class DemoActivity_1 extends AppCompatActivity {
    private HorizontalScrollViewEX mContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_activity_1);
        initView();
    }
    private void initView() {
        mContainer = (HorizontalScrollViewEX) findViewById(R.id.mContainers1);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) LayoutInflater.from(this).inflate
                    (R.layout.content_layout, mContainer, false);
            layout.getLayoutParams().width = width;
            TextView tv = (TextView) layout.findViewById(R.id.tv);
            tv.setText("第" + (i + 1) + "页");
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mContainer.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.lv);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            arrayList.add("数据" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_list_content, R.id.itemTv, arrayList);

        listView.setAdapter(adapter);
    }

}
