package com.zenith.voix;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;


public class SetInput extends ActionBarActivity {
    public String pname,lname,appname;
    protected static final int RESULT_SPEECH = 1;
    public String str;
    public TextView txtText;
    Intent intent = new Intent(
            RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_input);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.CYAN));
        getSupportActionBar().setTitle("Set Command");
        txtText = (TextView) findViewById(R.id.txtText);
        dbHandler = new MyDBHandler(this, null, null, 1);
       // Bundle SetLockdata=getIntent().getExtras();
        Bundle SetActiondata=getIntent().getExtras();
        if(SetActiondata==null ) //SetLockdata==null)
         {
            return;
        }

        pname=SetActiondata.getString("Packagename");
        appname=SetActiondata.getString("Appname");
        if (appname==null)
            appname="lock";
        if (pname==null)
        pname=SetActiondata.getString("lockname");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(intent, RESULT_SPEECH);


        } catch (ActivityNotFoundException a) {
            Toast t = Toast.makeText(getApplicationContext(),
                    "Opps! Your device doesn't support Speech to Text",
                    Toast.LENGTH_SHORT);
            t.show();
        }
    }
    public void spkclicked(View view){
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(intent, RESULT_SPEECH);


        } catch (ActivityNotFoundException a) {
            Toast t = Toast.makeText(getApplicationContext(),
                    "Opps! Your device doesn't support Speech to Text",
                    Toast.LENGTH_SHORT);
            t.show();
        }
    }
    public void setclicked(View view){

        boolean flag=false;
        try{
            flag=dbHandler.comparec(str);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "compare nahi ho rha ", Toast.LENGTH_SHORT).show();
        }

        if (flag==true){
            Toast.makeText(getApplicationContext(), "You already have used this command ", Toast.LENGTH_SHORT).show();
        }
        else {
            if(str!=null) {
                String abc = pname;
                Product prod = new Product(abc, str, appname);
                dbHandler.addProduct(prod);
                Toast.makeText(getApplicationContext(), "Command Set ", Toast.LENGTH_SHORT).show();
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(), "Say something first ", Toast.LENGTH_SHORT).show();
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    txtText.setText(text.get(0));
                    str = text.get(0);


                }
                break;
            }


        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_input, menu);
        return true;
    }



}
