package test;

import HMX.bean.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCtest_LifeCycle {
    @Test
    public void test01(){
        //1.创建ioc容器
        AnnotationConfigApplicationContext applicationContext=
                new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        System.out.println("容器创建完成...");
        applicationContext.close();
        //构造方法,创建
        //自定义初始化
        //容器创建完成...
        //自定义销毁
    }
}
