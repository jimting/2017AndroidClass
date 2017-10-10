package jt.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Play extends AppCompatActivity
{
    String songStr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        TextView songTV = (TextView) findViewById(R.id.textView1);
        songStr = getIntent().getStringExtra("song");
        songTV.setText(songStr);
    }
}

