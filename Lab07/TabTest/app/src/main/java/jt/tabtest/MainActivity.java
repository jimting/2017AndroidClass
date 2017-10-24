package jt.tabtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);
        TabHost host = (TabHost)findViewById(R.id.tabHost);        host.setup();
        TabHost.TabSpec spec;
        spec= host.newTabSpec("Tab1");
        spec.setIndicator("第一頁");
        spec.setContent(R.id.tab1);
        host.addTab(spec);
        spec= host.newTabSpec("Tab2");
        spec.setIndicator("第二頁");
        spec.setContent(R.id.tab2);
        host.addTab(spec);
        spec= host.newTabSpec("Tab3");
        spec.setIndicator("第三頁");
        spec.setContent(R.id.tab3);
        host.addTab(spec);
        host.setOnTabChangedListener(this);
    }

    @Override
    public void onTabChanged(String tabId)
    {
        Toast.makeText(MainActivity.this, tabId , Toast.LENGTH_LONG).show();
    }
}