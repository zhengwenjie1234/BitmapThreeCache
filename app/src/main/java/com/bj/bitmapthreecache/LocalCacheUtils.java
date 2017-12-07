package com.bj.bitmapthreecache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 三级缓存之本地缓存
 * Created by 李蓉 on 2017/11/8.
 */

public class LocalCacheUtils {

    private static final String CATCH_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/News";

    /**
     * 从本地读取图片
     */
    public Bitmap getBitmapfromLocal(String url){
        //把图片的url当做文件名，
        String fileName = null;
        try {
            fileName = MD5Encoder.encode(url); //将文件进行MD5加密
            return BitmapFactory.decodeStream(new FileInputStream(new File(CATCH_PATH,fileName)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存到本地
     * @param url
     * @param bitmap
     */
    public void setBitmaptoLocal(String url,Bitmap bitmap){
        try {
            String fileName = MD5Encoder.encode(url);
            File file = new File(CATCH_PATH,fileName); //把图片的url当做文件名
            //获取所有文件
            File parentFile = file.getParentFile();
            if(!parentFile.exists()){  //不存在就创建
                parentFile.mkdirs();
            }
            //图片保存到本地  质量压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
