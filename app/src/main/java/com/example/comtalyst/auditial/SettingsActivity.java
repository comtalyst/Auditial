package com.example.comtalyst.auditial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {
    private Auditial app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = ((Auditial) getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        String[] options = {"Reset","About"};

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, options);
        ListView listView = (ListView) findViewById(R.id.ListView_Settings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView)view).getText().toString();
                if(item.equals("Reset")){
                    app.table = new Row[1005];
                    app.rowcount = 0;
                    app.balance = 0.0;
                    app.save(getApplicationContext());
                    Toast.makeText(getApplicationContext(), "Reset Completed!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent in = new Intent(getApplicationContext(),AboutActivity.class);
                    startActivity(in);
                }
            }
        });
    }
}
