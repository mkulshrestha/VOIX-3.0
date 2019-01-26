package com.zenith.voix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "productname";
    public static final String COLUMN_COMMAND = "command";
    public static final String COLUMN_APPNAME = "appname";
    public int i=0;

    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PRODUCTNAME + " TEXT, " + COLUMN_COMMAND + " TEXT, " +  COLUMN_APPNAME + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addProduct(Product product){
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.get_productname());
        SQLiteDatabase db = getWritableDatabase();
        values.put(COLUMN_COMMAND,product.getCommand());
        values.put(COLUMN_APPNAME,product.getAppname());
        db.insert(TABLE_PRODUCTS, null, values);
          db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_APPNAME + "=\"" + productName + "\";");
        db.close();
    }
    public String packagename(String cmp) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results


        String dbString,packagename=null;

        i=0;
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("command")) != null) {
                dbString = c.getString(c.getColumnIndex("command"));
                i++;
                if (dbString.equals(cmp)){
                    packagename=c.getString(c.getColumnIndex("productname"));

                    break;
                }

            }
            c.moveToNext();

        }
        db.close();
        return packagename;

    }
    public boolean comparep(String cmp) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results


        String dbString;
        boolean flag=false;
        i=0;
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("productname")) != null) {
                dbString = c.getString(c.getColumnIndex("productname"));
                i++;
                if (dbString.equals(cmp)){
                    flag=true;
                    break;
                }

            }
            c.moveToNext();

        }
        db.close();
        return flag;
    }

    public boolean comparec(String cmp) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results


        String dbString;
        boolean flag=false;
        i=0;
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("command")) != null) {
                dbString = c.getString(c.getColumnIndex("command"));
                i++;
                if (dbString.equals(cmp)){
                    flag=true;
                    break;
                }

            }
            c.moveToNext();

        }
        db.close();
        return flag;
    }
    public String databaseToCommand1(){


        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("command")) != null) {
                dbString += c.getString(c.getColumnIndex("command"));
                dbString += "\n";


            }
            c.moveToNext();

        }

        db.close();
        return dbString;
    }

    public String[] databaseToApp(){


        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        i=0;
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("appname")) != null) {
                //dbString += c.getString(c.getColumnIndex("command"));
                //dbString += "\n";
                //dbString1[i]=c.getString(c.getColumnIndex("command"));
                i++;
            }
            c.moveToNext();

        }
        c.moveToFirst();
        String[] dbString=new String[i];
        i=0;
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("appname")) != null) {
                dbString[i] = c.getString(c.getColumnIndex("appname"));
                i++;
            }
            c.moveToNext();

        }
        db.close();
        return dbString;
    }

    public String[] databaseToCommand(){


        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        i=0;
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("command")) != null) {
                //dbString += c.getString(c.getColumnIndex("command"));
                //dbString += "\n";
                //dbString1[i]=c.getString(c.getColumnIndex("command"));
                i++;
            }
            c.moveToNext();

        }
        c.moveToFirst();
        String[] dbString=new String[i];
        i=0;
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("command")) != null) {
                dbString[i] = c.getString(c.getColumnIndex("command"));
                i++;
            }
            c.moveToNext();

        }
        db.close();
        return dbString;
    }
    public String[] databaseToString(){


        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        i=0;
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("productname")) != null) {
                //dbString += c.getString(c.getColumnIndex("productname"));
                //dbString += "\n";
                //dbString1[i]=c.getString(c.getColumnIndex("productname"));
                i++;
            }
            c.moveToNext();

        }
        c.moveToFirst();
        String[] dbString=new String[i];
        i=0;
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("productname")) != null) {
                dbString[i] = c.getString(c.getColumnIndex("productname"));
                i++;
            }
            c.moveToNext();

        }
        db.close();
        return dbString;
    }
    public String databaseToString1(){

        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("productname")) != null) {
                dbString += c.getString(c.getColumnIndex("productname"));
                dbString += "\n";


            }
            c.moveToNext();

        }

        db.close();
        return dbString;
    }
    public String databaseToApp1(){

        String dbString="";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {

            if (c.getString(c.getColumnIndex("Appname")) != null) {
                dbString += c.getString(c.getColumnIndex("Appname"));
                dbString += "\n";


            }
            c.moveToNext();

        }

        db.close();
        return dbString;
    }
    public int number(){
        return i;
    }


}
