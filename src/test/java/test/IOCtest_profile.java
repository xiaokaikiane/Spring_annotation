package test;

import HMX.bean.config.MainConfigOfProfile;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public class IOCtest_profile {
    //1.使用命令行参数:在虚拟机参数位置加载-Dspring.profiles.active=test
    //2.无参的加载方式,在环境中设置参数
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext=
                new AnnotationConfigApplicationContext();
        //1.创建一个appicationContext
        //2.设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test");
        //3.加载配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4.启动刷新容器
        applicationContext.refresh();
        String[] namesForType=applicationContext.getBeanNamesForType(DataSource.class);
        for (String name:namesForType){
            System.out.println(name);
        }
        applicationContext.close();
    }
}
