package com.example.comtalyst.auditial;

import android.app.DialogFragment;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.woalk.apps.lib.colorpicker.ColorPickerDialog;
import com.woalk.apps.lib.colorpicker.ColorPickerSwatch;

import java.util.Date;

public class AddActivity extends AppCompatActivity {
    int mSelectedColor;
    TextView colorshow,typeshow,dateshow;
    EditText editamount,editdes;
    boolean type;
    private Auditial app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        app = ((Auditial) getApplication());
        mSelectedColor = Color.BLACK;
        typeshow = (TextView) findViewById(R.id.textview_whattype);
        editamount = (EditText) findViewById(R.id.editText_amount);
        editdes = (EditText) findViewById(R.id.editText_des);
        colorshow = (TextView) findViewById(R.id.textView_selectedcolor);
        dateshow = (TextView) findViewById(R.id.textview_whatdate);
        dateshow.setText(DateFormat.format("dd/MM/yyyy", new Date().getTime()).toString());
        colorshow.setBackgroundColor(getResources().getColor(R.color.hologray));
        Bundle extras = getIntent().getExtras();
        if(extras == null){
            type = true;
        }
        else{
            type = extras.getBoolean("type");
        }
        if(type){
            typeshow.setText("Add");
            typeshow.setTextColor(getResources().getColor(R.color.bg_add));
            editamount.setHint("How much money did you get today?");
            editdes.setHint("How did you get this money?");
        }
        else{
            typeshow.setText("Decrease");
            typeshow.setTextColor(getResources().getColor(R.color.bg_dec));
            editamount.setHint("How much money did you spend today?");
            editdes.setHint("Why did you lost this money?");
        }
    }
    public void chooseType(View view){
        if(type){
            type = false;
            typeshow.setText("Decrease");
            typeshow.setTextColor(getResources().getColor(R.color.bg_dec));
            editamount.setHint("How much money did you spend today?");
            editdes.setHint("Why did you lost this money?");
        }
        else{
            type = true;
            typeshow.setText("Add");
            typeshow.setTextColor(getResources().getColor(R.color.bg_add));
            editamount.setHint("How much money did you get today?");
            editdes.setHint("How did you get this money?");
        }
    }
    public void chooseColor(View view){
        ColorPickerDialog dialog = ColorPickerDialog.newInstance(
                "Select a Tag Color",
                new int[] { getResources().getColor(R.color.holored),getResources().getColor(R.color.holoorange),
                        getResources().getColor(R.color.holoyellow), getResources().getColor(R.color.hologreen),
                        getResources().getColor(R.color.hololblue),getResources().getColor(R.color.holodblue),
                        getResources().getColor(R.color.holopurple),getResources().getColor(R.color.hologray)},
                mSelectedColor,
                4, // Number of columns
                ColorPickerDialog.SIZE_SMALL
        );
        dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener(){

            @Override
            public void onColorSelected(int color) {
                mSelectedColor = color;
                colorshow.setBackgroundColor(mSelectedColor);
            }

        });
        dialog.show(getFragmentManager(), "tag");
    }
    public void chooseDate(View view){
        DialogFragment dialog = new DatePickerFragment();
        dialog.show(getFragmentManager(), "datePicker");;
    }
    public void finishadd(View view){
        String date = dateshow.getText().toString();
        double amount = Double.parseDouble(editamount.getText().toString());
        int tagcolor = mSelectedColor;
        String des = editdes.getText().toString();
        app.newRow(date,type,amount,tagcolor,des);
        app.save(getApplicationContext());
        finish();
        /*Intent in = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(in);*/
    }

}
