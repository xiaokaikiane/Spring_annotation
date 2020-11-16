package HMX.bean;

import org.springframework.beans.factory.FactoryBean;
//创建一个Spring定义的FactoryBean
public class ColorFactoryBean  implements FactoryBean<Color> {
    //返回一个color对象这个对象会添加到容器中
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }
//返回的类型
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }
//是否单例 true是单例  false不是单例
    @Override
    public boolean isSingleton() {
        return true;
    }
}
