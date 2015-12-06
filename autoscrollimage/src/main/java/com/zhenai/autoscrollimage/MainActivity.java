package com.zhenai.autoscrollimage;

import android.database.Cursor;
import android.media.ImageReader;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterViewFlipper;
import android.widget.Button;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhenai.autoscrollimage.adapter.FlipperAdapter;
import com.zhenai.autoscrollimage.bean.ImageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动播放图片
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button preBtn;
    private Button nextBtn;
    private Button autoBtn;
    private AdapterViewFlipper adapterViewFlipper;

    private List<ImageInfo> list;
    private Cursor cursor;
    private int fileColumn;
    private int titleColumn;
    private int displayColumn;


    String[] columns = new String[]{MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.TITLE,
            MediaStore.Images.Media.DISPLAY_NAME};


    FlipperAdapter flipperAdapter;

    int maxIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initListener();


    }

    private void initData() {
        searchImages();
    }

    private void searchImages() {
        list = new ArrayList<>();

        cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, null);

        fileColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        titleColumn = cursor.getColumnIndex(MediaStore.Images.Media.TITLE);
        displayColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);



        ImageInfo imageInfo;
        String title;
        String path;
        while (cursor.moveToNext()) {
            title = cursor.getString(titleColumn);
            path = cursor.getString(fileColumn);
            imageInfo = new ImageInfo(path);
            list.add(imageInfo);
        }
       /* ImageInfo info1=new ImageInfo("http://img4.imgtn.bdimg.com/it/u=815953124,26087312&fm=21&gp=0.jpg");
        ImageInfo info2=new ImageInfo("http://5.26923.com/download/pic/000/282/37a5d7166255a9d5e5b635573edaf266.jpg");
        ImageInfo info3=new ImageInfo("http://image.xcar.com.cn/attachments/a/day_130709/2013070923_3b9f49e9b25948120c73Wr8Gwv8sodLh.jpg");
        ImageInfo info4=new ImageInfo("http://pic22.nipic.com/20120803/7981740_142432733000_2.jpg");
        ImageInfo info5=new ImageInfo("http://pic6.nipic.com/20100419/4177448_223742009725_2.jpg");
        ImageInfo info6=new ImageInfo("http://pic8.nipic.com/20100716/5208201_230507869386_2.jpg");

        list.add(info1);
        list.add(info2);
        list.add(info3);
        list.add(info4);
        list.add(info5);
        list.add(info6);*/

        maxIndex=list.size()-1;

    }

    private void initListener() {
        preBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        autoBtn.setOnClickListener(this);

    }

    private void initView() {

        preBtn = (Button) findViewById(R.id.prevBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        autoBtn = (Button) findViewById(R.id.audoBtn);
        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.filpper);
        adapterViewFlipper.setFlipInterval(1000);
        flipperAdapter=new FlipperAdapter(this,list);
        adapterViewFlipper.setAdapter(flipperAdapter);


        adapterViewFlipper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==maxIndex)
                    adapterViewFlipper.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.prevBtn:
                showPreImage();
                break;

            case R.id.nextBtn:
                showNextImage();
                break;

            case R.id.audoBtn:
                autoScanImage();
                break;

            default:
                break;
        }
    }

    private void showPreImage() {
        adapterViewFlipper.showPrevious();
        adapterViewFlipper.stopFlipping();//停止自动播放
    }

    private void showNextImage() {
        adapterViewFlipper.showNext();
        adapterViewFlipper.stopFlipping();
    }

    private void autoScanImage() {
        adapterViewFlipper.startFlipping();
    }
}
