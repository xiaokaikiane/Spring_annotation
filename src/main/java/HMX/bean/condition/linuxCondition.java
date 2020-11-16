package HMX.bean.condition;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

//判断是否是linux系统
public class linuxCondition implements Condition {
    /**
     *
     * @param conditionContext:判断条件能使用的上下文(环境)
     * @param annotatedTypeMetadata:注释信息
     * @return
     */
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
       //TODO 是否linux系统
        //1.获取到ioc使用的beanFactory
        ConfigurableListableBeanFactory beanFactory=conditionContext.getBeanFactory();
        //2.获取类加载器
        ClassLoader classLoader=conditionContext.getClassLoader();
        //3.获取当前环境信息
        Environment environment=conditionContext.getEnvironment();
        //4.获取bean定义的注册类
        BeanDefinitionRegistry registry=conditionContext.getRegistry();
        String property=environment.getProperty("os.name");
         if(property.contains("Linux")){
             return true;
         }
        return false;
    }
}
