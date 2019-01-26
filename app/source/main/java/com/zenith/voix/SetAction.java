package com.zenith.voix;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SetAction extends ActionBarActivity {
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_action);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.CYAN));
        getSupportActionBar().setTitle("Installed Apps");
        dbHandler = new MyDBHandler(this, null, null, 1);
        final PackageManager pm = getPackageManager();
        int i=0,j=0;
        int flags = PackageManager.GET_META_DATA |
                PackageManager.GET_SHARED_LIBRARY_FILES |
                PackageManager.GET_UNINSTALLED_PACKAGES;

        List<ApplicationInfo> installedApps = getPackageManager().getInstalledApplications(PackageManager.PERMISSION_GRANTED);
        List<ApplicationInfo> launchableInstalledApps = new ArrayList<ApplicationInfo>();

        List<PackageInfo> apps = getPackageManager().getInstalledPackages(flags);
        /*for(i=0;i<apps.size();i++) {
            PackageInfo p = apps.get(i);
            if ((p.applicationInfo.flags & (ApplicationInfo.FLAG_SYSTEM & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) != 0) {// IS A SYSTEM APP
            } else {
                j++;

            }

        }*/
         j=0;
        for(i =0; i<installedApps.size(); i++){
            if (getPackageManager().getLaunchIntentForPackage(installedApps.get(i).packageName) != null){
                //If you're here, then this is a launch-able app
                launchableInstalledApps.add(installedApps.get(i));
                j++;
            }
        }


        final String[] aname=new String[j];
        final String[] pname=new String[j];
        Drawable[] image=new Drawable[j];
        j=0;
        for(i =0; i<installedApps.size(); i++){
            ApplicationInfo p = installedApps.get(i);
            if (getPackageManager().getLaunchIntentForPackage(installedApps.get(i).packageName) != null){
                //If you're here, then this is a launch-able app
                aname[j] = p.loadLabel(getPackageManager()).toString();
                pname[j] = p.packageName;
                //newInfo.versionName = p.versionName;
                //newInfo.versionCode = p.versionCode;
                image[j] = p.loadIcon(getPackageManager());
                j++;

            }
        }

        /*for(i=0;i<apps.size();i++) {
            PackageInfo p = apps.get(i);
            if ((p.applicationInfo.flags & (ApplicationInfo.FLAG_SYSTEM & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) != 0) {
            }
            else {



                aname[j] = p.applicationInfo.loadLabel(getPackageManager()).toString();
                pname[j] = p.packageName;
                //newInfo.versionName = p.versionName;
                //newInfo.versionCode = p.versionCode;
                image[j] = p.applicationInfo.loadIcon(getPackageManager());
                j++;
            }
        }*/

        ListView list;
        CustomListAdapter adapter=new CustomListAdapter(this, pname, image,aname);
        list=(ListView)findViewById(R.id.harshitslist);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = pname[+position];
                String Slectedapp = aname[+position];
                boolean flag = false;
                flag = dbHandler.comparep(Slecteditem);
                if (flag == true) {
                    Toast.makeText(getApplicationContext(), "You already have set this Application a command", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(getApplicationContext(), SetInput.class);
                    intent.putExtra("Packagename", Slecteditem);
                    intent.putExtra("Appname", Slectedapp);
                    startActivity(intent);
                }


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
        getMenuInflater().inflate(R.menu.menu_set_action, menu);
        return true;
    }


}
