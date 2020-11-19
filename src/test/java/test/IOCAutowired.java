package test;

import HMX.bean.Color;
import HMX.bean.config.MainConfigOfAutowired;
import HMX.bean.test.Boss;
import HMX.bean.test.duck;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCAutowired {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext=
                new AnnotationConfigApplicationContext(MainConfigOfAutowired.class);
//        Object bean=applicationContext.getBean("bookService");
//        System.out.println(bean);
        Boss boss=applicationContext.getBean(Boss.class);
        System.out.println(boss);
//        duck duck=applicationContext.getBean(duck.class);
//        System.out.println(duck);
        Color color=applicationContext.getBean(Color.class);
        System.out.println(color);
        System.out.println(applicationContext);
        applicationContext.close();

    }
}
