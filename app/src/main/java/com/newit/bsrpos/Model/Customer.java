package com.newit.bsrpos.Model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

public class Customer implements Parcelable {
    public static final Parcelable.Creator<Customer> CREATOR = new Parcelable.Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel source) {
            return new Customer(source);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
    private String code;
    private String name;
    private String addr;
    private int point;
    private String key;

    public Customer() {
    }

    public Customer(String code, String name, String addr, int point) {
        this.code = code;
        this.name = name;
        this.addr = addr;
        this.point = point;
    }

    protected Customer(Parcel in) {
        this.code = in.readString();
        this.name = in.readString();
        this.addr = in.readString();
        this.point = in.readInt();
        this.key = in.readString();
    }

    @Exclude
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.name);
        dest.writeString(this.addr);
        dest.writeInt(this.point);
        dest.writeString(this.key);
    }
}
