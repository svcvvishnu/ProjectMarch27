package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.models.Tags;
import com.csci5308.w22.wiseshopping.repository.*;
import com.csci5308.w22.wiseshopping.service.*;
import com.csci5308.w22.wiseshopping.utils.Util;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * @author Pavithra Gunasekaran
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class ProductServiceTests {
    @Autowired
    ProductService productService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private ProductInventoryRepository productInventoryRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private TagsRepository tagsRepository;

    @Autowired
    private StoreService storeService;

    private Product product;
    private Merchant merchant;
    private Location location;

    @BeforeEach
    public void setUp() {
        product = new Product("Wired Headphones");
        merchant = new Merchant("John Doe", "johndoe@xyz.com", "password123");
        location = locationService.addLocation("testLocation", "testZIp", "testProvince", "testCountry");
    }

    @AfterEach
    public void cleanUp() {
        merchantService.removeMerchant(merchant.getEmail());
        locationService.remove(location);
    }

    @Test
    public void testGetProductStockAvailability() throws SQLException {
        List<Product> productList = productService.getProductStockAvailability("milk","halifax");
        Assertions.assertTrue(productList.size()>0);

    }

    @Test
    public void testUpdateProductPrice(){
        Product p = productRepository.findByProductId(1);
        Store store = storeRepository.findById(1);
        if (store == null) {
            store = storeService.addStore("Timbuktu", "private", "11","12", "John Doe", merchant,location);
        }
        Assertions.assertEquals(10, productInventoryRepository.findByProductAndStore(p, store).getPrice());
        productService.updateProductPrice(p, store, 100);

        Assertions.assertEquals(100, productInventoryRepository.findByProductAndStore(p, store).getPrice());
        productService.updateProductPrice(p, store, 10);
    }

    @Test
    public void testUpdateProductStock(){
        Product p = productRepository.findByProductId(1);
        Store store = storeRepository.findById(1);
        if (store == null) {
            store = storeService.addStore("Timbuktu", "private", "11","12", "John Doe", merchant,location);
        }
        Assertions.assertEquals(10, productInventoryRepository.findByProductAndStore(p, store).getStock());
        productService.updateProductPrice(p, store, 500);

        Assertions.assertEquals(500, productInventoryRepository.findByProductAndStore(p, store).getPrice());
        productService.updateProductPrice(p, store, 10);
    }

    @Test
    public void testUpdateProductCategoryName(){
        Assertions.assertEquals("Farmers_Milk", productCategoryRepository.findByProductCategoryId(1).getCategoryName());
        productService.updateProductCategoryName(1, "groceries_updated");

        Assertions.assertEquals("groceries_updated", productCategoryRepository.findByProductCategoryId(1).getCategoryName());
        productService.updateProductCategoryName(1, "Farmers_Milk");
    }

    @Test
    public void testUpdateProductCategoryDesc(){
        Assertions.assertEquals("Farmers_Milk", productCategoryRepository.findByProductCategoryId(1).getCategoryDesc());
        productService.updateProductCategoryName(1, "Farmers_Milk_updated");

        Assertions.assertEquals("Farmers_Milk_updated", productCategoryRepository.findByProductCategoryId(1).getCategoryName());
        productService.updateProductCategoryName(1, "Farmers_Milk");
    }

    @Test
    public void testUpdateProductTags(){

        Product p = productRepository.findByProductId(1);
        List<Tags> tags = tagsRepository.findByProductId(1);

        Tags newTag = productService.updateProductTags(p, "NewTag");
        List<Tags> tags1 = tagsRepository.findByProductId(1);
        tags1.removeAll(tags);

        Assertions.assertEquals(1, tags1.size());
        Assertions.assertEquals("NewTag", tags1.get(0).getTagName());
        tagsRepository.delete(newTag);
    }

}
