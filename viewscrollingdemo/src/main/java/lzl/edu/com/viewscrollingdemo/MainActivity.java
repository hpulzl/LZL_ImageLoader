package lzl.edu.com.viewscrollingdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";

    private Button demo1Btn;
    private Button demo2Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        demo1Btn = (Button) findViewById(R.id.demo1Btn);
        demo2Btn = (Button) findViewById(R.id.demo2Btn);

        demo1Btn.setOnClickListener(this);
        demo2Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = new Intent();
        switch (v.getId())
        {
            case R.id.demo1Btn:
                mIntent.setClass(MainActivity.this,DemoActivity_1.class);
                startActivity(mIntent);
                break;
            case R.id.demo2Btn:
                mIntent.setClass(MainActivity.this,DemoActivity_2.class);
                startActivity(mIntent);
                break;
            default:break;
        }
    }
}