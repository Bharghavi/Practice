package com.bhar.MyCompany;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Amrut on 2/12/2018.
 */
public class SalesManagement {

   InMemorySaleTransaction saleTransaction = new InMemorySaleTransaction();

    void addNewStore(int storeId, String district, String state) {
        Store newStore = new Store(storeId, district, state);
        saleTransaction.addNewStore(newStore);
    }

    void addNewSale(int storeId, String snackName, int quantity, Date date) {
        Sale newSale = new Sale(storeId, snackName, quantity, date);
        saleTransaction.recordNewSale(newSale);
    }


}
