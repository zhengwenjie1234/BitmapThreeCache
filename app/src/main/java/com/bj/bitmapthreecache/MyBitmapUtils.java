package com.bj.bitmapthreecache;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by dell-pc on 2017/11/8.
 */

public class MyBitmapUtils {

    private LocalCacheUtils localCacheUtils;
    private MemoryCacheUtils memoryCacheUtils;
    private NetCacheUtils netCacheUtils;

    public MyBitmapUtils() {
        localCacheUtils =new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
        netCacheUtils = new NetCacheUtils(memoryCacheUtils,localCacheUtils);
    }

    public void display(ImageView img,String url){
        img.setImageResource(R.mipmap.ic_launcher);
        Bitmap bitmap;
        bitmap = memoryCacheUtils.getBitmapfromMemory(url);
        if(bitmap != null){
            Log.i("xxx","内存缓存");
            //将图片设置给组件
            img.setImageBitmap(bitmap);
            Log.i("xxx","从内存获取图片了...");
        }else{
            bitmap = localCacheUtils.getBitmapfromLocal(url);
            if(bitmap != null){
                Log.i("xxx","本地缓存");
                //将图片设置给组件
                img.setImageBitmap(bitmap);
                Log.i("xxx","从本地获取图片了...");
            }else{
                netCacheUtils.getBitmapFromNet(img,url);
            }

        }

    }

}
