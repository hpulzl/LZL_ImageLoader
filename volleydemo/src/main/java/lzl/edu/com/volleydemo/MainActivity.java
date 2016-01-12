package lzl.edu.com.volleydemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import lzl.edu.com.volleydemo.util.NetWorkUtil;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private RequestQueue queue;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        requestView();
        requestImage();
    }
    //使用Volley访问网络图片
    private void requestImage() {
        mImageView = (ImageView) findViewById(R.id.mImageView);
        String url = "http://img2.imgtn.bdimg.com/it/u=2401995202,2506075514&fm=21&gp=0.jpg";
        ImageRequest request = new ImageRequest(url,new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }
        },0,0,null,new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mImageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
        queue.add(request);
    }

    private void requestView() {
        mTextView = (TextView) findViewById(R.id.mTextView);
        queue = Volley.newRequestQueue(this);
        String url = "http://www.baidu.com";
        if (NetWorkUtil.isNetWorkAvailable(this)) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    mTextView.setText("访问网络成功！");
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    mTextView.setText("连接访问失败！");
                }
            });
            queue.add(stringRequest);
        }else{
            Toast.makeText(this,"网络访问失败",Toast.LENGTH_SHORT).show();
        }
    }
}
