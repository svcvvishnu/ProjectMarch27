package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.repository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Pavithra Gunasekaran
 */
@Service
public class ProductInventoryService {
    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Transactional
    public List<ProductInventory> getProducts(){
        List<ProductInventory> productList= productInventoryRepository.findByProducts();

        //System.out.println(productList.get(0).getProduct().getProductName()+"-"+productList.get(0).getStock());
        //List<ProductInventory> productInventoryList=productInventoryRepository.findByProductId(productIDList.get(0).getProductId(),location);

//        System.out.println("Product Name : "+product_name+", "+"Store Name : "+productInventoryList.get(0).getStore().getStoreName()
//                +", Stock : "+productInventoryList.get(0).getStock());
        return  productList;
    }
}
