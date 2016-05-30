package com.example.administrator.towrd;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_cl);
        InputStream open = null;
        try {
             open = getAssets().open("1.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("${name}", "panxianfeng");
                param.put("${zhuanye}", "computer");
                param.put("${sex}", "male");
                param.put("${school_name}", "他打鱼的jia");
                param.put("${date}", new Date().toString());

                Map<String,Object> header = new HashMap<String, Object>();
                header.put("width", 100);
                header.put("height", 150);
                header.put("type", "jpg");
                try {
                    header.put("content", WordUtil.inputStream2ByteArray(new FileInputStream("/storage/sdcard0/1.jpg"), true));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                param.put("${header}", header);

                CustomXWPFDocument doc = (CustomXWPFDocument) WordUtil.generateWord(param, " file:///android_asset/1.docx");
                FileOutputStream fopts = null;
                try {
                    fopts = new FileOutputStream("/storage/sdcard0/2.docx");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    doc.write(fopts);
                    fopts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
