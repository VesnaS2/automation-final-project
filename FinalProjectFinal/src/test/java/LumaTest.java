import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LumaTest {
    @BeforeClass
    public static void openBrowser() {
        LumaPage.openBrowser();

    }
    @Test(priority = 10)
    public static void CreateAnAccount(){
        LumaPage.registerNewValidLumaAccount();
    }
    @Test(priority = 20)
    public static void pageTitle(){
        Assert.assertEquals(LumaPage.pageTitleCheck(),"My Account");
    }
    @Test(priority = 30)
    public static void successMessage(){
        Assert.assertEquals(LumaPage.successRegistration(),"Thank you for registering with Main Website Store.");
    }

    @Test(priority = 40)
    public static void AddToCart()  {
        LumaPage.addToCart();
    }
    @Test(priority = 50)
    public static void Shipping(){
        LumaPage.shippingDetails();
    }
    @Test(priority = 60)
    public static void orderSummery(){
        Assert.assertEquals(LumaPage.checkNumberOfProductsInOrderSummary(),"2 Items in Cart");
    }

    @Test(priority = 70)
    public static void  ReviewAndPayments(){
        LumaPage.ReviewAndPayments();
    }
    @Test(priority = 80)
    public static void ThankYouMessage(){
        Assert.assertEquals(LumaPage.getMessageFromThankYouPage(),"Thank you for your purchase!");
    }



    @AfterClass
    public static void closeBrowser(){
        LumaPage.closeBrowser();
    }
}
