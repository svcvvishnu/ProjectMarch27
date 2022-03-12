package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.Product;
import com.csci5308.w22.wiseshopping.models.ProductCategory;
import com.csci5308.w22.wiseshopping.models.ProductInventory;
import com.csci5308.w22.wiseshopping.models.Store;
import com.csci5308.w22.wiseshopping.repository.ProductCategoryRepository;
import com.csci5308.w22.wiseshopping.repository.ProductInventoryRepository;
import com.csci5308.w22.wiseshopping.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Elizabeth James
 */
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductCategoryRepository mockedCategoryRepository;

    @Mock
    private ProductInventoryRepository mockedInventoryRepository;

    @Mock
    private ProductRepository mockedProductRepository;

    @Mock
    private SubscriptionService subscriptionService;

    @InjectMocks
    private ProductService productService;
    @Test
    public void testUpdateProductPrice(){
        Product product = new Product();
        Store store = new Store();
        ProductInventory inventory = new ProductInventory( store, product, 123, 456);
        when(mockedInventoryRepository.findByProductAndStore(product,store)).thenReturn(inventory);

        ProductInventory updated = productService.updateProductPrice(product, store, 1000);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(1000, updated.getPrice());
    }

    @Test
    public void testUpdateProductStock(){
        Product product = new Product();
        Store store = new Store();
        ProductInventory inventory = new ProductInventory( store, product, 123, 456);
        when(mockedInventoryRepository.findByProductAndStore(product,store)).thenReturn(inventory);

        ProductInventory updated = productService.updateProductStock(product, store, 8888);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(8888, updated.getStock());
    }

    @Test
    public void testUpdateProductPriceInvalidProductStore(){
        Product product = new Product();
        Store store = new Store();
        when(mockedInventoryRepository.findByProductAndStore(product,store)).thenReturn(null);

        IllegalArgumentException ex = Assertions.assertThrows( IllegalArgumentException.class,
                () -> productService.updateProductPrice(product, store, 1000), "Exception not thrown");
        Assertions.assertTrue(ex.getMessage().contains("Could not find inventory with given Product in store"));
    }


    @Test
    public void testUpdateProductStockInvalidProductStore(){
        Product product = new Product();
        Store store = new Store();
        when(mockedInventoryRepository.findByProductAndStore(product,store)).thenReturn(null);

        IllegalArgumentException ex = Assertions.assertThrows( IllegalArgumentException.class,
                () -> productService.updateProductStock(product, store, 1000), "Exception not thrown");
        Assertions.assertTrue(ex.getMessage().contains("Could not find inventory with given Product in store"));
    }

    @Test
    public void testUpdateProductCategoryName(){
        Product product = new Product();
        ProductCategory category = new ProductCategory( product, "Category A", "Category A Desc");
        when(mockedCategoryRepository.findByProductCategoryId(any(Integer.class))).thenReturn(category);

        ProductCategory updated = productService.updateProductCategoryName(1, "Category Name Updated");

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Category Name Updated", updated.getCategoryName());
    }

    @Test
    public void testUpdateProductCategoryDesc(){
        Product product = new Product();
        ProductCategory category = new ProductCategory( product, "Category A", "Category A Desc");
        when(mockedCategoryRepository.findByProductCategoryId(any(Integer.class))).thenReturn(category);

        ProductCategory updated = productService.updateProductCategoryDescription(1, "Category Desc Updated");

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Category Desc Updated", updated.getCategoryDesc());
    }

    @Test
    public void testUpdateProductCategoryNameInvalidProduct(){
        when(mockedCategoryRepository.findByProductCategoryId(any(Integer.class))).thenReturn(null);

        IllegalArgumentException ex = Assertions.assertThrows( IllegalArgumentException.class,
                () -> productService.updateProductCategoryName(1, "Category Name Updated"), "Exception not thrown");
        Assertions.assertTrue(ex.getMessage().contains("Could not find category with given Id:"));
    }

    @Test
    public void testUpdateProductCategoryDescInvalidProduct(){
        when(mockedCategoryRepository.findByProductCategoryId(any(Integer.class))).thenReturn(null);

        IllegalArgumentException ex = Assertions.assertThrows( IllegalArgumentException.class,
                () -> productService.updateProductCategoryDescription(1, "Category Desc Updated"), "Exception not thrown");
        Assertions.assertTrue(ex.getMessage().contains("Could not find category with given Id:"));
    }

    @Test
    public void testGetProductStockAvailablity(){
        when(mockedProductRepository.findByProductName(any(String.class))).thenReturn(null);
        IllegalArgumentException productNullException=Assertions.assertThrows(IllegalArgumentException.class, () -> productService.getProductStockAvailability("",""));
        Assertions.assertEquals("product name cannot be null",productNullException.getMessage());
    }
}
