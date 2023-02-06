package manager;
import model.User;

import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> LoginDataCls() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"dsa@gmail.com", "Qq123456$"});
        list.add(new Object[]{"dsa@gmail.com", "Qq123456$"});
        list.add(new Object[]{"alex@gmail.com", "Sham12345$"});

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> loginDataUser(){

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{new User().withEmail("dsa@gmail.com").withPassword("Qq123456$")});
        list.add(new Object[]{new User().withEmail("dsa@gmail.com").withPassword("Qq123456$")});
        list.add(new Object[]{new User().withEmail("alex@gmail.com").withPassword("Sham12345$")});


        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> loginDataUserFromFile() throws IOException {

        List<Object[]> list = new ArrayList<>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("src/test/resources/logdata.csv")));
        String line  = bufferedReader.readLine();
        while (line!=null){
            String[] split =  line.split(",");
            list.add(new Object[]{new User().withEmail(split[0]).withPassword(split[1])});
            line=bufferedReader.readLine();
        }
        return list.iterator();
    }
}
