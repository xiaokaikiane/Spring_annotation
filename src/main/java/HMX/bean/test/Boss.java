package HMX.bean.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//默认加在ioc容器中的组件,容器启动会调用无参构造器,在进行初始化赋值操作
@Component
public class Boss {
    private duck duck;

    //构造器要用的组件也是从容器中获取的
    @Autowired
    public Boss(duck duck){
        this.duck=duck;
        System.out.println("Boss的有参构造器");
    }
    public HMX.bean.test.duck getDuck() {
        return duck;
    }
//    @Autowired //标注在方法上,Spring容器创建当前对象,就会调用方法,完成赋值
    //方法使用的参数,自定义类型的值从ioc容器中获取
    public void setDuck(HMX.bean.test.duck duck) {
        this.duck = duck;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "duck=" + duck +
                '}';
    }
}
