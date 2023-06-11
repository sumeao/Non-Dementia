package com.example.myapplication0316;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class RememberUploadImage extends AppCompatActivity {

    private final static int PHOTO = 99;
    private DisplayMetrics mPhone;
    int bt = Integer.MAX_VALUE;
    int img[] = new int[16];
    Bitmap bmp[] = new Bitmap[16];
    Boolean isempty = true;
    File appDir = null;
    String appDirPath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember_upload_image);

        appDir = new File(this.getExternalFilesDir(null), "image_file/");
        appDirPath = appDir.getPath() + File.separator;

        try {
            setimgFile(appDir);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ImageButton button[] = new ImageButton[16];
        Button confirm;

        button[0] = findViewById(R.id.one_1);
        button[15] = findViewById(R.id.one_2);
        button[1] = findViewById(R.id.two_1);
        button[14] = findViewById(R.id.two_2);
        button[2] = findViewById(R.id.three_1);
        button[13] = findViewById(R.id.three_2);
        button[3] = findViewById(R.id.four_1);
        button[12] = findViewById(R.id.four_2);
        button[4] = findViewById(R.id.five_1);
        button[11] = findViewById(R.id.five_2);
        button[5] = findViewById(R.id.six_1);
        button[10] = findViewById(R.id.six_2);
        button[6] = findViewById(R.id.seven_1);
        button[9] = findViewById(R.id.seven_2);
        button[7] = findViewById(R.id.eight_1);
        button[8] = findViewById(R.id.eight_2);

        confirm = findViewById(R.id.confirm);

        for (int i = 0; i < 16; i++) {
            bt = i;
            int finalI = i;
            button[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //開啟相簿相片集，須由startActivityForResult且帶入requestCode進行呼叫，原因為點選相片後返回程式呼叫onActivityResult
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, finalI + 100);

                }
            });

        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 16; i++) {
                    if (img[i] != 1) {
                        isempty = true;
                        break;
                    } else {
                        isempty = false;
                    }
                }
                if (isempty) {
                    new AlertDialog.Builder(RememberUploadImage.this)
                            .setTitle("還有圖片還沒新增喔!!!")
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .show();
                } else {
                    File folder = new File(appDirPath);
                    if (folder == null || !folder.exists()) {
                        System.out.println("file is not here");
                    } else {
                        File[] files = folder.listFiles();
                        int fileCount = files.length;
                        System.out.println("-------------------------");
                        System.out.println(fileCount);
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RememberUploadImage.this, RememberFamily.class);
                            startActivity(intent);
                        }
                    });
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File path = new File(getApplicationInfo().dataDir);
        //藉由requestCode判斷是否為開啟相機或開啟相簿而呼叫的，且data不為null
        if (requestCode > 99 && requestCode < 116 && data != null) {

            //取得照片路徑uri
            Uri uri = data.getData();
            ContentResolver cr = this.getContentResolver();

            try {
                //讀取照片，型態為Bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                img[requestCode - 100] = 1;
                bmp[requestCode - 100] = bitmap;
                String s = Integer.toString(requestCode - 100);
                ScalePic(bitmap, requestCode - 100);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            addphoto(bitmap,s);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void ScalePic(Bitmap bitmap, int i) {
        ImageButton button[] = new ImageButton[16];
        button[0] = findViewById(R.id.one_1);
        button[15] = findViewById(R.id.one_2);
        button[1] = findViewById(R.id.two_1);
        button[14] = findViewById(R.id.two_2);
        button[2] = findViewById(R.id.three_1);
        button[13] = findViewById(R.id.three_2);
        button[3] = findViewById(R.id.four_1);
        button[12] = findViewById(R.id.four_2);
        button[4] = findViewById(R.id.five_1);
        button[11] = findViewById(R.id.five_2);
        button[5] = findViewById(R.id.six_1);
        button[10] = findViewById(R.id.six_2);
        button[6] = findViewById(R.id.seven_1);
        button[9] = findViewById(R.id.seven_2);
        button[7] = findViewById(R.id.eight_1);
        button[8] = findViewById(R.id.eight_2);

        button[i].setImageBitmap(bitmap);
    }

    private void setimgFile(File imageFileDir) throws IOException {

        imageFileDir.mkdirs();
        System.out.println("-------------------------");
        System.out.println("file set !!!");
    }

    private void addphoto(Bitmap bitmap,String s) throws IOException {
        // 要寫入的 BMP 圖檔資料
        byte[] bmpData = bitmapToByteArray(bitmap);

        try (FileOutputStream fos = new FileOutputStream(appDirPath+s,true)) {
            fos.write(bmpData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

}
