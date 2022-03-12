package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Sales;
import com.csci5308.w22.wiseshopping.repository.SalesRepository;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Month;
import java.util.*;

/**
 * @author Pavithra Gunasekaran
 */
@Service
public class SalesService {
    @Autowired
    SalesRepository salesRepository;

    private static Multimap<Integer, List<Object>> PriceHistory = ArrayListMultimap.create();
    private static HashMap<Integer, Double> localMinimum = new HashMap<Integer, Double>();

    /**
     * Analytics to find the lowest price of a product over a year using
     * Local minimum concept
     * @param product
     * @return localMinimum
     * @throws SQLException
     */

    @Transactional
    public HashMap<Integer, Double> getProductLowestPriceAnalytics(String product) throws SQLException {
        if (product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }
        if (product.equals("")|| product.length() == 0) {
            throw new IllegalArgumentException("product cannot be empty");
        }
        if (product.equals(" ") ) {
            throw new IllegalArgumentException("product cannot be blank");
        }
        if(salesRepository.findByProduct(product).size()==0){
            throw new IllegalArgumentException("the selected product does not exists");
        }


        List<Sales> sales = salesRepository.findByProduct(product);
        localMinimum = getlocalMinimumByMonth(sales);
        return localMinimum;
    }

    /**
     * 
     * @param sales
     * @return localMinimum
     */
    @Transactional
    public HashMap<Integer, Double> getlocalMinimumByMonth(List<Sales> sales){
        int j=0,i=0;
        int prevMonth = 1;
        double  localMin = 1000;
        double currentLocalMin = 0;
        while (j<sales.size()) {
            Date date = sales.get(j).getOrderDate();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int monthFromResultSet = calendar.get(Calendar.MONTH)+1;

            int weekFromResultSet = calendar.get(Calendar.WEEK_OF_MONTH);
            double averagePriceFromResultSet = sales.get(j).getPriceEach();
            List<Object> priceByWeek = new ArrayList<>();
            priceByWeek.add(weekFromResultSet);
            priceByWeek.add(averagePriceFromResultSet);
            PriceHistory.put(monthFromResultSet, priceByWeek);
            for (Map.Entry<Integer, List<Object>> entry : PriceHistory.entries()) {
                int month = entry.getKey();

                if (prevMonth == month) {
                    if (i == 0) {

                        currentLocalMin = (double) entry.getValue().listIterator(1).next();
                        if (localMin > currentLocalMin) {
                            localMin = currentLocalMin;
                            prevMonth = month;
                        }
                        i++;
                    } else {
                        currentLocalMin = (double) entry.getValue().listIterator(1).next();
                        if (localMin > currentLocalMin) {
                            localMin = currentLocalMin;
                            prevMonth = month;
                        } else {
                            prevMonth = month;
                        }
                        i++;
                    }
                }
                if (prevMonth != month) {
                    //currentLocalMin = entry.getValue().listIterator(1).next();
                    localMinimum.put(prevMonth, localMin);
                    prevMonth = month;
                    i = 0;
                    currentLocalMin = (double) entry.getValue().listIterator(1).next();
                    localMin = currentLocalMin;
                }


            }
            j++;
        }
        localMinimum.put(prevMonth, localMin);


        return localMinimum;
    }


}
