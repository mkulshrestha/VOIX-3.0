package com.zenith.voix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import static android.content.Context.MODE_PRIVATE;

public class MainActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private ImageButton add;
    private Button stopw;
    boolean stop=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=(ImageButton)findViewById(R.id.add);
        add.setImageResource(R.drawable.add);
        mDrawerList = (ListView)findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        stopw=(Button)findViewById(R.id.stopw);
        addDrawerItems();
        setupDrawer();
        SharedPreferences preferences= getSharedPreferences("my_preferences",MODE_PRIVATE);
        if(!preferences.getBoolean("tut_complete",false)){
            Intent intent=new Intent(this,tut.class);
            startActivity(intent);
            preferences.edit().putBoolean("tut_complete",true).apply();
        }

        if (stop){

            stopw.setText("Start Widget");
        }
        else {

            stopw.setText("Stop Widget");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.CYAN));
    }
    public void stop(View view){
        if (stop==false){
            stop=true;
            stopw.setText("Start Widget");
        }
        else {
            stop=false;
            stopw.setText("Stop Widget");
        }


    }


    public void addclick(View view){
        Intent i=new Intent(this,Choose.class);
        startActivity(i);
    }

    private void addDrawerItems() {
        String[] osArray = { "MY COMMANDS", "CREDITS", "CONTACT US" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (position){
                   case 0:
                      Intent i=new Intent(getApplicationContext(),Yourcommands.class);
                       startActivity(i);

                        break;
                   case 1:
                       Intent i2=new Intent(getApplicationContext(),Aboutus.class);
                       startActivity(i2);
                        break;
                   case 2:
                       Intent i3=new Intent(getApplicationContext(),ContactUs.class);
                       startActivity(i3);
                        break;
               }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_tutorial) {
            Intent i4=new Intent(getApplicationContext(),tut.class);
            startActivity(i4);
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // The activity is about to become visible.
    }

    @Override
    protected void onResume() {
        super.onResume();

        stopService(new Intent(getApplication(), ChatHeadService.class));
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (stop)
           stopService(new Intent(getApplication(), ChatHeadService.class));
            else
            startService(new Intent(getApplication(), ChatHeadService.class));
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        super.onStop();

        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // The activity is about to be destroyed.
    }
}
