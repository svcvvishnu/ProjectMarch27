package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.repository.ProductCategoryRepository;
import com.csci5308.w22.wiseshopping.repository.ProductInventoryRepository;
import com.csci5308.w22.wiseshopping.repository.ProductRepository;
import com.csci5308.w22.wiseshopping.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csci5308.w22.wiseshopping.models.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Elizabeth James
 */
@Service
public class ProductService {

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TagsRepository tagsRepository;

    public ProductInventory addProductInventory( Product product, Store store, float price, float stock ){
        return null;
    }

    public Product addProduct ( String name, String description){
        return null;
    }



    @Transactional
    public ProductInventory updateProductPrice(Product product, Store store, int price) {

        ProductInventory productInventory = productInventoryRepository.findByProductAndStore(product, store);

        if (productInventory == null) {
            throw new IllegalArgumentException("Could not find inventory with given Product in store");
        }

        productInventory.setPrice(price);
        productInventoryRepository.save(productInventory);
        subscriptionService.alertSubscribers(product, store, price);
        return productInventory;
    }

    @Transactional
    public ProductInventory updateProductStock(Product product, Store store, int stock) {
        ProductInventory productInventory = productInventoryRepository.findByProductAndStore(product, store);

        if (productInventory == null) {
            throw new IllegalArgumentException("Could not find inventory with given Product in store:");
        }

        productInventory.setStock(stock);
        productInventoryRepository.save(productInventory);
        return productInventory;
    }

    @Transactional
    public ProductCategory updateProductCategoryName(int productCategoryId, String name) {
        ProductCategory category = productCategoryRepository.findByProductCategoryId(productCategoryId);

        if (category == null) {
            throw new IllegalArgumentException("Could not find category with given Id: " + productCategoryId);
        }

        category.setCategoryName(name);
        productCategoryRepository.save(category);
        return category;
    }

    @Transactional
    public ProductCategory updateProductCategoryDescription(int productCategoryId, String description) {
        ProductCategory category = productCategoryRepository.findByProductCategoryId(productCategoryId);

        if (category == null) {
            throw new IllegalArgumentException("Could not find category with given Id: " + productCategoryId);
        }

        category.setCategoryDesc(description);
        productCategoryRepository.save(category);
        return category;
    }

    @Transactional
    public List<Product> getProductStockAvailability(String product_name,String location){
        List<Product> productIDList= productRepository.findByProductName(product_name);
        if(product_name.equals("")){
            throw  new IllegalArgumentException("product name cannot be null");
        }
        System.out.println(productIDList.get(0).getProductId());
        List<ProductInventory> productInventoryList=productInventoryRepository.findByProductId(productIDList.get(0).getProductId(),location);

//        System.out.println("Product Name : "+product_name+", "+"Store Name : "+productInventoryList.get(0).getStore().getStoreName()
//                +", Stock : "+productInventoryList.get(0).getStock());
    return  productIDList;
    }


    //UpdateTags
    @Transactional
    public Tags updateProductTags(Product product, String name) {
        Tags tag = new Tags(product, name);
        return tagsRepository.save(tag);
    }
}


