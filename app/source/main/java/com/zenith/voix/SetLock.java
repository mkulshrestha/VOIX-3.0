package com.zenith.voix;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class SetLock extends ActionBarActivity {
    private static final int ADMIN_INTENT = 15;
    private static final String description = "Sample Administrator description";
    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_lock);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.CYAN));
        getSupportActionBar().setTitle("Set Command");



        mDevicePolicyManager = (DevicePolicyManager) getSystemService(
                    Context.DEVICE_POLICY_SERVICE);
            mComponentName = new ComponentName(this, MyAdminReceiver.class);
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, description);
            startActivityForResult(intent, ADMIN_INTENT);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case ADMIN_INTENT:{
                if (resultCode == RESULT_OK) {
                    Toast.makeText(getApplicationContext(), "Registered As Admin", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), SetInput.class);
                    i.putExtra("lockname", "lock");
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "Failed to register as Admin", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_lock, menu);
        return true;
    }


}
