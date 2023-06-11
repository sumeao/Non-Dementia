package com.example.myapplication0316;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Camera;
import android.media.CamcorderProfile;
import android.media.Image;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;

import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class Exercise extends AppCompatActivity {

    ListView listView;
    ArrayList<String> ExName = new ArrayList<>();
    ArrayList<String> ExVideo = new ArrayList<>();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        listView = findViewById(R.id.Exercise);

        ExName.add("簡單手部運動1");
        ExName.add("簡單手部運動2");
        ExName.add("簡單手部運動3");
        ExName.add("簡單手部運動4");
        ExName.add("心肺運動");
        ExName.add("大腦保健體操");
        ExName.add("上下肢運動");


        ExVideo.add("https://www.youtube.com/watch?v=IzwuxstXycY");
        ExVideo.add("https://www.youtube.com/watch?v=HpTaqv31zcs");
        ExVideo.add("https://www.youtube.com/watch?v=HjVHmoPsdNk");
        ExVideo.add("https://www.youtube.com/watch?v=gLLdgBSwStw");
        ExVideo.add("https://www.youtube.com/watch?v=co-ih1HvDgw");
        ExVideo.add("https://www.youtube.com/watch?v=4HFPuGe1du4");
        ExVideo.add("https://www.youtube.com/watch?v=RX5_Hf5k-K4");




        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return ExName.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup viewGroup) {
                View ReLayout = View.inflate(Exercise.this,R.layout.exercise_list,null);
                TextView TvName = ReLayout.findViewById(R.id.ExName);
                ImageButton IbVideo = ReLayout.findViewById(R.id.BtVideo);
                TvName.setText(ExName.get(position));
                IbVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse(ExVideo.get(position));
                        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });
                return ReLayout;
            }
        };
        listView.setAdapter(baseAdapter);


    }
}
