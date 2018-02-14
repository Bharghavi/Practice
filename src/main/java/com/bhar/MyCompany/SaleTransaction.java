package com.bhar.MyCompany;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Amrut on 2/13/2018.
 */
public interface SaleTransaction {

    ArrayList<Sale> allSales = new ArrayList<Sale>();
    ArrayList<Store> stores = new ArrayList<Store>();
    ArrayList<Stock> stocks = new ArrayList<Stock>();
    ArrayList<Snack> snacks = new ArrayList<Snack>();

    void recordNewSale(Sale sale);
    void addNewStock(Stock stock);
    void addNewStore(Store store);
    void addNewSnack(Snack snack);


    ArrayList<Stock> getStocksLeftAt(int storeId);
    ArrayList<Sale> getSalesAt(int storeId);

    HashMap<Date, ArrayList<Sale>> getDailySale(int storeId, String district, String state);
    HashMap<Date, ArrayList<Sale>> getDailySale(String district, String state);
    HashMap<Date, ArrayList<Sale>> getDailySale(String state);
    HashMap<Date, ArrayList<Sale>> getDailySale();

    HashMap<String, ArrayList<Sale>> getMonthlySale(String snackName, int storeId, String district, String state);
    HashMap<String, ArrayList<Sale>> getMonthlySale(String snackName, String district, String state);
    HashMap<String, ArrayList<Sale>> getMonthlySale(String snackName, String state);
    HashMap<String, ArrayList<Sale>> getMonthlySale(String snackName);

    ArrayList<Stock> getStockLeft(int storeId, Date date);

    String highestMonthlyBeverageConsumptionState();

}
