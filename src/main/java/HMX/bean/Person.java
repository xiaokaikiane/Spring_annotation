package HMX.bean;

import org.springframework.beans.factory.annotation.Value;

public class Person {
    //使用@value赋值
    //1.基本数值
    //2.可以写SpEL;#{}
    //3.${};取出配置文件[properties]中的值.
    @Value("永恩1")
    private String name;
    @Value("#{23-1}")
    private Integer age;
    @Value("${person.nickName}")
    private String nickName;
    public Person(String name, Integer age,String nickName) {
        this.name = name;
        this.age = age;
        this.nickName=nickName;
    }
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
