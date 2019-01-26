package com.zenith.voix;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomAppAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] appname;
    private final String[] commands;

    public CustomAppAdapter(Activity context, String[] appname,String[] commands) {
        super(context, R.layout.mycommandlist,appname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.appname=appname;
        this.commands=commands;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mycommandlist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.name);
        TextView command=(TextView) rowView.findViewById(R.id.command);


        txtTitle.setText(appname[position]);

        command.setText(commands[position]);

        return rowView;

    }
}
