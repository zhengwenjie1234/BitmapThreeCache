package com.bj.bitmapthreecache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 三级缓存之网络缓存
 * Created by 李蓉 on 2017/11/8.
 */

public class NetCacheUtils {

    private MemoryCacheUtils memoryCacheUtils;
    private LocalCacheUtils localCacheUtils;

    public NetCacheUtils(){
        super();
    }

    public NetCacheUtils(MemoryCacheUtils memoryCacheUtils, LocalCacheUtils localCacheUtils) {
        this.memoryCacheUtils = memoryCacheUtils;
        this.localCacheUtils = localCacheUtils;
    }

    /**
     * 从网络下载图片
     *
     * @param ivPic
     * @param url
     */
    public void getBitmapFromNet(ImageView ivPic, String url) {
        new BitmapTask().execute(ivPic, url);// 启动AsyncTask,
        // 参数会在doInbackground中获取
    }

    /**
     * Handler和线程池的封装
     *
     * 第一个泛型: 参数类型 第二个泛型: 更新进度的泛型, 第三个泛型是onPostExecute的返回结果
     *
     *
     */
    class BitmapTask extends AsyncTask<Object, Void, Bitmap> {

        private ImageView ivPic;
        private String url;

        /**
         * 后台耗时方法在此执行, 子线程
         */
        @Override
        protected Bitmap doInBackground(Object... params) {
            ivPic = (ImageView) params[0];
            url = (String) params[1];

            ivPic.setTag(url);// 将url和imageview绑定

            return downloadBitmap(url);
        }

        /**
         * 更新进度, 主线程
         */
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        /**
         * 耗时方法结束后,执行该方法, 主线程
         */
        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                String bindUrl = (String) ivPic.getTag();
                if (url.equals(bindUrl)) {// 确保图片设定给了正确的imageview
                    ivPic.setImageBitmap(result);
                    localCacheUtils.setBitmaptoLocal(url, result);// 将图片保存在本地
                    memoryCacheUtils.setBitmaptoMemory(url, result);// 将图片保存在内存
                    Log.i("xxx","从网络缓存读取图片啦...");
                }
            }
        }
    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     */
    private Bitmap downloadBitmap(String url) {

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();

                //图片压缩处理
                BitmapFactory.Options option = new BitmapFactory.Options();

                option.inSampleSize = 2;//宽高都压缩为原来的二分之一, 此参数需要根据图片要展示的大小来确定
                option.inPreferredConfig = Bitmap.Config.RGB_565;//设置图片格式

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null, option);
                return bitmap;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return null;
    }

}
