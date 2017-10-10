package jt.unitconversion;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    int unit1, unit2;
    double input, output;
    double unit_tran[] = { 1.0, 100.0, 10000.0, 2.54, 30.48, 160930, 91.440 };
    double unit_tran_inv[] = { 1.0, 0.01, 0.00001, 0.3937, 0.032808, 0.010936,0.0000062139 };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(this);
    }
    public void onClick(View v) {
        Spinner sp1 = (Spinner) findViewById(R.id.spinner);
        Spinner sp2 = (Spinner) findViewById(R.id.spinner2);
        EditText et = (EditText) findViewById(R.id.editText);
        TextView tv = (TextView) findViewById(R.id.textView);
        unit1 = sp1.getSelectedItemPosition();
        unit2 = sp2.getSelectedItemPosition();
        String str=et.getText().toString();
        if(str.length()>0){
            input = Double.parseDouble(str);
            output = input *unit_tran[unit1] * unit_tran_inv[unit2];
            tv.setText("=" + String.valueOf(output));
        }
    }
}

