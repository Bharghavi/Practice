package com.bhar.MyCompany;

import java.util.*;

/**
 * Created by Amrut on 2/12/2018.
 */
public class Store {
    private int storeId;
    private String district;
    private String state;

    Store(int storeId, String district, String state)
    {
        this.storeId = storeId;
        this.district = district;
        this.state = state;
    }

    protected int getStoreId(){
        return this.storeId;
    }

    protected String getDistrict(){
        return this.district;
    }

    protected String getState(){
        return this.state;
    }

}

