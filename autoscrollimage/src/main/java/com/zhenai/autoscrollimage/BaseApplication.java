package com.zhenai.autoscrollimage;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by admin on 2015/12/6.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = StorageUtils.getCacheDirectory(this);
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCache(new UnlimitedDiscCache(cacheFile))
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO);
        ImageLoaderConfiguration config = builder.build();
        ImageLoader.getInstance().init(config);
    }
}
