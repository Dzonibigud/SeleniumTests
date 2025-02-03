import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.HomePage;


public class MenCategory extends BaseTest {
    @Test
    public void testJeans() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        Thread.sleep(5000);
        homePage.closeCoockies();
        homePage.navigatgeToMenCategory();
        homePage.navigateToMenSubCategoryJeans();
    }


}
