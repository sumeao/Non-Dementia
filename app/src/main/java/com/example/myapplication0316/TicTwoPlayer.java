package com.example.myapplication0316;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TicTwoPlayer extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_playerOneScore , tv_playerTwoScore , tv_playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame , home;

    private int playerOneScoreCount , playerTwoScoreCount , rountCount;
    boolean activePlayer;

//    p1 => 0
//    p2 => 1
//    empty => 2

    int [] gameStatus = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {
            {0,1,2} , {3,4,5} , {6,7,8} , //列
            {0,3,6} , {1,4,7} , {2,5,8} , //欄
            {0,4,8} , {2,4,6} //斜對角
    };

    String turn1 = "玩家 X 回合！";
    String turn2 = "玩家 O 回合！";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_two_player);

        setView();


        for(int i = 0; i < buttons.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID , "id" , getPackageName());
            //得到資源id,顯示一大串數字
            buttons[i] = findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        rountCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;



    }

    public void setView(){
        tv_playerOneScore = findViewById(R.id.tv_playerOneScore);
        tv_playerTwoScore = findViewById(R.id.tv_playerTwoScore);
        tv_playerStatus = findViewById(R.id.tv_playerStatus);
        resetGame = findViewById(R.id.btn_resetGame);
        home = findViewById(R.id.btn_back);

    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")) {
            return;
        }

        String buttonID = view.getResources().getResourceEntryName(view.getId());
        //得到id名稱
//        Log.i("id" , buttonID); // 按第1個 顯示btn_0

        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1 , buttonID.length()));
//        String test = String.valueOf(gameStatePointer);
//        Log.i("test" , test); //取位置 按第1個 顯示 0

        if(activePlayer){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#FFC34A"));
            gameStatus[gameStatePointer] = 0;
            tv_playerStatus.setText(turn2);
        }else {
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#70FFEA"));
            gameStatus[gameStatePointer] = 1;
            tv_playerStatus.setText(turn1);
        }

        rountCount++;

        if(checkWinner()){
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"玩家 X 獲勝！" , Toast.LENGTH_LONG).show();
                playAgain();
            }
            else {
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this,"玩家 O 獲勝！" , Toast.LENGTH_LONG).show();
                playAgain();
            }
        }
        else if(rountCount == 9){
            playAgain();
            Toast.makeText(this,"平手！" , Toast.LENGTH_LONG).show();
        }
        else {
            activePlayer = !activePlayer;
        }

        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                tv_playerStatus.setText(turn1);
                updatePlayerScore();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winningPosion : winningPositions){
            if(gameStatus[winningPosion[0]] == gameStatus[winningPosion[1]] &&
                    gameStatus[winningPosion[1]] == gameStatus[winningPosion[2]] &&
                    gameStatus[winningPosion[0]] != 2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    public void updatePlayerScore(){
        tv_playerOneScore.setText(Integer.toString(playerOneScoreCount));
        tv_playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }

    public void playAgain(){
        rountCount = 0;
        activePlayer = true;
        tv_playerStatus.setText(turn1);

        for(int i = 0; i < buttons.length; i++){
            gameStatus[i] = 2;
            buttons[i].setText("");
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}