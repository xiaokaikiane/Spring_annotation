package test;

import HMX.bean.tx.TxConfig;
import HMX.bean.tx.UserService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_tx {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext=
                new AnnotationConfigApplicationContext(TxConfig.class);
        UserService userService=applicationContext.getBean(UserService.class);
        userService.insertUser();
        applicationContext.close();
    }
}
