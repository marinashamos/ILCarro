package tests;

import manager.ListenerTNG;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
@Listeners(ListenerTNG.class)



public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("I need logout");
        }
    }

    @Test
    public void loginSuccess() {
        logger.info("Login with valid data: email: dsa@gmail.com & password: Qq123456$");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("dsa@gmail.com", "Qq123456$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Tests success");
    }
    @Test
    public void loginSuccessModel() {
        User user=new User().withEmail("dsa@gmail.com").withPassword("Qq123456$");

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"Logged in success");
        logger.info("Tests success");


    }

    @Test
    public void loginWrongEmail() {
        User user = new User().withEmail("dsagmail.com").withPassword("Qq123456$");
        logger.info("Login with invalid email: 'dsagmail.com' & valid password: Qq123456$");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getErrorText(), "It'snot look like email");
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        logger.info("Error message is: 'It'snot look like email'");
    }

    @Test
    public void loginWrongPassword() {

        User user = new User().withEmail("dsa@gmail.com").withPassword("Qq12");
        logger.info("Login with valid email: 'dsa@gmail.com' & invalid password: Qq12");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
        logger.info("Error message is: 'Login or Password incorrect'");
    }
    @Test (enabled = false)
    public void loginUnregisterUser() {
        logger.info("Login unregister user with valid data email:'mjmj@gmail.com' & password:'Tt12345$'");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("mjmj@gmail.com","Tt12345$");
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertEquals(app.getHelperUser().getMessage(),"\"Login or Password incorrect\"");
        logger.info("Error message is: 'Login or Password incorrect'");

    }
    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeDialogContainer();
    }
}
