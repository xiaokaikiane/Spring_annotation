package HMX.bean.config;

import HMX.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * bean的生命周期  创建----初始化---销毁
 * 容器管理bean的生命周期
 * 我们可以自定义初始化和销毁方法
 *
 * 1.指定初始化和销毁方法 xml中init-method和destroy-method方法
 * 2.注解方式:(@Bean注解里指定方法)
 *   构造(通过配置完成bean的实例化)
 *   初始化:对象创建完成,并赋值好,调用初始化方法
 *   销毁:容器关闭的时候(特殊:多实例的bean容器不会进行管理,需要的时候容器可以帮忙创建这个Bean,
 *   后面就不归容器管了,手动调用销毁方法)
 * 3.通过Bean实现InitializingBean接口(定义初始化逻辑)
 *   通过Bean实现DisposableBean接口(定义销毁)
 * 4.可以使用JSR250规范(@PostConstruct:在bean创建完成并且属性赋值完成;来执行初始化方法
 * @PreDestroy:在容器销毁bean之前通知进行清理工作
 * 5.BeanPostProcessor:(bean的后置处理器)在bean初始化前后进行一些处理
 *  PostProcessorBeforeInitialization(初始化前方法)
 *  PostProcessorAfterInitialization(初始化后方法)
 *
 *
 * BeanPostprocessor原理:
 * 首先会遍历容器中所有的BeanPostProcessor;按个执行beforeInitialization,一直到为null,
 * 跳出for循环,就不会执行后面的BeanPostProcessor.PostProcessorBeforeInitialization方法
 * populateBean():给Bean进行属性赋值
 * initialization:分三步
 * {
 *     applyBeanPostProcessorBeforeInitialization():应用初始化前方法
 *     invokeInitMethods():执行初始化
 *     applyBeanPostProcessorAfterInitialization():应用初始化后方法
 * }
 *
 * Spring底层对BeanPostProcessor的使用:
 * bean的赋值,注入其他组件,@Autowired自动装配注解,生命周期的注解功能,@Async(异步方法)等等都是使用的
 * BeanPostProcessor;
 */
@Configuration
@ComponentScan("HMX.bean.test")
public class MainConfigOfLifeCycle {
    @Bean(value = "car1",initMethod="init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }
}
