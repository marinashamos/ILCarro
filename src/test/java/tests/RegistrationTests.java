package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();

        }
    }
    @Test
    public void registrationSuccess(){

        Random random = new Random();
        int i = random.nextInt(1000);
        User user = new User().withName("Alex").withLastName("Shamos").withEmail("Alex"+i+"@mail.com").withPassword("Sham12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        //Assert.assertEquals(app.getHelperUser().getRegMessage(),"Registered");
    }
    @Test
    public void registrationWrongEmail(){

        User user = new User().withName("Alex").withLastName("Shamos").withEmail("Alexmail.com").withPassword("Sham12345$");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Wrong email format\n" +
                "Wrong email format");
    }
    @Test
    public void registrationWrongPassword(){

        User user = new User().withName("").withLastName("Shamos").withEmail("Alex@mail.com").withPassword("Sham");

        app.getHelperUser().openRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().checkPolicy();
        app.getHelperUser().submit();
        Assert.assertTrue(app.getHelperUser().isYallaButtonNotActive());
        Assert.assertEquals(app.getHelperUser().getErrorText(),"Password must contain minimum 8 symbols\n" +
                "Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&*!]");
    }
}