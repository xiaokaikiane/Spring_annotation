package HMX.bean.config;

import HMX.bean.Color;
import HMX.bean.ColorFactoryBean;
import HMX.bean.Person;
import HMX.bean.Red;
import HMX.bean.condition.MyImportBeanDefinitionRegister;
import HMX.bean.condition.MyImportSelector;
import HMX.bean.condition.linuxCondition;
import HMX.bean.condition.windowsCondition;
import org.springframework.context.annotation.*;

@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegister.class})
public class MainConfig2 {
    //默认是单实例的
    /**
     * @Scope可以改变bean的作用域
     * prototype 多实例的  ioc容器启动并不会去调用方法创建对象放在容器中,
     * 每次获取的时候才会调用方法创建对象;
     * singleton 单实例的  ioc容器启动的时候会调用方法创建对象放到ioc容器中,
     * 以后每次获取都是直接从容器中(map.get())中去拿
     * @return
     *
     * @Lazy可以使单实例的bean延迟加载
     * 懒加载:容器启动不创建对象,第一次使用的bean创建对象,并初始化
     * 对单实例bean来说的:默认在容器启动的时候创建对象;
     */
//    @Scope("prototype")
    @Lazy
    @Bean("person2")
    public Person person(){
        return new Person("王五",21);
    }
    /**
     * @Conditional:按照一定的条件进行判断,满足条件给容器中注册bean
     * 假如系统是windows,给容器中注册"亚索"
     * 假如系统是linux系统,给容器中注册"提莫"
     * 也可给一个类进行注解,满足这个条件这个类才能生效
     */
    @Conditional({windowsCondition.class})
    @Bean("person3")
    public Person person01(){
        return new Person("亚索",23);
    }
    @Conditional({linuxCondition.class})
    @Bean("person4")
    public Person person02(){
        return new Person("提莫",1);
    }
    /**
     * 给容器中注册组件
     * 1).包扫描+组件组件标注注解(@Controller/@Service/@Repository/@Component)
     * 2).@Bean[导入的第三方包里面的组件]
     * 3).@Import[快速给容器中导入一个组件]
     *   1).@Import(要导入到容器的组件);容器中就会自动注册这个组件,id默认是全类名
     *   2).ImportSelector :返回需要导入的组件的全类名数组
     *   3)ImportBeanDefinitionRegistrar:手动注册到bean的容器中
     * 4).使用Spring提供的FactoryBean(工厂Bean)
     *   getObject()把某个对象放在容器中
     *   getObjectType()放回的类型
     *   isSingleton()是否单例
     *      1)默认获取到的是Bean调用getObject创建的对象
     *      2)获取工厂bean本身,我们需要给id前面加上一个&符号
     */
    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
