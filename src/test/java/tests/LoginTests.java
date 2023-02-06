package tests;

import manager.DataProviderUser;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("The test needs  logout scenario");
        }
    }
    @DataProvider
    public Iterator<Object[]> loginData() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"dsa@gmail.com", "Qq123456$"});
        list.add(new Object[]{"dsa@gmail.com", "Qq123456$"});
        list.add(new Object[]{"alex@gmail.com", "Sham12345$"});

        return list.iterator();

    }
    @Test(dataProvider = "LoginData")
    public void loginSuccess(String email, String password) {

        logger.info("Login with valid data :  email: "+email +" & password: "+password);
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(email,password);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Tests success");
    }
    @Test(invocationCount = 2)
    public void loginSuccessNew() {

        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("alex@gmail.com", "Sham12345$");
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Assert check message with text --> 'Logged in success'");
    }
    @Test(dataProvider = "LoginDataCls",dataProviderClass = DataProviderUser.class)
    public void loginSuccess2(String email, String password) {

        logger.info("Login with valid data :  email: "+email +" & password: "+password);
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(email,password);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Tests success");
    }

    @Test(dataProvider = "loginDataUser",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModel(User user) {

        logger.info("Tests start with user model---" + user.toString());
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        //Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
        logger.info("Tests success");
    }
    @Test(dataProvider = "loginDataUserFromFile",dataProviderClass = DataProviderUser.class)
    public void loginSuccessModelFromFile(User user) {

        logger.info("Tests start with user model---" + user.toString());
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm(user);
        app.getHelperUser().submit();
        Assert.assertEquals(app.getHelperUser().getMessage(), "Logged in success");
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
        Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Error message is: 'Login or Password incorrect'");
    }

    @Test
    public void loginUnregisterUser() {
        logger.info("Login unregister user with valid data email:'mjmj@gmail.com' & password:'Tt12345$'");
        app.getHelperUser().openFormLogin();
        app.getHelperUser().fillLoginForm("mjmj@gmail.com", "Tt12345$");
        app.getHelperUser().submit();
        Assert.assertFalse(app.getHelperUser().isLogged());
        //Assert.assertEquals(app.getHelperUser().getMessage(), "\"Login or Password incorrect\"");
        logger.info("Error message is: 'Login or Password incorrect'");

    }
    @AfterMethod
    public void postCondition() {
        app.getHelperUser().closeDialogContainer();
    }
}
