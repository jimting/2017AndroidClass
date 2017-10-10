package jt.musicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    ArrayList<String> musicList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        musicList = findMusic();
        for (int i = 0; i < musicList.size(); i++) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            String musicName = musicList.get(i);
            item.put("song", musicName);
            String extensionName = musicName.substring(musicName.lastIndexOf('.') + 1);
            if (extensionName.equalsIgnoreCase("mp3"))
                item.put("icon", R.drawable.mp3);
            else if (extensionName.equalsIgnoreCase("wma"))
                item.put("icon", R.drawable.wma);
            list.add(item);
        }
        String from[] = {"icon", "song"};
        int to[] = {R.id.imageView1, R.id.textView1};
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.list, from, to);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    private ArrayList<String> findMusic()
    {
        ArrayList<String> playList=new ArrayList<String>();
        File dir= Environment.getExternalStorageDirectory();
        File [] fileList=dir.listFiles();
        for(File file : fileList ){
            if(file.isFile()){
                String fileName=file.getName();
                String extensionName=fileName.substring(fileName.lastIndexOf('.')+1);
                if(extensionName.equalsIgnoreCase("mp3")
                        ||extensionName.equalsIgnoreCase("wma"))
                    playList.add(fileName);
            }
        }
        return playList;
    }
    @Override
    public void onItemClick(AdapterView<?> parent,View view,int position,long id)
    {
        Log.i("list", "您選了:" + musicList.get(position) + "這首歌");
        Intent intent = new Intent();
        intent.setClass(this, Play.class);
        intent.putExtra("song",musicList.get(position));
        startActivity(intent);
    }


}


