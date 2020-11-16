package HMX.bean.condition;

import HMX.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegister implements ImportBeanDefinitionRegistrar {
    /**
     *
     * @param importingClassMetadata 当前类的注解信息
     * @param registry  bean的注册类 把所有需要添加到容器中的bean注册进来
     *                 调用 BeanDefinitionRegistry.registryBeanDefinition手工注册
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //查询容器中有没有
        boolean definition=registry.containsBeanDefinition("HMX.bean.Red");
        boolean definition2=registry.containsBeanDefinition("HMX.bean.Blue");
        if(definition&&definition2){
            //指定Bean的定义信息(bean的类型,bean的..)
            RootBeanDefinition beanDefinition=new RootBeanDefinition(RainBow.class);
            //指定bean的名字
            registry.registerBeanDefinition("rainbow",beanDefinition);
        }
    }
}
