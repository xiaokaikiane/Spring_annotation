package HMX.bean.config;

import HMX.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;

/**
 * @Profile;Spring为我们提供的可以根据当前环境,动态激活和切换一系列组件(Bean)的功能
 * 一个项目有开发环境,测试环境,生产环境
 * 对应数据源:(/A) (/B) (/C)
 * @profile:指定组件在哪个环境下才能被注入到容器中,不指定,任何环境下都能注册这个组件
 * 1.加了环境标识的组件(bean) ,只有这个环境被激活的时候才能注册到容器中,默认是default环境
 * @Profile也可写在类上,只有当某个环境下,才能运行
 */
@PropertySource("classpath:/dbConfig.properties")
@Configuration
public class MainConfigOfProfile implements EmbeddedValueResolverAware {
    @Profile("Test")
    @Bean
    public Yellow yellow(){
        return new Yellow();
    }
    private StringValueResolver valueResolver;
    String driverclass;
    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.valueResolver=stringValueResolver;
        driverclass=valueResolver.resolveStringValue("${db.driverClass}");
    }

    @Value("${db.user}")
    private String user;
    //开发
    @Profile("develop")
    @Bean("开发")
    public DataSource dataSourceDev(@Value("${db.password}")String pwd) throws Exception{
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/sys");
        dataSource.setDriverClass(driverclass);
        return dataSource;
    }
    //测试
    @Profile("test")
    @Bean("测试")
    public DataSource dataSourceTest(@Value("${db.password}")String pwd) throws Exception{
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setDriverClass(driverclass);
        return dataSource;
    }
    //生产
    @Profile("product")
    @Bean("生产")
    public DataSource dataSourceProd(@Value("${db.password}")String pwd) throws Exception{
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(pwd);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/java_oj");
        dataSource.setDriverClass(driverclass);
        return dataSource;
    }
}
