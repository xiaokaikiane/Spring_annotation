package HMX.bean;

public class Car {
    public Car(){
        System.out.println("Car构造方法,创建");
    }
    public void init(){
        System.out.println("Car自定义初始化");
    }
    public void destroy(){
        System.out.println("Car自定义销毁");
    }
}
