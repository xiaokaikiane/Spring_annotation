package HMX.bean.config;

import HMX.bean.AOP.LogAspects;
import HMX.bean.AOP.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Aop[动态代理]:只在程序运行期间动态的将某段代码切入到指定方法指定位置进行运行的编程方式.
 * 1.导入aop模块:Spring Aop:(spring-aspects)
 * 2.定义一个业务逻辑类(MathCalculator.java)在业务逻辑运行的时候将日志进行打印(方法之前,方法运行,方法
 * 出现异常)
 * 3.定义一个日志切面类(LogAspects):切面类里面的方法需要动态感知MathCalculation.div运行到哪里了然后执行
 * 通知方法:
 *   前置通知(@Before):logStart:在目标方法(div)运行之前运行
 *   后置通知(@After):logEnd:在目标方法(div)运行结束之后运行
 *   返回通知(@AfterReturning):logReturn:在目标方法(div)正常返回之后运行
 *   异常通知(@AfterThrowing):logException:在出异常的时候运行
 *   环绕方法(@Around):动态代理,手动推进目标方法运行(joinPoint.proceed())
 * 4.给切面类的目标方法标注何时何地运行
 * 5.将切面类和业务逻辑类(目标方法所在类)都加入到容器中
 * 6.告诉Spring哪个类是切面类(给切面类加上@Aspect)
 * 7.给配置类加@EnableAspectJAutoProxy[开启基于注解的Aop模式]
 *
 * 三步:
 *   1.将业务逻辑组件和切面类都加入到容器中;告诉Spring那个是切面类
 *   2.在切面类上的每一个通知方法上标注解,告诉Spring何时何地运行(切入点表达式)
 *   3.开启基于注解的aop模式:@EnableAspectJAutoProxy
 *
 *   Aop原理:[看给容器注册了什么组件,这个组件什么时候工作,这个组件的功能是什么]
 *   @EnableAspectJAutoProxy
 *   1.@Import({AspectJAutoProxyRegistrar.class})给容器中导入AspectJAutoProxyRegistrar
 *   利用AspectJAutoProxyRegistrar自定义给容器中注册bean;
 *   给容器中注册一个AnnotationAwareAspectJAutoProxyCreator(注解装配模式下的切面自动代理创建器);
 *   2.AnnotationAwareAspectJAutoProxyCreator
 *   AnnotationAwareAspectJAutoProxyCreator
 *     -->AspectJAwareAdvisorAutoProxyCreator
 *       -->AbstractAdvisorAutoProxyCreator
 *        -->AbstractAutoProxyCreator
 *         -->implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 *         关注后置处理器(在Bean初始化完成前后做事情),自动装配BeanFactory
 *    AbstractAutoProxyCreator.setBeanFactory();
 *    AbstractAutoProxyCreator.有后置处理器的逻辑;
 *
 *    AbstractAdvisorAutoProxyCreator.setBeanFactory()重写, 再调用InitBeanFactory;
 *
 *    AnnotationAwareAspectJAutoProxyCreator.InitBeanFactory();
 *
 *    Aop的流程:
 *     1>.传入配置类,创建ioc容器
 *     2>.注册配置类,调用refresh()刷新容器
 *     3>.registerBeanPostProcessor(BeanFactory);注册bean的后置处理器方便拦截bean的创建
 *       1).先获取ioc容器已经定义了的需要创建对象的所有BeanPostProcessor
 *       2).给容器中加别的BeanPostProcessor
 *       3).优先注册实现了priorityOrdered接口的BeanPostProcessor
 *       4).在给容器中注册实现了Ordered接口的BeanPostProcessor;
 *       5).在注册没实现优先级接口的BeanPostProcessor
 *       6).注册BeanPostProcessor,实际上就是创建BeanPostProcessor对象,保存在容器中
 *         例:创建internalAutoProxyCreator的BeanPostProcessor[AnnotationAwareAspectJAutoProxyCreator]
 *         1).创建Bean的实例
 *         2).populateBean:给Bean的各种属性赋值
 *         3).initializationBean;初始化Bean
 *             1).invokeAwareMethods();处理Aware接口的方法回调
 *             2).applyBeanPostProcessorBeforeInitialization():应用初始化前方法
 *             3).invokeInitMethods();执行初始化方法
 *             4).applyBeanPostProcessorAfterInitialization():应用初始化后方法
 *         4).BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功-->aspectAdviserBuilder
 *       7).把BeanPostProcessor注册到BeanFactory中;
 *            beanFactory.addBeanPostProcessor(postProcessor);
 * ======以上是创建和注册AnnotationAwareAspectJAutoProxyCreator的过程======
 *    4>.finishBeanFactoryInitialization(beanFactory);完成BeanFactory初始化工作;创建剩下的单实例bean
 *       1).遍历容器中所有的bean,依次创建对象getBean(beaName);
 *       getBean()-调用doGetBean()-getSingleton()获取单实例bean
 *       2).创建bean
 *          [[AnnotationAwareAspectJAutoProxyCreator在所有bean创建之前会有一个拦截,InstantiationAwareBeanPostProcessor,会调用postProcessBeforeInstantiation]]
 *         1).先从缓存中获取当前bean,如果能获取到,说明bean是之前被创建过的,直接使用,否则在创建
 *           只要创建好的bean都会被缓存起来
 *         2).createBean()创建Bean;
 *            [[BeanPostProcessor是在Bean对象创建完成初始化前后调用的]]
 *            [[InstantiationAwareBeanPostProcessor是创建Bean实例之前先尝试用后置处理器返回对象的]]
 *            1.先拿到要创建的bean的信息
 *            2.resolveBeforeInstantiation(beanName,mbdToUse);解析
 *            希望后置处理器在此能返回一个代理对象,如果能返回一个代理对象,如果不能,就继续之执行第三步
 *               1.后置处理器先尝试返回对象
 *               bean=applyBeanPostProcessorBeforeInstantiation()
 *               拿到所有后置处理器,如果是InstantiationAwareBeanPostProcessor;
 *               就执行postProcessorBeforeInstantiation
 *               if(bean!=null){
 *                   bean=applyBeanPostProcessorsAfterInitialization(bean,beanName)
 *               }
 *            3.doCreateBean(beanName,mbdToUse,args);真正的去创建一个Bean实例,和3.6流程一样
 *
 *    AnnotationAwareAspectJAutoProxyCreator[InstantiationAwareBeanPostProcessor]的作用;
 *     1.每一个Bean创建之前,调用postProcessBeforeInstantiation()
 *          1).当前Bean是否在adviserBean中
 *          2).判断当前bean是否是基础类型的Advice,Pointcut,Advisor.AopInfrastructureBean,
 *          或者是否是切面(@Aspect)
 *          3).是否需要跳过
 *               1.获取候选的增强器(切面里面的通知方法) List<advisor> candidateAdvisors]
 *                每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor;
 *                判断每一个增强器是否是AspectPointcutAdvisor类型的;返回为true
 *               2.永远返回false;
 *     2.创建对象
 *      postProcessorAfterInitialization;
 *        return wrapIfNecessary(Bean,beanName,cacheKey);
 *        1.获取当前bean的左右增强器(通知方法)
 *            1.找到能在当前Bean使用的增强器(找哪些通知方法是需要切入当前bean方法的)
 *            2.获取到能在当前bean使用的增强器
 *            3.给增强器排序
 *        2.保存当前bean在advisorBeans中
 *        3.如果当前Bean需要增强,创建当前Bean的代理对象
 *             1.获取所有增强器(通知方法)
 *             2.保存到proxyFactory
 *             3.创建代理对象(JDk和cglib)
 *        4.给容器中返回当前组件使用的cglib增强了的代理对象;
 *        5.以后容器中获取到的就是这个组件的代理对象,执行目标方法的时候,代理对象就会执行通知方法的流程
 *    3.目标方法的执行
 *       容器中保存了组件的代理对象(增强后的对象),这个对象里面保存了详细信息(比如增强器,目标对象,xx)
 *       1.CglibAopProxy.intercept();拦截目标方法的执行
 *       2.根据proxyFactory对象获取将要执行的目标方法拦截器链
 *       3.如果没有拦截器链,直接执行目标方法
 *       4.如果有拦截器链,把需要执行的目标对象,目标方法,拦截器链等信息传入创建一个CglibMethodInvocation对象
 *         并调用MethodInvocation.proceed()
 *       List<Object> chain=this.advised.getInterceptorsAndDynamicInterceptionAdvice
 *        1).LIst<Object> interceptorList   保存所有的拦截器 5
 *          一个默认的ExposeInvocationInterceptor和四个增强器
 *        2).遍历所有的增强器,将其转为Interceptor
 *           registry.getInterceptors(advisor);
 *        3).将增强器转化为List<MethodInterceptor>;
 *            如果是MethodInterceptor,直接加入到集合中
 *            如果不是,使用AdvisorAdepter将增强器转化为MethodInterceptor;
 *            转化完成返回MethodInterceptor数组
 *    拦截器链:(每一个通知方法又被包装为方法拦截器,利用MethodInterceptor机制)
 *    拦截器链的触发过程:
 *     1.如果没有拦截器执行目标方法,或者拦截器的索引和拦截器数组-1大小一样(指定到了最后一个拦截器)
 *       就会执行目标方法
 *     2.
 *
 *
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class MainConfigOfAOP {
    //业务逻辑类加入容器中
    @Bean
    public MathCalculator calculator(){
        return new MathCalculator();
    }

    //切面类加入到容器中
    @Bean
    public LogAspects logAspects(){
        return new LogAspects();
    }
}
