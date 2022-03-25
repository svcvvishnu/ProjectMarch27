package com.csci5308.w22.wiseshopping.integrationTests.service;

import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.service.*;
import com.csci5308.w22.wiseshopping.utils.Util;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * @author Pavithra Gunasekaran
 */
@SpringBootTest
@ActiveProfiles(profiles = "dev")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductInCartServiceTests {
    @Autowired
    private ProductInCartService productInCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private LocationService locationService;

    private ProductInCart productInCart;

    private Cart cart;

    private Store store;

    private User user;

    private Product product;

    private Merchant merchant;

    private Location location;

    @BeforeAll
    public void setUp(){
        user =userService.registerUser("testuser","testuser","testuser@test.com","test","test");
        cart = cartService.addCart(user,"inprogress");

        product=productService.addProduct("test","test");

        merchant = merchantService.registerMerchant("test", "test@test.com", "test");
        location =  locationService.addLocation("test","test","test","test");
        store=storeService.addStore("test","test", Util.parseTime("8"),Util.parseTime("23"),"test",merchant,location);

        productInCart = new ProductInCart(cart,product,store,2,12);
    }


    @Test
    @Order(1)
    public void testAddProductInCart(){
        //System.out.println(product.getProductId());

        System.out.println("product id: "+product.getProductId());

        ProductInCart actualProductInCart=productInCartService.addProductInCart(cart,product,store,2,12);
        productInCart.setId(actualProductInCart.getId());
        Assertions.assertEquals(productInCart,actualProductInCart);

    }

    @Test
    @Order(2)
    public void testRemoveExistingProductInCart(){
        //ProductInCart removeProductInCart=productInCartService.addProductInCart(cart,product,store,2,12);
        Assertions.assertTrue(productInCartService.removeProductInCart(productInCart));
    }

    @AfterAll
    public void cleanUp(){
//        productInCartService.remove(productInCart);
        cartService.remove(cart);
        userService.removeUser(user.getEmail());



        storeService.remove(store);
        locationService.remove(location);
        merchantService.removeMerchant(merchant.getEmail());

        productService.remove(product);


    }

}
