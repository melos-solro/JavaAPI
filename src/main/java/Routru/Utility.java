package Routru;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by nisargap on 4/1/16.
 */
public class Utility {

    public static String getHost(){

        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        return context.getBean("prodHost").toString();
    }

    public static String getGoogleClientId(){

        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        return context.getBean("googleClientId").toString();

    }

    public static String getGoogleClientSecret(){

        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");

        return context.getBean("googleClientSecret").toString();

    }


}
