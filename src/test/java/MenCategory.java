import base.BaseTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MenJeansPage;
import pages.MenPage;


public class MenCategory extends BaseTest {
    @Test
    public void testJeans() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        MenPage menPage = new MenPage(driver);
        //Thread.sleep(5000);
        menPage.closeCookies();
        menPage.navigateToMenCategory();
        menPage.navigateToMenJeans();
        MenJeansPage menJeans = new MenJeansPage(driver);
        menJeans.moveToProduct(12);
    }
}
