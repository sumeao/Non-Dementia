package com.example.myapplication0316;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class newExercise extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner exerciseSpinner;
    private Button startButton;
    private TextView timerTextView;
    private VideoView videoView;

    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;

    private String[] exercises = {
            "躺姿─雙腿彎曲抬臀運動",
            "躺姿─大腿伸直抬高(左右腳輪流)",
            "坐姿─直膝抬小腿(左右腳輪流)",
            "坐姿─大腿輪流上提抬高左右輪流",
            "站姿─腳尖踮起放下",
            "站姿─坐下/起立"
    };

    private int[] exerciseVideos = {
            R.raw.img_2136_0,
            R.raw.img_2137_1,
            R.raw.img_2138_2,
            R.raw.img_2139_3,
            R.raw.img_2140_4,
            R.raw.img_2141_5
    };

    private int selectedExerciseIndex;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        exerciseSpinner = findViewById(R.id.exerciseSpinner);
        startButton = findViewById(R.id.startButton);
        timerTextView = findViewById(R.id.timerTextView);
        videoView = findViewById(R.id.videoView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, exercises);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseSpinner.setAdapter(adapter);

        exerciseSpinner.setOnItemSelectedListener(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    resetTimer();
                } else {
                    startExercise();
                }
            }
        });
    }

    private void startExercise() {
        if (isTimerRunning) {
            return;
        }

        long exerciseTimeMinutes = getExerciseTimeMinutes();

        if (exerciseTimeMinutes <= 0) {
            return;
        }

        long exerciseTimeMillis = exerciseTimeMinutes * 60 * 1000;

        countDownTimer = new CountDownTimer(exerciseTimeMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60;
                String time = String.format("%02d:%02d", minutes, seconds);
                timerTextView.setText(time);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("00:00");
                stopExerciseVideo();
                showExerciseCompletion();
                Toast.makeText(newExercise.this, "運動完成！", Toast.LENGTH_SHORT).show();
                isTimerRunning = false;
            }
        };

        countDownTimer.start();
        isTimerRunning = true;

        startButton.setText("停止運動");
        exerciseSpinner.setEnabled(false);
        timerTextView.setVisibility(View.VISIBLE);
        videoView.setVisibility(View.VISIBLE);
        playExerciseVideo();
    }

    private void resetTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;

        startButton.setText("開始運動");
        exerciseSpinner.setEnabled(true);
        timerTextView.setVisibility(View.GONE);
        videoView.setVisibility(View.GONE);
        timerTextView.setText("00:00");
        stopExerciseVideo();
    }

    private long getExerciseTimeMinutes() {
        String timeText = ((TextView) findViewById(R.id.timeEditText)).getText().toString().trim();

        if (timeText.isEmpty()) {
            return 0;
        }

        try {
            return Long.parseLong(timeText);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void showExerciseCompletion() {
        // 在此显示运动完成的消息
    }

    private void playExerciseVideo() {
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + exerciseVideos[selectedExerciseIndex]);
        videoView.setVideoURI(videoUri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.start();
            }
        });
    }

    private void stopExerciseVideo() {
        videoView.stopPlayback();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedExerciseIndex = position;
        if (isTimerRunning) {
            resetTimer();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }
}