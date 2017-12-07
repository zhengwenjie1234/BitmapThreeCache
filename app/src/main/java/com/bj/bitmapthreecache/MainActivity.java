package com.bj.bitmapthreecache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.iv);
        final String path = "http://www.quanjing.com/image/2017index/lx1.png";
        final MyBitmapUtils myBitmapUtils = new MyBitmapUtils();
        myBitmapUtils.display(imageView,path);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myBitmapUtils.display(imageView,path);
            }
        });
    }
}
