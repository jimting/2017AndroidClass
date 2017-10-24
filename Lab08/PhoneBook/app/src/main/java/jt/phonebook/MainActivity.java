package jt.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnJump;
    private ListView listMyData;
    private ArrayAdapter<String> myDataAdapter;
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        switch (resultCode)
        {
            case 0:
                myDataAdapter.add(data.getStringExtra("name")+"-"+data.getStringExtra("phone"));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        btnJump = (Button) findViewById(R.id.btnJump);
        btnJump.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent();
                i.setClass(MainActivity.this, Main2Activity.class);
                startActivityForResult(i, 0);
            }
        });
        ArrayList<String> myDataList = new ArrayList<String>();
        myDataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 	myDataList);
        listMyData = (ListView) findViewById(R.id.listMyData);
        listMyData.setAdapter(myDataAdapter);
    }
}
