package test;

import HMX.bean.config.MainconfigOfpropertyValues;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCPropertyValues {
    AnnotationConfigApplicationContext applicationContext=
            new AnnotationConfigApplicationContext(MainconfigOfpropertyValues.class);

    @Test
    public void test01(){
        printBeans(applicationContext);
        Object bean=applicationContext.getBean("永恩Bean");
        System.out.println(bean);
       applicationContext.close();
    }
    public void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] definitionNames=applicationContext.getBeanDefinitionNames();
        for (String name:definitionNames){
            System.out.println(name);
        }
    }
}
