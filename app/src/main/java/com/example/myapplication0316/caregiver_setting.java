package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class caregiver_setting extends AppCompatActivity {

    ImageView sethead, save;
    private Uri selectedImageUri;
    private SQLiteDatabase db;
    Uri imageuri;
    private static final int REQUEST_IMAGE_PICKER = 1;
    private static String careName;
    private static String carePhone;
    private static String careMail;
    private static String carePatient;
    private Uri croppedImageUri; // 全局變量，用於保存裁剪後的圖片路徑


    EditText EditcareName, EditcarePhone, EditcarePatient, EditcareMail;
    String account;
    private byte[] imageData;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caregiver_setting);

        EditcareName = findViewById(R.id.editTextTextPersonName);
        EditcarePhone = findViewById(R.id.editTextTextPersonNumber);
        EditcareMail = findViewById(R.id.editTextTextPersonAddress);
        EditcarePatient = findViewById(R.id.editTextTextPersonDisease1);
        sethead = findViewById(R.id.sethead);
        save = findViewById(R.id.save);

        SqlDataBaseHelper dbHelper = new SqlDataBaseHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase(); // 開啟資料庫


        // 构建查询语句
        String[] projection = {
                "account",
                "name",
                "phone",
                "patient1",
                "email",
                "photo"
        };

        String selection = ""; // 可以使用适当的条件
        String[] selectionArgs = new String[0]; // 如果有条件，提供相应的参数
        String sortOrder = ""; // 可以指定排序顺序

        // 执行查询
        Cursor c = db.query(
                "caregiver",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        // 遍历查询结果
        if (c.moveToFirst()) {
            String caregiverName = c.getString(c.getColumnIndexOrThrow("name"));
            String caregiverPhone = c.getString(c.getColumnIndexOrThrow("phone"));
            String caregiverPatient = c.getString(c.getColumnIndexOrThrow("patient1"));
            String caregiverEmail = c.getString(c.getColumnIndexOrThrow("email"));
            imageData = c.getBlob(c.getColumnIndexOrThrow("photo"));

//            System.out.println("=================================");
//            System.out.println(imageData);

            if (imageData != null) {
                bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
                sethead.setImageBitmap(bitmap);
            }

            account = c.getString(c.getColumnIndexOrThrow("account"));

            EditcareName.setText(caregiverName);
            EditcarePhone.setText(caregiverPhone);
            EditcareMail.setText(caregiverEmail);
            EditcarePatient.setText(caregiverPatient);


            careName = caregiverName;
            carePhone = caregiverPhone;
            careMail = caregiverEmail;
            carePatient = caregiverPatient;

        }


        // 关闭游标和数据库连接
        c.close();


        sethead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_PICKER);
            }
        });

        EditcareName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        EditcarePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        EditcareMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        EditcarePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                careName = EditcareName.getText().toString();
                carePhone = EditcarePhone.getText().toString();
                careMail = EditcareMail.getText().toString();
                carePatient = EditcarePatient.getText().toString();

                // 將裁剪後的圖片轉換為位元組數組
                if (croppedImageUri != null) {
                    imageData = getByteArrayFromUri(croppedImageUri);
                }
                System.out.println("====================================================");
                System.out.println(croppedImageUri);
                System.out.println(imageData);
                // 將位元組數組插入到資料庫的 photo 欄位
                ContentValues values = new ContentValues();
                values.put("name", careName);
                values.put("phone", carePhone);
                values.put("email", careMail);
                values.put("patient1", carePatient);
                values.put("photo", imageData);

//                System.out.println("===============================");
//                System.out.println(careName);
//                System.out.println(imageData);

                String tableName = "caregiver";
                String selection = "account = ?";
                String[] selectionArgs = new String[]{account};

                int rowsAffected = db.update(tableName, values, selection, selectionArgs);


                if (rowsAffected > 0) {
                    Toast.makeText(view.getContext(), "資料更新成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(caregiver_setting.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(view.getContext(), "資料更新失敗", Toast.LENGTH_LONG).show();
                }
                db.close();

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                selectedImageUri = data.getData();
                // 啟動圖片裁剪
                startImageCrop(selectedImageUri);
            }
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                if (result != null) {
                    croppedImageUri = result.getUri();
                    // 在這裡處理裁剪後的圖片
                    sethead.setImageURI(croppedImageUri);
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                System.out.println("----------------------------------------");
                System.out.println(error);
                // 處理裁剪錯誤
            }
        }
    }

    private void startImageCrop(Uri sourceUri) {
        // 建立裁剪後的圖片保存路徑
        File cropFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "crop_image.jpg");
        croppedImageUri = Uri.fromFile(cropFile);
        File pictureDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        String picturePath = pictureDir.getAbsolutePath();
        Log.d("ImagePath", "Picture Directory: " + picturePath);


        // 跳轉至裁剪界面
        CropImage.activity(sourceUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1) // 設定裁剪比例為 1:1，即圓形裁剪
                .setCropShape(CropImageView.CropShape.OVAL) // 設定裁剪形狀為圓形
                .setFixAspectRatio(true) // 固定裁剪比例
                .start(this);
    }

    private byte[] getByteArrayFromUri(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            inputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            System.out.println("============================");
            e.printStackTrace();
            return null;
        }
    }


}