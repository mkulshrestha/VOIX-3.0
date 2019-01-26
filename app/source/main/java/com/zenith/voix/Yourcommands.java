package com.zenith.voix;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class Yourcommands extends ActionBarActivity {
    MyDBHandler dbHandler;
    String[] aname=new String[]{};
    String[] cmd = new String[]{};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yourcommands);
        stopService(new Intent(getApplication(), ChatHeadService.class));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.CYAN));
        getSupportActionBar().setTitle("Set Command");

        dbHandler = new MyDBHandler(this, null, null, 1);



        //String[] aname = new String[j];
        //String[] cmd = new String[j];
        aname=dbHandler.databaseToApp();
        cmd=dbHandler.databaseToCommand();


        ListView list;
        CustomAppAdapter adapter = new CustomAppAdapter(this, aname, cmd);
        list = (ListView) findViewById(R.id.harshitslist2);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                 String Slectedapp=aname[+position];

                Intent intent = new Intent(getApplicationContext(), Remove.class);
                intent.putExtra("Appname", Slectedapp);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
       finish();
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_yourcommands, menu);

        return true;
    }


}
