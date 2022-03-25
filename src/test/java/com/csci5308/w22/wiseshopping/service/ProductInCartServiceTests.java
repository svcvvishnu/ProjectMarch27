package com.csci5308.w22.wiseshopping.service;

import com.csci5308.w22.wiseshopping.models.*;
import com.csci5308.w22.wiseshopping.repository.MerchantRepository;
import com.csci5308.w22.wiseshopping.repository.ProductInCartRepository;
import com.csci5308.w22.wiseshopping.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Pavithra Gunasekaran
 */
@ExtendWith(MockitoExtension.class)
public class ProductInCartServiceTests {
    @Mock
    private ProductInCartRepository mockedProductInCartRepository;


    @InjectMocks
    private ProductInCartService productInCartService;

    private ProductInCart productInCart;

    private Cart cart;

    private Store store;

    private User user;

    private Product product;

    private Merchant merchant;

    private Location location;

    @BeforeEach
    public void setUp(){
//        user=new User(10);
//        cart = new Cart(1,user,"inprogress");
//        product = new Product(1);
//        store = new Store(1);
//        productInCart = new ProductInCart(cart,product,store,2,12);

        user =new User("testuser","testuser","testuser@test.com","test","test");
        cart = new Cart(user,"inprogress");

        product=new Product("test","test");

        merchant = new Merchant("test", "test@test.com", "test");
        location =  new Location("test","test","test","test");
        store=new Store("test", Util.parseTime("8"),Util.parseTime("23"),"test","test",location,merchant);

        productInCart = new ProductInCart(cart,product,store,2,12);
    }

    @Test
    public void testAddProductInCart() {
        when(mockedProductInCartRepository.save(any(ProductInCart.class))).thenReturn(productInCart);
        ProductInCart actualProductInCart=productInCartService.addProductInCart(cart,product,store,2,12);
        Assertions.assertEquals(productInCart, actualProductInCart);
    }

    @Test
    public void testInputParametersForAddProductInCart(){
        IllegalArgumentException exceptionForCart = Assertions.assertThrows(IllegalArgumentException.class, () -> productInCartService.addProductInCart(null,product,store,2,12));
        Assertions.assertEquals("cart id cannot be null or empty or blank",exceptionForCart.getMessage());
        IllegalArgumentException exceptionForProduct = Assertions.assertThrows(IllegalArgumentException.class, () -> productInCartService.addProductInCart(cart,null,store,2,12));
        Assertions.assertEquals("product id cannot be null or empty or blank",exceptionForProduct.getMessage());

        IllegalArgumentException exceptionForStore = Assertions.assertThrows(IllegalArgumentException.class, () -> productInCartService.addProductInCart(cart,product,null,2,12));
        Assertions.assertEquals("store id cannot be null or empty or blank",exceptionForStore.getMessage());
        IllegalArgumentException exceptionForQuantity = Assertions.assertThrows(IllegalArgumentException.class, () -> productInCartService.addProductInCart(cart,product,store,0,12));
        Assertions.assertEquals("quantity cannot be zero",exceptionForQuantity.getMessage());
        IllegalArgumentException exceptionForPrice = Assertions.assertThrows(IllegalArgumentException.class, () -> productInCartService.addProductInCart(cart,product,store,1,0));
        Assertions.assertEquals("price cannot be zero",exceptionForPrice.getMessage());

    }


    @Test
    public void testRemoveProductInCart(){
        Assertions.assertTrue(productInCartService.remove(productInCart));
    }

}
