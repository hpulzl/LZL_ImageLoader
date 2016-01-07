package lzl.edu.com.bitmapcachedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import lzl.edu.com.bitmapcachedemo.adapter.ImageViewAdapter;

public class MainActivity extends AppCompatActivity {
    private ImageViewAdapter ivAdapter;
    private List<String> urlList;
    private GridView mGirdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }
    private void initView(){
        mGirdView = (GridView) findViewById(R.id.mGridView);
        urlList = getImageUrl();
        ivAdapter = new ImageViewAdapter(this,urlList);
        mGirdView.setAdapter(ivAdapter);
    }
    private List<String> getImageUrl(){
       List<String> list = new ArrayList<>();
        list.add("http://img4.imgtn.bdimg.com/it/u=128811874,840272376&fm=21&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=1183223528,3058066243&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=3965306929,1867766385&fm=21&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=48252272,1629415252&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=128811874,840272376&fm=21&gp=0.jpg");
        list.add("http://img3.imgtn.bdimg.com/it/u=3552972128,3819830255&fm=21&gp=0.jpg");
        list.add("http://img5.imgtn.bdimg.com/it/u=183656797,1730936710&fm=21&gp=0.jpg");
        list.add("http://img4.imgtn.bdimg.com/it/u=1414293996,3193615352&fm=21&gp=0.jpg");
        return list;
    }
}

