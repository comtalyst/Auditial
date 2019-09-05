package com.example.comtalyst.auditial;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Auditial app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = ((Auditial) getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app.read(getApplicationContext());
        TextView balance = (TextView) findViewById(R.id.textView_balance);
        balance.setText(new DecimalFormat("#,###,###.##").format(app.balance));
    }
    public void DetailsClick(View view){
        Intent in = new Intent(getApplicationContext(),TableActivity.class);
        startActivity(in);
    }
    public void AddClick(View view){
        Intent in = new Intent(getApplicationContext(),AddActivity.class);
        in.putExtra("type",true);
        startActivity(in);
    }
    public void DelClick(View view){
        Intent in = new Intent(getApplicationContext(),AddActivity.class);
        in.putExtra("type",false);
        startActivity(in);
    }
    public void SettingsClick(View view){
        Intent in = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(in);
    }

    @Override
    public void onResume() {
        super.onResume();
        app = ((Auditial) getApplication());
        TextView balance = (TextView) findViewById(R.id.textView_balance);
        balance.setText(new DecimalFormat("#,###,###.##").format(app.balance));
    }
}
