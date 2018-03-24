package com.example.divya.dice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DiceActivity extends AppCompatActivity {
    public static int cal_val=0,total_user=0,total_comp=0;
    Button roll,reset,hold;
    ImageView img;
    TextView user,computer;
    Random r=new Random();
    boolean con=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);
        roll=(Button)findViewById(R.id.roll);
        reset=(Button)findViewById(R.id.hold);
        hold=(Button)findViewById(R.id.hold);
        img=(ImageView)findViewById(R.id.imageView);
        user=(TextView)findViewById(R.id.user);
        computer=(TextView)findViewById(R.id.computer);
    }
    public void onRoll(View v) {
        int i = r.nextInt(7);
        switch (i) {
            case 1:
                cal_val = 0;
                img.setImageResource(R.drawable.dice1);
                user_mode();
                break;
            case 2:
                img.setImageResource(R.drawable.dice2);
                cal_val += 2;
                break;
            case 3:
                img.setImageResource(R.drawable.dice3);
                cal_val += 3;
                break;
            case 4:
                img.setImageResource(R.drawable.dice4);
                cal_val += 4;
                break;
            case 5:
                img.setImageResource(R.drawable.dice5);
                cal_val += 5;
                break;
            case 6:
                img.setImageResource(R.drawable.dice6);
                cal_val += 6;
                break;
        }
    }
    public void user_mode()
    {
        total_user+=cal_val;
        cal_val=0;
        user.setText(total_user+"");
        if(total_user>=100) {
            Toast.makeText(DiceActivity.this, "You Are Winner", Toast.LENGTH_LONG).show();
            onReset(reset);
        }
        com_cal();
    }
    public void onHold(View v)
    {
        roll.setEnabled(false);
        hold.setEnabled(false);
        user_mode();
    }
    public void onReset(View v)
    {
        total_comp=0;
        total_user=0;
        user.setText("0");
        computer.setText("0");
        hold.setEnabled(true);
        roll.setEnabled(true);
        img.setImageResource(R.drawable.dice6);
    }
    Handler h=new Handler();
    Runnable Run=new Runnable() {
        @Override
        public void run() {
            con = true;
            int i = r.nextInt(7);
            switch (i) {
                case 1:
                    cal_val = 0;
                    img.setImageResource(R.drawable.dice1);
                    con = false;
                    break;
                case 2:
                    img.setImageResource(R.drawable.dice2);
                    cal_val += 2;
                    break;
                case 3:
                    img.setImageResource(R.drawable.dice3);
                    cal_val += 3;
                    break;
                case 4:
                    img.setImageResource(R.drawable.dice4);
                    cal_val += 4;
                    break;
                case 5:
                    img.setImageResource(R.drawable.dice5);
                    cal_val += 5;
                    break;
                case 6:
                    img.setImageResource(R.drawable.dice6);
                    cal_val += 6;
                    break;
            }
            if (cal_val < 10 && con)
                h.postDelayed(this, 500);
            else computer_mode();
        }
    };
    public void com_cal() {
        h.postDelayed(Run, 500);
    }
    public void computer_mode()
    {
        total_comp+=cal_val;
        cal_val=0;
        computer.setText(total_comp+"");
        if(total_comp>=100) {
            Toast.makeText(DiceActivity.this, "Computer is a Winner", Toast.LENGTH_LONG).show();
            onReset(reset);
        }
        hold.setEnabled(true);
        roll.setEnabled(true);
    }
}
