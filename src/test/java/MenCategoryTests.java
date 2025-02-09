import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.MenJeansPage;
import pages.ShoppingBagPage;

class MenCategoryTests extends BaseTest {
    @Test
    void testJeans() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.closeCookies();
        MenJeansPage menJeansPage = homePage.navigateToMenJeans();
        ShoppingBagPage shoppingBagPage = menJeansPage.addToBagFirstProduct();
        shoppingBagPage.payPalButton();
    }
}
