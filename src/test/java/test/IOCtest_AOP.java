package test;

import HMX.bean.AOP.MathCalculator;
import HMX.bean.config.MainConfigOfAOP;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCtest_AOP {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext=
                new AnnotationConfigApplicationContext(MainConfigOfAOP.class);
        MathCalculator calculator=applicationContext.getBean(MathCalculator.class);
        calculator.div(1,0);
        applicationContext.close();
    }
}
