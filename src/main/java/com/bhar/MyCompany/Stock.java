package com.bhar.MyCompany;

import java.util.Date;

/**
 * Created by Amrut on 2/12/2018.
 */
public class Stock {
    private String snackName;
    private int quantity;
    private int storeId;
    private Date date;

    public Stock(String snackName, int quantity, int storeId, Date date) {
        this.snackName = snackName;
        this.quantity = quantity;
        this.storeId = storeId;
        this.date = date;
    }

    public String getSnackName() {
        return snackName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getStoreId() {
        return storeId;
    }
}