package com.newit.bsrpos.Model;

public class OrderLine {

    private int amt;
    private int disc;
    private int no;
    private int price;
    private String prod;
    private int qty;

    public OrderLine() {
    }

    public OrderLine(int amt, int disc, int no, int price, String prod, int qty) {
        this.amt = amt;
        this.disc = disc;
        this.no = no;
        this.price = price;
        this.prod = prod;
        this.qty = qty;
    }

    public int getAmt() {
        return amt;
    }

    public void setAmt(int amt) {
        this.amt = amt;
    }

    public int getDisc() {
        return disc;
    }

    public void setDisc(int disc) {
        this.disc = disc;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
