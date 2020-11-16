package HMX.bean.config;

import HMX.bean.Person;
import HMX.bean.Service.bookService;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

//配置类=配置文件
@Configuration  //告诉Spring这是一个配置类
//@ComponentScan (value="HMX.bean",excludeFilters = {
//      @ComponentScan.Filter(type = FilterType.ANNOTATION,classes={Controller.class})
//})//可排除那个类等等,includeFilters可以选择包含的那些类,类似拦截器
@ComponentScan (value="HMX.bean",includeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes={Controller.class}),
//        @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE,classes ={bookService.class}),
        @ComponentScan.Filter(type=FilterType.CUSTOM,classes={MyTypeFilter.class})
},useDefaultFilters=false)
//@ComponentScan value:指定要扫描的包
//excludeFilters=Filter[]
//@ComponentScans(value={
//        @ComponentScan (value="HMX.bean",includeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION,classes={Controller.class})
//        },useDefaultFilters=false)
//})里面可以放置多个扫描规则
//FilterType.ANNOTATION:按照注解
//FilterType.ASSIGNABLE_TYPE:按照给定的类型
//FilterType.ASPECTJ:使用ASPECTJ表达式
//FilterType.REGEX:使用正则表达式
//FilterType.CUSTOM:使用自定义
public class MainConfig {
    @Bean("person1")  //给容器注册一个Bean;类型为返回值类型,id默认用方法名作为id
public Person person(){
    return new Person("李四",20);
}
}
