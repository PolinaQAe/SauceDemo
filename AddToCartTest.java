

package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    // Test 1: Adding item to cart
    public class AddToCartTest {
        @Test
        public void testAddingItemToCart() {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.open();
            loginPage.login("standard_user", "secret_sauce");

            ProductsPage productsPage = new ProductsPage(driver);
            productsPage.addToCart("Sauce Labs Backpack");

            CartPage cartPage = new CartPage(driver);
            Assert.assertTrue(cartPage.isProductInCart("Sauce Labs Backpack"));
        }
    }

    // Test 2: Removing item from cart
    public class RemoveFromCartTest {
        @Test
        public void testRemovingItemFromCart() {
            // Войти на сайт и добавить товар в корзину

            CartPage cartPage = new CartPage(driver);
            cartPage.removeProduct("Sauce Labs Backpack");
            Assert.assertFalse(cartPage.isProductInCart("Sauce Labs Backpack"));
        }
    }

    // Тест 3: Редактирование количества товара в корзине
    public class UpdateCartTest {
        @Test
        public void testUpdatingCartItemQuantity() {
            // Войти на сайт и добавить несколько единиц товара в корзину

            CartPage cartPage = new CartPage(driver);
            cartPage.updateQuantity("Sauce Labs Bolt T-Shirt", 2);
            Assert.assertEquals(2, cartPage.getProductQuantity("Sauce Labs Bolt T-Shirt"));
        }
    }

    // Test 4: Checking out from cart
    public class CheckoutTest {
        @Test
        public void testCheckoutFromCart() {
            // Войти на сайт, добавить товар в корзину и перейти к оформлению заказа

            CartPage cartPage = new CartPage(driver);
            cartPage.checkout();

            CheckoutInfoPage checkoutInfoPage = new CheckoutInfoPage(driver);
            checkoutInfoPage.enterUserDetails("John", "Doe", "12345");

            Assert.assertTrue(checkoutInfoPage.isPaymentInformationPageDisplayed());
        }
    }

    // Test 5: Emptying cart
    public class EmptyCartTest {
        @Test
        public void testEmptyingCart() {
            // Войти на сайт, добавить товар в корзину и очистить корзину

            CartPage cartPage = new CartPage(driver);
            cartPage.emptyCart();

            Assert.assertTrue(cartPage.isCartEmpty());
        }
    }
}

