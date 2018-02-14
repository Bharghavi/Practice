package com.bhar.MyCompany;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Amrut on 2/13/2018.
 */
public class InMemorySaleTransaction implements SaleTransaction {

    private String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};


    void updateStockForSale(Sale sale) {
        //TODO
    }

    @Override
    public void recordNewSale(Sale sale) {
        Sale existingSale = getSaleFor(sale.getStoreId(), sale.getSnackName(), sale.getDate());
        if( existingSale!= null) {
            existingSale.setQuantity(existingSale.getQuantity() + sale.getQuantity());
        } else
            allSales.add(sale);
        updateStockForSale(sale); //TODO
    }

    @Override
    public void addNewStock(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void addNewStore(Store store) {
        stores.add(store);
    }

    @Override
    public void addNewSnack(Snack snack) {
        snacks.add(snack);
    }

    @Override
    public ArrayList<Stock> getStocksLeftAt(int storeId) {
       return (ArrayList<Stock>) stocks.stream().
               filter(stock -> stock.getStoreId()== storeId).
               collect(Collectors.toList());
    }

    @Override
    public ArrayList<Sale> getSalesAt(int storeId) {
        return (ArrayList<Sale>) allSales.stream().
                filter(sale -> sale.getStoreId() == storeId).
                collect(Collectors.toList());
    }

    @Override
    public HashMap<Date, ArrayList<Sale>> getDailySale(int storeId, String district, String state) {
        return groupByDate(getSalesAt(storeId));
    }

    @Override
    public HashMap<Date, ArrayList<Sale>> getDailySale(String district, String state) {
        return groupByDate(getAllSalesAtDistrict(district));
    }

    @Override
    public HashMap<Date, ArrayList<Sale>> getDailySale(String state) {
        return groupByDate(getAllSalesAtState(state));
    }

    @Override
    public HashMap<Date, ArrayList<Sale>> getDailySale() {
        return groupByDate(allSales);
    }

    @Override
    public HashMap<String, ArrayList<Sale>> getMonthlySale(String snackName, int storeId, String district, String state) {
        ArrayList<Sale> snackSaleAtStore = (ArrayList<Sale>) getSalesAt(storeId).stream().
                filter(sale -> sale.getSnackName().equals(snackName)).
                collect(Collectors.toList());
        return groupByMonth(snackSaleAtStore);
    }

    @Override
    public HashMap<String, ArrayList<Sale>> getMonthlySale(String snackName, String district, String state) {
        return groupByMonth(filterSnacksFromSales(getAllSalesAtDistrict(district), snackName));
    }

    @Override
    public HashMap<String, ArrayList<Sale>> getMonthlySale(String snackName, String state) {
        return groupByMonth(filterSnacksFromSales(getAllSalesAtState(state), snackName));
    }

    @Override
    public HashMap<String, ArrayList<Sale>> getMonthlySale(String snackName) {
        return groupByMonth(filterSnacksFromSales(allSales, snackName));
    }

    @Override
    public ArrayList<Stock> getStockLeft(int storeId, Date date) {
        return null;
    }

    @Override
    public String highestMonthlyBeverageConsumptionState() {
        ArrayList<String> allSnacksOfCategory = getAllSnacksOfCategory("Beverage");

        int maxBeverageSale = 0;
        String maxBeverageState = new String();

        for (String state : getAllStates()) {
            ArrayList<Sale> allSalesAtState = getAllSalesAtState(state);
            ArrayList<Sale> beverageSaleAtState = (ArrayList<Sale>) allSalesAtState.stream().
                    filter(sale -> allSnacksOfCategory.contains(sale.getSnackName())).
                    collect(Collectors.toList());

            HashMap<String, ArrayList<Sale>> beverageSaleAtStateGroupedByMonth = groupByMonth(beverageSaleAtState);

            Iterator it = beverageSaleAtStateGroupedByMonth.entrySet().iterator();

            while (it.hasNext()) {
                int sum = 0;
                ArrayList<Sale> bevSaleAtMonth = (ArrayList<Sale>) it.next();
                for (Sale eachSale : bevSaleAtMonth) {
                    sum += eachSale.getQuantity();
                }

                if (sum > maxBeverageSale) {
                    maxBeverageSale = sum;
                    maxBeverageState = new String(state);
                }
            }
        }


        return maxBeverageState;
    }

    private ArrayList<String> getAllSnacksOfCategory(String category) {
        ArrayList<Snack> snacksOfCategory = (ArrayList<Snack>)  snacks.stream().
                filter(snack -> snack.getCategory().equals(category)).
                collect(Collectors.toList());
        ArrayList<String> result = new ArrayList<String>();
        for (Snack each : snacksOfCategory)
            result.add(each.getSnackName());
        return result;
    }

    private ArrayList<String> getAllStates() {
        ArrayList<String> result = new ArrayList<String>();
        for (Store store : stores) {
            if (!result.contains(store.getState()))
                result.add(store.getState());
        }
        return result;
    }

    public ArrayList<Sale> getAllSalesAtDistrict(String district) {
        ArrayList<Store> storesAtDistrict = getAllStoresAtDistrict(district);
        ArrayList<Sale> salesAtDistrict = new ArrayList<Sale>();

        for(Store eachStore : storesAtDistrict) {
            salesAtDistrict.addAll(getSalesAt(eachStore.getStoreId()));
        }
        return salesAtDistrict;
    }
    public ArrayList<Sale> getAllSalesAtState(String state) {
        ArrayList<Store> storesAtState = getAllStoresAtState(state);
        ArrayList<Sale> salesAtState = new ArrayList<Sale>();

        for(Store eachStore : storesAtState) {
            salesAtState.addAll(getSalesAt(eachStore.getStoreId()));
        }
        return salesAtState;
    }

    ArrayList<Store> getAllStoresAtDistrict(String district) {
        return (ArrayList<Store>) stores.stream().filter(store -> store.getDistrict().equals(district)).collect(Collectors.toList());
    }

    ArrayList<Store> getAllStoresAtState(String state) {
        return (ArrayList<Store>) stores.stream().filter(store -> store.getState().equals(state)).collect(Collectors.toList());
    }

    private HashMap<Date, ArrayList<Sale>> groupByDate(ArrayList<Sale> givenSales) {
        HashMap<Date, ArrayList<Sale>> result = new HashMap<Date, ArrayList<Sale>>();

        for(Sale sale : givenSales){
            Date d = sale.getDate();
            if(result.containsKey(d)) {
                result.get(d).add(sale);
            } else {
                ArrayList<Sale> newSaleList = new ArrayList<Sale>();
                newSaleList.add(sale);
                result.put(d, newSaleList);
            }
        }

        return result;
    }

    private HashMap<String, ArrayList<Sale>> groupByMonth(ArrayList<Sale> givenSales) {
        HashMap<String, ArrayList<Sale>> result = new HashMap<String, ArrayList<Sale>>();
        Calendar calendar = Calendar.getInstance();

        for(Sale sale : givenSales){
            calendar.setTime(sale.getDate());
            String month = months[calendar.get(Calendar.MONTH)];
            if(result.containsKey(month)) {
                result.get(month).add(sale);
            } else {
                ArrayList<Sale> newSaleList = new ArrayList<Sale>();
                newSaleList.add(sale);
                result.put(month, newSaleList);
            }
        }
        return result;
    }

    ArrayList<Sale> filterSnacksFromSales(ArrayList<Sale> givenSales, String snackName) {
        return (ArrayList<Sale>) givenSales.stream().
                filter(sale -> sale.getSnackName().equals(snackName)).
                collect(Collectors.toList());
    }

    Sale getSaleFor(int storeId, String snackName, Date date) {
        ArrayList<Sale> filterDate = (ArrayList<Sale>) getSalesAt(storeId).stream().
                filter(sale -> sale.getDate().equals(date) && sale.getSnackName().equals(snackName)).
                collect(Collectors.toList());
        if (!filterDate.isEmpty())
            return filterDate.get(0);
        return null;
    }

    void updateSale(Sale s, int newQuantity){

    }

}
