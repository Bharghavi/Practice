package com.bhar.MyCompany;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Amrut on 2/12/2018.
 */
public class Sale {
    private int storeId;
    private String snackName;
    private int quantity;
    private Date date;

    public Sale(int storeId, String snackName, int quantity, Date date) {
        this.storeId = storeId;
        this.snackName = snackName;
        this.quantity = quantity;
        this.date = date;
    }


    public int getStoreId() {
        return storeId;
    }

    public String getSnackName() {
        return snackName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}