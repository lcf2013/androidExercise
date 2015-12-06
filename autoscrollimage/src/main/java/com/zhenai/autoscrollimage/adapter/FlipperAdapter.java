package com.zhenai.autoscrollimage.adapter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.zhenai.autoscrollimage.MainActivity;
import com.zhenai.autoscrollimage.R;
import com.zhenai.autoscrollimage.bean.ImageInfo;

import java.io.File;
import java.util.List;

/**
 * Created by admin on 2015/12/6.
 */
public class FlipperAdapter extends BaseAdapter {
    private List<ImageInfo> images;
    private MainActivity context;

    public FlipperAdapter(MainActivity context, List<ImageInfo> list) {
        this.context = context;
        this.images = list;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = context.getLayoutInflater().inflate(R.layout.flipper_item, viewGroup, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.img);
            view.setTag(holder);
        } else {

            holder = (ViewHolder) view.getTag();
        }

        File file=new File(images.get(i).getPath());
        Uri uri= Uri.fromFile(file);
//        Uri uri= Uri.parse(images.get(i).getPath());

        ImageLoader.getInstance().displayImage(uri.toString(), holder.imageView,
                new DisplayImageOptions.Builder()
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .imageScaleType(ImageScaleType.NONE)
                        .cacheOnDisc()
                        .cacheInMemory()
                        .build());
        return view;
    }

    private class ViewHolder {
        public ImageView imageView;
    }
}
