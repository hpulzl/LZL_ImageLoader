package lzl.edu.com.bitmapcachedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

import lzl.edu.com.bitmapcachedemo.R;
import lzl.edu.com.bitmapcachedemo.util.ImageLoader;

/**
 * Created by admin on 2016/1/7.
 */
public class ImageViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> urlList;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public ImageViewAdapter(Context context,List<String> list){
        this.mContext = context;
        this.urlList = list;
        inflater = LayoutInflater.from(mContext);
        imageLoader = new ImageLoader(context);
    }
    @Override
    public int getCount() {
        return urlList.size();
    }

    @Override
    public Object getItem(int position) {
        return urlList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UrlHolder holder = null;
        if(convertView ==null){
            convertView = inflater.inflate(R.layout.item_gridview_image,null);
            holder = new UrlHolder();
            holder.iv = (ImageView) convertView.findViewById(R.id.mImageView);
            convertView.setTag(holder);
        }else{
            holder = (UrlHolder) convertView.getTag();
        }
        ImageView imageView = holder.iv;
        String urlString = (String) getItem(position);
        imageLoader.bindBitmap(urlString,imageView,100,100);
        return convertView;
    }
    class UrlHolder {
        ImageView iv;
    }
}

