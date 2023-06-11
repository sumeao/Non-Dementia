package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Eat extends AppCompatActivity {
    ListView listView;
    ArrayList<Integer> RecipePicture = new ArrayList<Integer>();
    ArrayList<String> RecipeName = new ArrayList<>();
    ArrayList<String> RecipeVideo = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_eat);
        listView = findViewById(R.id.Eat);

        RecipePicture.add(R.drawable.fishveg);
        RecipePicture.add(R.drawable.chickensand);
        RecipePicture.add(R.drawable.tomatoveg);
        RecipePicture.add(R.drawable.redmushroom);
        RecipePicture.add(R.drawable.spainshrimp);
        RecipePicture.add(R.drawable.lemonpotato);
        RecipePicture.add(R.drawable.tomatofish);
        RecipePicture.add(R.drawable.fcb);

        RecipeName.add("希臘鮭魚佐蔬菜");
        RecipeName.add("地中海雞肉三明治");
        RecipeName.add("番茄蔬菜燉飯");
        RecipeName.add("蒜炒紅椒蘑菇花");
        RecipeName.add("西班牙大蒜蝦");
        RecipeName.add("希臘式檸檬馬鈴薯");
        RecipeName.add("蔬菜番茄燉魚");
        RecipeName.add("法式三文魚麵包");

        RecipeVideo.add("https://www.youtube.com/watch?v=9fVfv_n9IYk");
        RecipeVideo.add("https://www.youtube.com/watch?v=RvZlCqPClz0");
        RecipeVideo.add("https://www.youtube.com/watch?v=Go9CY7oHt7w");
        RecipeVideo.add("https://www.youtube.com/watch?v=zmXxuYShKV0");
        RecipeVideo.add("https://www.youtube.com/watch?v=TOLA2-E4S40");
        RecipeVideo.add("https://www.youtube.com/watch?v=SA5akYWPivc");
        RecipeVideo.add("https://www.youtube.com/watch?v=ssna61T9tOM");
        RecipeVideo.add("https://www.youtube.com/watch?v=272eICABlIc");

        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return RecipeName.size();
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
                View ReLayout = View.inflate(Eat.this,R.layout.recipe_list,null);
                View layout = ReLayout.findViewById(R.id.layout_recipe);
                TextView TvName = ReLayout.findViewById(R.id.ExName);
                ImageView IvPhoto = ReLayout.findViewById(R.id.photo);
                ImageButton IbVideo = ReLayout.findViewById(R.id.BtVideo);
                TvName.setText(RecipeName.get(position));
                IvPhoto.setImageResource(RecipePicture.get(position));
                IbVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri uri = Uri.parse(RecipeVideo.get(position));
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