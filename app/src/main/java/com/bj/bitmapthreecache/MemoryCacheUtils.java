package com.bj.bitmapthreecache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 图片三级缓存之内存缓存
 * 使用Lrucache来做缓存
 * Created by 李蓉 on 2017/11/8.
 */

public class MemoryCacheUtils {

//    private LruCache<String,SoftReference<Bitmap>> lruCache = new LruCache<>(1024*1024*4);

    private LruCache<String,Bitmap> lruCache;

    public MemoryCacheUtils(){
        long maxMemory = Runtime.getRuntime().maxMemory()/8;
        lruCache = new LruCache<String, Bitmap>((int)maxMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //获取图片占用内存的大小
                int byteCount = value.getRowBytes() * value.getHeight();
                return byteCount;
            }
        };
    }

    /**
     * 从内存读
     */
    public Bitmap getBitmapfromMemory(String url){
        return lruCache.get(url);
    }

    /**
     * 写进内存
     */
    public void setBitmaptoMemory(String url,Bitmap bitmap){
        lruCache.put(url,bitmap);
    }

}
