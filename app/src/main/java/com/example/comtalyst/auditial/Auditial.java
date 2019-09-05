package com.example.comtalyst.auditial;

import android.app.Application;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Auditial extends Application {
    public double balance=0.0;
    int rowcount=0;
    public Row[] table = new Row[1005];
    String str;

    public Auditial(){
        super();
    }
    public void newRow(String date,boolean type, double amount, int tagcolor,String des){
        rowcount++;
        table[rowcount] = new Row();
        table[rowcount].date = date;
        table[rowcount].type = type;
        table[rowcount].amount = amount;
        table[rowcount].tag = tagcolor;
        table[rowcount].des = des;
        if(type){
            balance += amount;
        }
        else{
            balance -= amount;
        }
    }
    public void save(Context context){
        System.out.println("SAVING");
        int i;
        String filename = "tablesave.txt";
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(filename,MODE_PRIVATE);
            outputStream.write((Double.toString(balance)).getBytes());
            for(i = 1; i <= rowcount; i++){
                outputStream.write("\n".getBytes());
                outputStream.write(table[i].date.getBytes());
                outputStream.write("\n".getBytes());
                if(table[i].type){
                    outputStream.write("1".getBytes());
                }
                else{
                    outputStream.write("0".getBytes());
                }
                outputStream.write("\n".getBytes());
                outputStream.write(Double.toString(table[i].amount).getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write(Integer.toString(table[i].tag).getBytes());
                outputStream.write("\n".getBytes());
                outputStream.write(table[i].des.getBytes());
            }
            //outputStream.write("END".getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void read(Context context){
        System.out.println("READING");
        try {
            String Message;
            FileInputStream inputStream = context.openFileInput("tablesave.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            Message = bufferedReader.readLine();
            balance = Double.parseDouble(Message);
            while((Message = bufferedReader.readLine()) != null){
                rowcount++;
                table[rowcount] = new Row();
                table[rowcount].date = Message;
                Message = bufferedReader.readLine();
                if(Integer.parseInt(Message) == 1){
                    table[rowcount].type = true;
                }
                else{
                    table[rowcount].type = false;
                }
                table[rowcount].amount = Double.parseDouble(bufferedReader.readLine());
                table[rowcount].tag = Integer.parseInt(bufferedReader.readLine());
                table[rowcount].des = bufferedReader.readLine();
            }
            inputStream.close();
        } catch (FileNotFoundException e) {
            save(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
