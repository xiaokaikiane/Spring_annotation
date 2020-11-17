package HMX.bean.test;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
@Component
public class Dog implements ApplicationContextAware {
    public Dog(){
        System.out.println("Dog创建Bean,构造方法");
    }
    //对象创建并赋值后调用
    @PostConstruct
    public void init(){
        System.out.println("Dog对象创建并赋值后调用");
    }
    //容器移除对象之前调用
    @PreDestroy
    public void destroy(){
        System.out.println("Dog容器移除对象之前调用");
    }
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
