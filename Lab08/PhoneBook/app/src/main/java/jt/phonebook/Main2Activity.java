package jt.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText editMyName, editMyPhone;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        editMyName = (EditText) findViewById(R.id.editMyName);
        editMyPhone = (EditText) findViewById(R.id.editMyPhone);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent();
                i.putExtra("name", editMyName.getText().toString());
                i.putExtra("phone", editMyPhone.getText().toString());
                Main2Activity.this.setResult(0, i);
                Main2Activity.this.finish();
            }
        });
    }
}
