package com.example.comtalyst.auditial;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Date;

public class TableActivity extends Activity {
    private Auditial app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        app = ((Auditial) getApplication());
        super.onCreate(savedInstanceState);
        Refresh();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Refresh();
    }
    public void Refresh(){
        int i;
        setContentView(R.layout.activity_table);
        for (i = 1; i <= app.rowcount; i++) {
            addRow(app.table[i].date, app.table[i].type, app.table[i].amount, app.table[i].tag, app.table[i].des);
            System.out.println(app.table[i].date + " ");
            System.out.println(app.table[i].type + " ");
            System.out.println(app.table[i].amount + " ");
            System.out.println(app.table[i].tag + " ");
            System.out.println(app.table[i].des + " ");
        }
    }
    public void RowClick(View view) {
        TableRow row = (TableRow) view;
        Toast.makeText(getApplicationContext(), row.getContentDescription().toString(), Toast.LENGTH_LONG).show();
    }

    public void TagClick(View view) {
        TextView tag = (TextView) view;
        Toast.makeText(getApplicationContext(), tag.getContentDescription().toString(), Toast.LENGTH_LONG).show();
    }

    public int getPx(int dimensionDp) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }

    private void addRow(String date, boolean type, double amount, int tagcolor, String des) {
        //Create a tableLayout and its params
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tablelayout);


        //Create tableRow
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, getPx(40)));
        tableRow.setContentDescription(des);
        tableRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RowClick(v);
            }
        });
        tableRow.setWeightSum(335);

        //TextView : Date
        TextView textView = new TextView(this);
        TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, getPx(40));
        params.weight = 110;
        textView.setText(date);
        textView.setLayoutParams(params);
        textView.setTextSize((int) (getResources().getDimension(R.dimen.tabdatesize) / getResources().getDisplayMetrics().density));
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        textView.setGravity(Gravity.CENTER);

        tableRow.addView(textView);

        //TextView : Type
        textView = new TextView(this);
        params.weight = 45;
        params.height = TableRow.LayoutParams.MATCH_PARENT;
        if (type) {
            textView.setText("+");
            textView.setBackgroundColor(getResources().getColor(R.color.bg_add));
        } else {
            textView.setText("-");
            textView.setBackgroundColor(getResources().getColor(R.color.bg_dec));
        }
        textView.setLayoutParams(params);
        textView.setTextSize(30);
        textView.setTextColor(Color.parseColor("#FFFFFF"));

        textView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        textView.setGravity(Gravity.CENTER);

        tableRow.addView(textView);

        //TextView : Amount
        textView = new TextView(this);
        params.weight = 130;
        textView.setText(new DecimalFormat("#,###,###.##").format(amount));
        textView.setLayoutParams(params);
        textView.setTextSize((int) (getResources().getDimension(R.dimen.tabmonsize) / getResources().getDisplayMetrics().density));
        textView.setTextColor(Color.parseColor("#000000"));
        textView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        textView.setGravity(Gravity.CENTER);

        tableRow.addView(textView);

        //TextView : Tag
        textView = new TextView(this);
        params.weight = 50;
        textView.setText("");
        textView.setLayoutParams(params);
        textView.setBackgroundColor(tagcolor);
        textView.setContentDescription("Tag Description");
        /*textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TagClick(v);
            }
        });*/

        tableRow.addView(textView);


        //Add tableRow to tableLayout
        tableLayout.addView(tableRow);
    }
}