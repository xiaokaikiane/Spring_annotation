package HMX.bean.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class duck implements ApplicationContextAware, BeanNameAware {
    private ApplicationContext applicationContext;

    @Override
    public void setBeanName(String s) {
        System.out.println("传入的Bean的id"+s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("传入的ioc"+applicationContext);
        this.applicationContext=applicationContext;
    }
}
