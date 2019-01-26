package com.zenith.voix;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Choose extends ActionBarActivity {
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        dbHandler = new MyDBHandler(this, null, null, 1);
        stopService(new Intent(getApplication(), ChatHeadService.class));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.CYAN));



    }
    /*
    public void lockclicked(View view){
        boolean flag=dbHandler.comparep("lock");
        if (flag){
            Toast.makeText(getApplicationContext(), "You already have set  a command for lock", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this, SetLock.class);
            startActivity(i);
        }
    }
*/

    public void appclicked(View view){
        Intent i=new Intent(this,SetAction.class);
        startActivity(i);
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
        getMenuInflater().inflate(R.menu.menu_choose, menu);
        return true;
    }


}
