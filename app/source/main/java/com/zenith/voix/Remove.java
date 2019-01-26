package com.zenith.voix;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Remove extends ActionBarActivity {
    MyDBHandler dbHandler;
    String del;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.CYAN));
        getSupportActionBar().setTitle("Remove Command");
        Bundle Deletedata=getIntent().getExtras();
        del=Deletedata.getString("Appname");
        dbHandler = new MyDBHandler(this, null, null, 1);

    }
    public void yesclicked(View view){
        dbHandler.deleteProduct(del);
        finish();

    }
    public void noclicked(View view){
        finish();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_remove, menu);
        return true;
    }


}
