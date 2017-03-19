//package com.newit.bsrpos.Model;
//
//import com.bignerdranch.expandablerecyclerview.model.Parent;
//
//import java.util.List;
//
//
//public class Order implements Parent<OrderLine> {
//
//    private String no;
//    private String date;
//    private String cus;
//    private String branch;
//    private List<OrderLine> lines;
//
//    public Order() {
//    }
//
//    public Order(String no, String date, String cus, String branch, List<OrderLine> lines) {
//        this.cus = cus;
//        this.no = no;
//        this.date = date;
//        this.branch = branch;
//        this.lines = lines;
//    }
//
//    @Override
//    public List<OrderLine> getChildList() {
//        return lines;
//    }
//
//    @Override
//    public boolean isInitiallyExpanded() {
//        return false;
//    }
//
//    public String getNo() {
//        return no;
//    }
//
//    public void setNo(String no) {
//        this.no = no;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getCus() {
//        return cus;
//    }
//
//    public void setCus(String cus) {
//        this.cus = cus;
//    }
//
//    public String getBranch() {
//        return branch;
//    }
//
//    public void setBranch(String branch) {
//        this.branch = branch;
//    }
//
//    public void setLines(List<OrderLine> lines) {
//        this.lines = lines;
//    }
//
//    //    @Exclude
////    public Map<String, Object> toMap() {
////        HashMap<String, Object> result = new HashMap<>();
////        result.put("cus", cus);
////        result.put("no", no);
////        result.put("date", date);
////        result.put("branch", branch);
////        return result;
////    }
//}

package com.newit.bsrpos.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;

public class Order implements Parcelable {
    public static final Parcelable.Creator<Order> CREATOR = new Parcelable.Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel source) {
            return new Order(source);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
    private String no;
    private String date;
    private String cus;
    private String key;

    public Order() {

    }

    public Order(String no, String date, String cus) {
        this.no = no;
        this.date = date;
        this.cus = cus;
    }

    protected Order(Parcel in) {
        this.no = in.readString();
        this.date = in.readString();
        this.cus = in.readString();
        this.key = in.readString();
    }

    @Exclude
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCus() {
        return cus;
    }

    public void setCus(String cus) {
        this.cus = cus;
    }

    @Override
    public String toString() {
        return no + " : " + cus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.no);
        dest.writeString(this.date);
        dest.writeString(this.cus);
        dest.writeString(this.key);
    }
}