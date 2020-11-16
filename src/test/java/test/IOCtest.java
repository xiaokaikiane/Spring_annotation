package test;

import HMX.bean.Person;
import HMX.bean.config.MainConfig;
import HMX.bean.config.MainConfig2;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.sql.SQLOutput;
import java.util.Map;

public class IOCtest {
    public void printBeans(AnnotationConfigApplicationContext applicationContext){
        String[] definitionNames=applicationContext.getBeanDefinitionNames();
        for (String name:definitionNames){
            System.out.println(name);
        }
    }
    @Test
    public void testImport(){
        AnnotationConfigApplicationContext applicationContext=new AnnotationConfigApplicationContext(MainConfig2.class);
        printBeans(applicationContext);
        //工厂bean获取的是调用getObject创建的对象
        Object bean=applicationContext.getBean("colorFactoryBean");
        //bean的类型:class HMX.bean.Color,所以返回的类型是color不是colorFactoryBean
        System.out.println("bean的类型:"+bean.getClass());
        //加上&符号获取当前的bean
        Object bean1=applicationContext.getBean("&colorFactoryBean");
        System.out.println("bean1的类型:"+bean1.getClass());
    }
    @Test
    @SuppressWarnings("resource")
    public void test03(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] namesForType=annotationConfigApplicationContext.getBeanNamesForType(Person.class);
        for (String name:namesForType){
            System.out.println(name);
        }
        Map<String,Person> persons=annotationConfigApplicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
        //获取环境变量的值
        ConfigurableEnvironment environment=annotationConfigApplicationContext.getEnvironment();
        String property=environment.getProperty("os.name");
        System.out.println();
    }
    @Test
    @SuppressWarnings("resource")
    public void test01(){
      AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(MainConfig.class);
      String[] definitionNames=annotationConfigApplicationContext.getBeanDefinitionNames();
      for (String name:definitionNames){
          System.out.println(name);
      }
    }
    @Test
    @SuppressWarnings("resource")
    public void test02(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext=new AnnotationConfigApplicationContext(MainConfig2.class);
        String[] definitionNames=annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String name:definitionNames){
            System.out.println(name);
        }
        //默认是单实例的
        Object bean=annotationConfigApplicationContext.getBean("person2");
        Object bean2=annotationConfigApplicationContext.getBean("person2");
        System.out.println(bean==bean2);
    }
}
