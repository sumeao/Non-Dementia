package com.example.myapplication0316;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class ExercisePatient extends AppCompatActivity {
    private ImageButton BtnVideo;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_patient);

        BtnVideo = findViewById(R.id.BtnVideo);

//        WebView web_exercise = findViewById(R.id.web_exercise);
//        web_exercise.getSettings().setJavaScriptEnabled(true);
//        web_exercise.loadUrl("https://www.youtube.com/watch?v=HjVHmoPsdNk");
        YouTubePlayerView youTubePlayerView = findViewById(R.id.youtube);
        getLifecycle().addObserver(youTubePlayerView);

//        videoView = findViewById(R.id.videoView);

//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
        BtnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if(intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(intent,200);
                }
            }
        });
    }
}
