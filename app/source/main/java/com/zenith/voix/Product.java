package com.zenith.voix;

//Each row in the database can be represented by an object
//Columns will represent the objects properties
public class Product {

    private int _id;
    private String _productname;
    private String command;
    private String appname;

    public Product(){
    }

    public Product(String productname,String command,String appname){
        this._productname = productname;
        this.command=command;
        this.appname=appname;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }

    public int get_id() {
        return _id;
    }

    public String get_productname() {
        return _productname;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
