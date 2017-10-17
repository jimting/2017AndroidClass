package jt.musicplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class Play extends Activity implements View.OnClickListener, View.OnTouchListener, MediaPlayer.OnCompletionListener
{
    String songStr;
    MediaPlayer player;
    SeekBar seekBar;
    Button playButton, stopButton;
    UpdateSeekBar update;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        TextView songTV = (TextView)findViewById(R.id.textView1);
        songStr = getIntent().getStringExtra("song");
        songTV.setText(songStr);
        playButton=(Button)findViewById(R.id.button1);        stopButton=(Button)findViewById(R.id.button2);        seekBar=(SeekBar)findViewById(R.id.seekBar1);
        playButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        seekBar.setOnTouchListener(this);
        update=new UpdateSeekBar();
        player = new MediaPlayer();
        play();
        player.setOnCompletionListener(this);
    }
    class UpdateSeekBar implements Runnable
    {
        @Override
        public void run()
        {
            seekBar.setProgress(player.getCurrentPosition());
            handler.postDelayed(this, 1000);
        }
    }

    void play()
    {
        player.reset();
        try {
            player.setDataSource(Environment.getExternalStorageDirectory()+"/"+songStr);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        seekBar.setMax(player.getDuration());
        player.start();
        handler.post(update);
        playButton.setText("暫停");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            if(player.isPlaying())
                player.seekTo(seekBar.getProgress());
        }
        return false;
    }
    @Override
    public void onCompletion(MediaPlayer mp)
    {
        playButton.setText("播放");
        handler.removeCallbacks(update);
        seekBar.setProgress(0);
    }


    @Override
    public void onClick(View v)
    {
        if(v.getId()==R.id.button1)
        {
            if(player.isPlaying())
            {
                player.pause();
                handler.removeCallbacks(update);
                playButton.setText("繼續");
            }
            else if(playButton.getText().equals("繼續"))
            {
                player.seekTo(seekBar.getProgress());
                player.start();
                handler.post(update);
                playButton.setText("暫停");
            }
            else
            {
                play();
            }
        }
        else
        {
            player.stop();
            playButton.setText("播放");
            seekBar.setProgress(0);
            handler.removeCallbacks(update);
        }
    }
    @Override
    protected void onDestroy()
    {
        handler.removeCallbacks(update);
        player.stop();
        player.release();
        super.onDestroy();
    }
}


