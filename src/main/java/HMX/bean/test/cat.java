package HMX.bean.test;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class cat implements InitializingBean, DisposableBean {
    public cat(){
        System.out.println("Cat创建Bean,构造方法执行");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Cat销毁方法");
    }

    @Override
    //Bean创建完成,属性并赋好值后调用
    public void afterPropertiesSet() throws Exception {
        System.out.println("Cat初始化方法");
    }
}
