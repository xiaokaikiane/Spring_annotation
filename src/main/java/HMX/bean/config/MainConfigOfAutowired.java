package HMX.bean.config;

import HMX.bean.Color;
import HMX.bean.dao.bookDao;
import HMX.bean.test.cat;
import HMX.bean.test.duck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring利用依赖注入(DI),完成对IOC的容器中各个组件依赖关系赋值
 * 1.@Autowired(required=)后面为false,意为非必须的,找的到这个bean就装配,找不到就为null,不会报错
 *    默认优先按照类型去容器中找你对应的组件:applicationContext(要找的组件.class),
 *    找到就赋值.如果找到多个相同类型的组件,在将属性的名称作为组建的id去容器中查找
 * @qualifier 指定需要装配的组件id,不再采用默认的属性名
 * 自动装配默认一定要将属性赋值好,没有就会报错
 * @Primary表示Spring在自动装配时,默认首选的bean进行装配
 *
 * 2.Spring还支持使用@Resource(JSR250规范)和Inject(JSR330规范)这是java的规范,而Autowired
 * 属于Spring的注解
 * @Resource默认是按组件名称来装配的,没有其他的像Autowired的功能
 * #Inject:需要导入javax.inject的包,和Autowired相似,只是内部不支持判断是否为true为false的功能
 * AutowiredAnnotationBeanPostProcessor:解析完成自动装配的功能
 *
 * 3.@Autowired:构造器,参数,方法,属性都可以使用
 *
 * 4.自定义组件想要使用Spring容器底层的一些组件(例如ApplicationContext,BeanFactory,等等);
 *  自定义组件就要实现xxxxxAware接口;在创建对象的时候,会调用接口规定的相关方法给当前bean注入相关的Spring底层的组件.
 *  xxxAware接口都是继承Aware接口,Aware意思为意识到,感知到,可以理解为感知到Aware前面的东西,例如
 *  BeanFactoryAware就是感知到bean工厂,用来传入当前Bean工厂的实例的引用
 *
 *  xxxAware:功能对应使用对应的xxxProcessor来处理的;
 *  ApplicationContextAware==>ApplicationContextAwareProcessor
 */
@Configuration
@ComponentScan({"HMX.bean.Service","HMX.bean.dao","HMX.bean.Controller","HMX.bean.test"})
public class MainConfigOfAutowired {
    @Bean("booDao2")
    public bookDao bookDao(){
        bookDao bookdao= new bookDao();
        bookdao.setLable("2");
        return bookdao;
    }

    /**
     * @Bean标注的方法创建对象的时候,方法参数的值从容器中获取,在参数的中的@Autowired的可以省略
     * @param duck
     * @return
     */
    @Bean
    public Color color(duck duck){
        Color color=new Color();
        color.setDuck(duck);
        return color;
    }
}
