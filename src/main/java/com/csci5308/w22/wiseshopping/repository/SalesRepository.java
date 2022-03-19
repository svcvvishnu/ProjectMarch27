package com.csci5308.w22.wiseshopping.repository;

//import com.csci5308.w22.wiseshopping.models.Sales;
import com.csci5308.w22.wiseshopping.models.Sales;
import com.csci5308.w22.wiseshopping.models.Store;
//import com.google.common.collect.ArrayListMultimap;
//import com.google.common.collect.Multimap;
import org.apache.commons.collections.MultiMap;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Pavithra Gunasekaran
 */


@Repository
public interface SalesRepository extends CrudRepository<Sales, Integer> {
    @Query(value = "SELECT * FROM sales WHERE product = ?1", nativeQuery = true)
//@Query(value="select MONTH(order_date) as Month,WEEK(order_date) as Week, Round(avg(price_each),2) as AveragePrice " +
//           "from sales where Product= ?1 group by Week,Month", nativeQuery = true)

   //Multimap<Integer, List<Double>> findPriceByMonthWeek(String product);
   List<Sales> findByProduct(String product);
}
