package jt.mymaptests;

import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,AdapterView.OnItemSelectedListener,CompoundButton.OnCheckedChangeListener
        ,DialogInterface.OnCancelListener{

    private GoogleMap mMap;
    Spinner spnMapType;
    CheckBox chkTraffic;
    String[] mapType = { "街道圖", "衛星圖", "衛星圖+街道圖", "地形圖" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        int errorCode= GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(errorCode!= ConnectionResult.SUCCESS)
            GooglePlayServicesUtil.showErrorDialogFragment(errorCode,this,111,this);
        else {
            spnMapType = (Spinner) findViewById(R.id.spinner1);
            chkTraffic = (CheckBox) findViewById(R.id.checkBox1);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mapType);
            spnMapType.setAdapter(adapter);
            spnMapType.setOnItemSelectedListener(this);
            chkTraffic.setOnCheckedChangeListener(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(25.150953, 121.780101);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:/* 一般街道模式 */
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 1:/* 衛星模式 */
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 2:/* 街道衛星混和模式 */
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case 3:/* 地形模式 */
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked)
            mMap.setTrafficEnabled(true);/* 顯示交通狀況 */
        else
            mMap.setTrafficEnabled(false);/* 不顯示交通狀況 */
    }
    @Override
    public void onCancel(DialogInterface dialog) {
        finish(); /* 將視窗關閉*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

