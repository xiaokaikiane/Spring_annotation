package HMX.bean;

import HMX.bean.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {
    public static void main(String[] args) {
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("beans.xml");
//        Person bean= (Person) applicationContext.getBean("person");
//        System.out.println(bean);
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean=applicationContext.getBean(Person.class);
        System.out.println(bean);
        String[] beanNamesForType=applicationContext.getBeanNamesForType(Person.class);
        for (String name:beanNamesForType){
            System.out.println(name);
        }
    }
}
